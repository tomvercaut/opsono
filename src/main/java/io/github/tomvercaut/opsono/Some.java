package io.github.tomvercaut.opsono;

import java.io.Serializable;
import java.util.function.Supplier;

public class Some<T extends Serializable> extends Option<T> {
    private final T value;

    Some(T value) {
        this.value = value;
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public T get() throws IllegalStateException {
        return value;
    }

    @Override
    public Option<T> or(Option<T> o) {
        return this;
    }

    @Override
    public Option<T> some_or(Supplier<Option<T>> f) {
        return this;
    }

    @Override
    public Option<T> and(Option<T> o) {
        return o;
    }

    @Override
    public Option<T> xor(Option<T> o) {
        return o.isNone() ? this : None.ofType();
    }
}
