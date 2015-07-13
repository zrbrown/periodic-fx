package net.eightlives.periodic.view.fx;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.eightlives.periodic.core.ParcelContainer;
import net.eightlives.periodic.core.parcel.Parcel;
import net.eightlives.periodic.core.parcel.ParcelIntent;

/**
 * A UI layout for {@link FXPeriodicView} that has a left vertical bar for
 * navigation and uses the rest of the area for {@link Parcel} presentation.
 * 
 * @author Zack Brown
 */
public class FXVerticalBarView extends BorderPane
{
    private final BorderPane leftNavigationBar = new BorderPane();
    private final VBox leftNavigationBarCenter = new VBox();
    private final HBox leftNavigationBarBottom = new HBox();
    private final StackPane contentPane = new StackPane();

    /**
     * Constructs a new {@link FXVerticalBarView}.
     */
    public FXVerticalBarView()
    {
        setId("mainPane");

        leftNavigationBarCenter.setId("leftNavigationBar");
        leftNavigationBar.setCenter(leftNavigationBarCenter);

        leftNavigationBarBottom.setId("leftNavigationBar");
        leftNavigationBar.setBottom(leftNavigationBarBottom);

        setLeft(leftNavigationBar);

        setCenter(contentPane);
        contentPane.getChildren().add(createNoContentPane());
    }

    /**
     * Creates a navigation button in the {@link ParcelIntent#STANDARD} style.
     * The button will appear in a scrollable list of buttons on the left
     * navigation bar.
     * 
     * @param displayText
     *            the text to display on the button
     * @return the new button
     */
    public Button createStandardNavigationButton(String displayText)
    {
        Button navigationButton = new Button();
        navigationButton.getStyleClass().add("navigationItemStandard");
        leftNavigationBarCenter.getChildren().add(navigationButton);
        navigationButton.setText(displayText);

        return navigationButton;
    }

    /**
     * Creates a navigation button in the {@link ParcelIntent#APPARENT} style.
     * The button will appear in the bottom of the left navigation bar.
     * 
     * @param displayText
     *            the text to display on the button
     * @return the new button
     */
    public Button createApparentNavigationButton(String displayText)
    {
        Button navigationButton = new Button();
        navigationButton.getStyleClass().add("navigationItemApparent");
        leftNavigationBarBottom.getChildren().add(navigationButton);
        navigationButton.setText(displayText);

        return navigationButton;
    }

    /**
     * Sets the visibility of the specified navigation button.
     * 
     * @param navigationButton
     *            the button whose visibility should be modified
     * @param isVisible
     *            {@code true} to set the button visible; {@code false}
     *            otherwise
     */
    public void setNavigationButtonVisible(Button navigationButton, boolean isVisible)
    {
        leftNavigationBarCenter.getChildren().stream().filter(button -> button == navigationButton).findFirst()
                .ifPresent(button ->
                {
                    button.setVisible(isVisible);
                    button.setManaged(isVisible);
                });
    }

    /**
     * Destroys the specified navigation button.
     * 
     * @param navigationButton
     *            the button to destroy
     */
    public void destroyNavigationButton(Button navigationButton)
    {
        leftNavigationBarCenter.getChildren().remove(navigationButton);
    }

    /**
     * Returns the next content pane on which to render a {@link Parcel}.
     * 
     * @return the next content pane on which to render a parcel
     */
    public ParcelContainer getNextContentPane()
    {
        ObservableList<Node> childNodes = contentPane.getChildren();

        if (childNodes.size() > 1)
        {
            childNodes.remove(childNodes.size() - 1);
        }

        StackPane nextContentPane = new StackPane();
        childNodes.add(nextContentPane);
        return new FXParcelContainer(nextContentPane);
    }

    private StackPane createNoContentPane()
    {
        StackPane noContentPane = new StackPane();
        noContentPane.setId("contentBackgroundPane");

        /*
         * TODO This needs to dynamically load the image from somewhere. CSS:
         * #contentBackgroundPane { -fx-background-image: url(IMAGE_URL);
         * -fx-background-position: center; -fx-background-repeat: no-repeat; }
         */
        noContentPane.setBackground(new Background(new BackgroundImage(new Image(
                "https://www.google.com/images/srpr/logo11w.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));
        return noContentPane;
    }
}
