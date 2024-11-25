import by.solution.pair.PairElementContainer;
import by.solution.pair.Entry;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PairElementContainerTest {

    @Test
    public void testSize() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        assertEquals(0, container.size());
        container.put("Key1", 1);
        assertEquals(1, container.size());
    }

    @Test
    public void testIsEmpty() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        assertTrue(container.isEmpty());
        container.put("Key1", 1);
        assertFalse(container.isEmpty());
    }

    @Test
    public void testClear() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        container.put("Key1", 1);
        container.clear();
        assertTrue(container.isEmpty());
    }

    @Test
    public void testIterator() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        container.put("Key1", 1);
        container.put("Key2", 2);

        Iterator<Entry<String, Integer>> iterator = container.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Key1", iterator.next().getKey());
        assertTrue(iterator.hasNext());
        assertEquals("Key2", iterator.next().getKey());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() {
        PairElementContainer<String, Integer> container1 = new PairElementContainer<>();
        PairElementContainer<String, Integer> container2 = new PairElementContainer<>();
        assertEquals(container1, container2);

        container1.put("Key1", 1);
        assertNotEquals(container1, container2);

        container2.put("Key1", 1);
        assertEquals(container1, container2);
    }

    @Test
    public void testToString() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        container.put("Key1", 1);
        assertEquals("[Entity{key=Key1, value=1}]", container.toString());
    }

    @Test
    public void testGet() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        container.put("Key1", 1);
        assertEquals(Optional.of(1), container.get("Key1"));
        assertEquals(Optional.empty(), container.get("Key2"));
    }

    @Test
    public void testPutAll() {
        PairElementContainer<String, Integer> container = new PairElementContainer<>();
        Map<String, Integer> data = new HashMap<>();
        data.put("Key1", 1);
        data.put("Key2", 2);
        container.putAll(data);
        assertEquals(2, container.size());
        assertEquals(Optional.of(1), container.get("Key1"));
        assertEquals(Optional.of(2), container.get("Key2"));
    }
}