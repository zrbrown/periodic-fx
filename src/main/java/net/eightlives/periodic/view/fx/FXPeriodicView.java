package net.eightlives.periodic.view.fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.eightlives.periodic.core.ParcelContainer;
import net.eightlives.periodic.core.ParcelOpener;
import net.eightlives.periodic.core.PeriodicView;
import net.eightlives.periodic.core.parcel.ParcelData;

import org.osgi.service.component.annotations.Component;

/**
 * The JavaFX reference implementation of {@link PeriodicView}.
 * 
 * @author Zack Brown
 */
@Component(service = PeriodicView.class)
public class FXPeriodicView extends Application implements PeriodicView
{
    private static FXVerticalBarView view;
    private static boolean isFXLaunched;

    private final Map<Long, Button> parcelIdsToNavigationButtons = new HashMap<Long, Button>();
    private final List<Consumer<Long>> parcelSelectedListeners = new ArrayList<Consumer<Long>>();

    @Override
    public void launchViewFramework()
    {
        // TODO add all javafx packages to launch args - new parcels could use
        // things it's not loaded with
        Executors.defaultThreadFactory().newThread(() ->
        {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            launch();
        }).start();

        while (!isFXLaunched)
        {
        }

        // TODO set to false here so it can be launched again?
    }

    @Override
    public ParcelOpener createParcelOpener(ParcelData parcelData)
    {
        Platform.runLater(() ->
        {
            Button navigationButton = null;

            switch (parcelData.getParcelIntent())
            {
                case APPARENT:
                    navigationButton = view.createApparentNavigationButton(parcelData.getDisplayText());
                    break;
                case STANDARD:
                    navigationButton = view.createStandardNavigationButton(parcelData.getDisplayText());
                    break;
                default:
                    break;
            }

            navigationButton.setOnAction(event -> parcelSelectedListeners.forEach(listener -> listener
                    .accept(parcelData.getParcelId())));

            parcelIdsToNavigationButtons.put(parcelData.getParcelId(), navigationButton);
        });

        return new ParcelOpener()
        {
            @Override
            public void setVisible(boolean isVisible)
            {
                Platform.runLater(() ->
                {
                    view.setNavigationButtonVisible(parcelIdsToNavigationButtons.get(parcelData.getParcelId()),
                            isVisible);
                });
            }

            @Override
            public void destroy()
            {
                Platform.runLater(() ->
                {
                    Button navigationButton = parcelIdsToNavigationButtons.get(parcelData.getParcelId());

                    if (navigationButton != null)
                    {
                        view.destroyNavigationButton(navigationButton);
                    }
                });
            }
        };
    }

    @Override
    public ParcelContainer getNextParcelContainer()
    {
        return view.getNextContentPane();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO load the title from somewhere (same as background image)
        primaryStage.setTitle("Periodic Framework");

        view = new FXVerticalBarView();
        Scene scene = new Scene(view, 1024, 768);
        scene.getStylesheets().add("css/periodic.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        isFXLaunched = true;
    }

    @Override
    public void addParcelSelectedListener(Consumer<Long> parcelSelectedListener)
    {
        parcelSelectedListeners.add(parcelSelectedListener);
    }

    @Override
    public void removeParcelSelectedListener(Consumer<Long> parcelSelectedListener)
    {
        parcelSelectedListeners.remove(parcelSelectedListener);
    }
}
