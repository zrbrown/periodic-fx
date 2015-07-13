package net.eightlives.periodic.view.fx;

import javafx.scene.layout.StackPane;
import net.eightlives.periodic.core.ParcelContainer;
import net.eightlives.periodic.core.parcel.Parcel;
import net.eightlives.periodic.core.parcel.ParcelViewProvider;

/**
 * An abstract JavaFX implementation of ParcelViewProvider that consumers using
 * {@link FXPeriodicView} are recommended to extend for convenience and
 * conformity. Implementing classes must still register as proper OSGi
 * Components (see {@link ParcelViewProvider} documentation).
 * 
 * @author Zack Brown
 *
 * @param <T>
 *            The view type. The {@link Parcel} this provider is bound to should
 *            know this type.
 */
public abstract class FXParcelViewProvider<T> implements ParcelViewProvider<T>
{
    @Override
    public T showView(ParcelContainer container)
    {
        if (!(container instanceof FXParcelContainer))
        {
            // TODO throw exception; bad assembly
        }

        return getFXView(((FXParcelContainer) container).getParentPane());
    }

    /**
     * Returns the JavaFX view implementation for the {@link Parcel} to which
     * this provider is bound.
     * 
     * @param parent
     *            the {@link StackPane} on which to create the Parcel view
     * @return the JavaFX view implementation for the Parcel to which this
     *         provider is bound
     */
    protected abstract T getFXView(StackPane parent);
}
