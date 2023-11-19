package io.github.tomvercaut.opsono;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isSome() {
        Option<Integer> o1 = new Some<>(5);
        assertTrue(o1.isSome());
    }

    @Test
    void isNone() {
        Option<Integer> o1 = new Some<>(5);
        assertFalse(o1.isNone());
    }

    @Test
    void get() {
        Option<Integer> o1 = new Some<>(5);
        assertEquals(5, o1.get());
    }

    @Test
    void or() {
        Option<Integer> o1 = new Some<>(5);
        Option<Integer> o2 = new Some<>(6);
        Option<Integer> result = o1.or(o2);
        assertTrue(result.isSome());
        assertEquals(5, result.get());
    }

    @Test
    void some_or() {
        Option<Integer> o1 = new Some<>(5);
        Option<Integer> o2 = new Some<>(6);
        Option<Integer> result = o1.some_or(() -> o2);
        assertTrue(result.isSome());
        assertEquals(5, result.get());
    }

    @Test
    void and() {
        Option<Integer> o1 = new Some<>(5);
        Option<Integer> o2 = new Some<>(6);
        Option<Integer> result = o1.and(o2);
        assertTrue(result.isSome());
        assertEquals(6, result.get());

        Option<Integer> o3 = None.ofType();
        assertTrue(o3.isNone());
    }

    @Test
    void xor() {
        Option<Integer> n1 = None.ofType();
        Option<Integer> o1 = new Some<>(5);
        Option<Integer> o2 = new Some<>(6);

        assertEquals(o1.xor(n1), o1);
        assertTrue((o1.xor(o2)).isNone());
    }
}