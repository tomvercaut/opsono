package io.github.tomvercaut.opsono;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoneTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ofType() {
        None<Integer> n1 = (None<Integer>) None.<Integer>ofType();
        assertTrue(n1.isNone());
    }

    @Test
    void isSome() {
        None<Integer> n1 = (None<Integer>) None.<Integer>ofType();
        assertFalse(n1.isSome());
    }

    @Test
    void isNone() {
        None<Integer> n1 = (None<Integer>) None.<Integer>ofType();
        assertTrue(n1.isNone());
    }

    @Test
    void get() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    None<Integer> n1 = (None<Integer>) None.<Integer>ofType();
                    n1.get();
                });
        assertNotNull(thrown);
    }

    @Test
    void or() {
        Option<Integer> n1 = None.ofType();
        Option<Integer> n2 = None.ofType();
        assertEquals(n1.or(n2), None.<Integer>ofType());

        Option<Integer> o2 = Option.of(6);
        Option<Integer> r1 = n1.or(o2);
        assertTrue(r1.isSome());
        assertEquals(r1.get(), 6);
    }

    @Test
    void some_or() {
        Option<Integer> n1 = None.ofType();
        Option<Integer> o1 = new Some<>(5);
        Option<Integer> result = n1.some_or(() -> o1);
        assertTrue(result.isSome());
        assertEquals(5, result.get());
    }

    @Test
    void and() {
        Option<Integer> n1 = None.ofType();
        Option<Integer> o1 = new Some<>(5);
        assertTrue((n1.and(o1)).isNone());
    }

    @Test
    void xor() {
        Option<Integer> n1 = None.ofType();
        Option<Integer> n2 = None.ofType();
        Option<Integer> o1 = new Some<>(5);

        assertTrue((n1.xor(n2)).isNone());
        assertEquals(n1.xor(o1), o1);
    }
}