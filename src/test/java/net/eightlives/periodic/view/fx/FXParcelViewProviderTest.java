package net.eightlives.periodic.view.fx;

import static org.junit.Assert.assertEquals;
import javafx.scene.layout.StackPane;
import net.eightlives.periodic.core.ParcelContainer;

import org.junit.Test;

/**
 * An abstract test which should be extended by classes testing implementations
 * of {@link FXParcelViewProvider}.
 * 
 * @author Zack Brown
 * 
 * @param <T>
 *            The view type. This needs to be the same type as the implementing
 *            view provider's type.
 */
public abstract class FXParcelViewProviderTest<T>
{
    private final FXParcelViewProvider<T> viewProvider = createParcelViewProvider();

    /**
     * Tests {@link FXParcelViewProvider#showView(ParcelContainer)}.
     */
    @Test
    public void testShowView()
    {
        StackPane parentPane = new StackPane();
        FXParcelContainer container = new FXParcelContainer(parentPane);

        viewProvider.showView(container);
        assertEquals(parentPane, getViewParentPane());

        assertEquals(viewProvider.showView(container), viewProvider.getFXView(parentPane));
    }

    /**
     * Returns the {@link StackPane} that is the parent of the root node of the
     * implementation being tested.
     * 
     * @return the view's parent pane
     */
    protected abstract StackPane getViewParentPane();

    /**
     * Create the {@link FXParcelViewProvider} implementation being tested.
     * 
     * @return a new instance of the implementation being tested
     */
    protected abstract FXParcelViewProvider<T> createParcelViewProvider();
}
