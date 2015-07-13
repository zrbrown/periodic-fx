package net.eightlives.periodic.view.fx;

import static org.junit.Assert.assertEquals;
import javafx.scene.layout.StackPane;

import org.junit.Test;

/**
 * Tests {@link FXParcelContainer}.
 * 
 * @author Zack Brown
 */
public class FXParcelContainerTest
{
    private final StackPane parentPane = new StackPane();
    private final FXParcelContainer container = new FXParcelContainer(parentPane);

    /**
     * Tests {@link FXParcelContainer#getParentPane()}.
     */
    @Test
    public void testGetParentPane()
    {
        assertEquals(parentPane, container.getParentPane());
    }
}
