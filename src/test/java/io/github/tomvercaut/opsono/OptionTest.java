package io.github.tomvercaut.opsono;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {

    @Test
    void of() {
        Option<Integer> o1 = Option.of(5);
        assertTrue(o1.isSome());
        assertEquals(5, o1.get());
    }

    @Test
    void ofNullable() {
        Option<Integer> o1 = Option.ofNullable(5);
        assertTrue(o1.isSome());
        assertEquals(5, o1.get());

        Option<Integer> o2 = Option.ofNullable(null);
        assertTrue(o2.isNone());
    }

    @Test
    void none() {
        Option<Integer> n1 = Option.none();
        assertTrue(n1.isNone());
    }
}