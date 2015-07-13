package net.eightlives.periodic.view.fx;

import javafx.scene.layout.StackPane;
import net.eightlives.periodic.core.ParcelContainer;
import net.eightlives.periodic.core.parcel.Parcel;

/**
 * The JavaFX reference implementation of {@link ParcelContainer}.
 * 
 * @author Zack Brown
 */
public class FXParcelContainer implements ParcelContainer
{
    private final StackPane parentPane;

    /**
     * Constructs a new {@link FXParcelContainer}.
     * 
     * @param parentPane
     *            the {@link StackPane} that will always be returned from
     *            {@link #getParentPane()}
     */
    public FXParcelContainer(StackPane parentPane)
    {
        if (parentPane == null)
        {
            // TODO throw exception
        }

        this.parentPane = parentPane;
    }

    /**
     * Returns the {@link StackPane} on which JavaFX {@link Parcel} UI
     * components should be created.
     * 
     * @return the pane on which JavaFX Parcel UI components should be created
     */
    public StackPane getParentPane()
    {
        return parentPane;
    }
}
