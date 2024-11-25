import by.solution.single.SingleElementContainer;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class SingleElementContainerTest {
    @Test
    public void testIteratorWithNoElements() {
        SingleElementContainer<String> container = new SingleElementContainer<>();
        Iterator<String> iterator = container.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorWithOneElement() {
        SingleElementContainer<String> container = new SingleElementContainer<>();
        container.toJList().add("Element1");
        Iterator<String> iterator = container.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element1", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorWithMultipleElements() {
        SingleElementContainer<String> container = new SingleElementContainer<>();
        container.toJList().add("Element1");
        container.toJList().add("Element2");
        container.toJList().add("Element3");

        Iterator<String> iterator = container.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Element2", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Element3", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemoveNotSupported() {
        SingleElementContainer<String> container = new SingleElementContainer<>();
        container.toJList().add("Element1");
        Iterator<String> iterator = container.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element1", iterator.next());
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    public void testIteratorConcurrentModification() {
        SingleElementContainer<String> container = new SingleElementContainer<>();
        container.toJList().add("Element1");
        container.toJList().add("Element2");

        Iterator<String> iterator = container.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element1", iterator.next());

        // Modify the container during iteration
        container.toJList().add("Element3");

        //assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals("Element2", iterator.next());
    }
}