package io.github.tomvercaut.opsono;

import java.io.Serializable;
import java.util.function.Supplier;

public class None<T extends Serializable> extends Option<T> {
    private static final None<Serializable> instance = new None<>();

    public static <T extends Serializable> Option<T> ofType() {
        @SuppressWarnings("unchecked") Option<T> r = (Option<T>) instance;
        return r;
    }


    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public T get() {
        throw new IllegalStateException("Option.get() on None can't be called.");
    }

    @Override
    public Option<T> or(Option<T> o) {
        return o;
    }

    @Override
    public Option<T> some_or(Supplier<Option<T>> f) {
        return f.get();
    }

    @Override
    public Option<T> and(Option<T> o) {
        return None.ofType();
    }

    @Override
    public Option<T> xor(Option<T> o) {
        return o;
    }


}
