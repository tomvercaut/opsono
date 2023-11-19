package io.github.tomvercaut.opsono;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * The abstract <code>Option</code> type represents an optional and <code>Serializable</code> value.
 * <p>
 * This type can be used to:
 * <ul>
 *     <li>Optional values in classes</li>
 *     <li>Return values from functions that are not defined in specific conditions (invalid input, invalid state during processing, ...).</li>
 *     <li>Optional function arguments</li>
 * </ul>
 *
 * @param <T> the type of the value
 */
public abstract class Option<T extends Serializable> implements Serializable {


    /**
     * Returns an Option containing a value of type T.
     *
     * @param t   the value to be wrapped in the Option
     * @param <T> the type of the value
     * @return an Option containing the specified value
     * @throws NullPointerException if the specified value is null
     */
    public static <T extends Serializable> Option<T> of(T t) {
        Objects.requireNonNull(t);
        return new Some<>(t);
    }

    /**
     * Creates an Option containing a value of type T. If the value is null, it returns an Option representing None.
     *
     * @param t   the value to be wrapped in the Option
     * @param <T> the type of the value
     * @return an Option containing the specified value, or None if the value is null
     */
    public static <T extends Serializable> Option<T> ofNullable(T t) {
        if (t == null) {
            return None.ofType();
        }
        return new Some<>(t);
    }

    /**
     * Returns an Option representing None.
     *
     * @param <T> the type of the value
     * @return an Option representing None
     */
    public static <T extends Serializable> Option<T> none() {
        return None.ofType();
    }

    /**
     * Checks if the option contains a value.
     *
     * @return true if the option contains a value, false otherwise
     */
    public abstract boolean isSome();

    /**
     * Checks if the option represents None.
     *
     * @return true if the option represents None, false otherwise
     */
    public abstract boolean isNone();

    /**
     * Returns the value contained in the Option.
     *
     * @return the value contained in the Option
     * @throws IllegalStateException if the Option represents None
     */
    public abstract T get() throws IllegalStateException;


    /**
     * Returns an Option that represents the result of the logical OR operation between this Option and the specified Option.
     *
     * <table>
     *     <tr>
     *         <th>Method</th>
     *         <th>Self</th>
     *         <th>Input option</th>
     *         <th>Output option</th>
     *     </tr>
     *     <tr>
     *         <td>or</td>
     *         <td>None</td>
     *         <td>None</td>
     *         <td>None</td>
     *     </tr
     *     <tr>
     *         <td>or</td>
     *         <td>None</td>
     *         <td>Some(U)</td>
     *         <td>Some(U)</td>
     *     </tr>>
     *     <tr>
     *         <td>or</td>
     *         <td>Some(T)</td>
     *         <td>(Ignored)</td>
     *         <td>Some(T)</td>
     *     </tr>
     * </table>
     *
     * @param o the Option to be combined with this Option
     * @return an Option that represents the result of the logical OR operation
     * between this Option and the specified Option. If this instance is a {@code Some<T>} {@code Option}, this instance is returned, otherwise {@code Option<T> o} is returned.
     */
    public abstract Option<T> or(Option<T> o);

    /**
     * If this instance contains a value, this is returned.
     * Otherwise, the result of the given supplier function is returned.
     *
     * @param f the supplier function to be applied if this Option is None
     * @return If this Option is None, the result of the given supplier function is returned. Otherwise, this option (Some) is returned.
     */
    public abstract Option<T> some_or(Supplier<Option<T>> f);

    /**
     * If this instance contains a value and the input argument contains a value, the input argument is returned. Otherwise, None is returned.
     *
     * <table>
     *     <tr>
     *         <th>Method</th>
     *         <th>Self</th>
     *         <th>Input option</th>
     *         <th>Output option</th>
     *     </tr>
     *     <tr>
     *         <td>and</td>
     *         <td>None</td>
     *         <td>(ignored)</td>
     *         <td>None</td>
     *     </tr>
     *     <tr>
     *         <td>and</td>
     *         <td>Some(T)</td>
     *         <td>None</td>
     *         <td>None</td>
     *     </tr>
     *     <tr>
     *         <td>and</td>
     *         <td>Some(T)</td>
     *         <td>Some(U)</td>
     *         <td>Some(U)</td>
     *     </tr>
     * </table>
     *
     * @param o   the Option to be combined with this Option
     * @return If this instance contains a value and the input argument contains a value, the input argument is returned. Otherwise, None is returned.
     */
    public abstract Option<T> and(Option<T> o);

    /**
     * Returns an Option that represents the result of the exclusive OR operation between this Option and the specified Option.
     *
     * <table>
     *     <tr>
     *         <th>Method</th>
     *         <th>Self</th>
     *         <th>Input option</th>
     *         <th>Output option</th>
     *     </tr>
     *     <tr>
     *         <td>xor</td>
     *         <td>None</td>
     *         <td>None</td>
     *         <td>None</td>
     *     </tr>
     *     <tr>
     *         <td>xor</td>
     *         <td>None</td>
     *         <td>Some(U)</td>
     *         <td>Some(U)</td>
     *     </tr>
     *     <tr>
     *         <td>xor</td>
     *         <td>Some(T)</td>
     *         <td>None</td>
     *         <td>Some(T)</td>
     *     </tr>
     *     <tr>
     *         <td>xor</td>
     *         <td>Some(T)</td>
     *         <td>Some(U)</td>
     *         <td>None</td>
     *     </tr>
     * </table>
     *
     * @param o the Option to be combined with this Option
     * @return an Option that represents the result of the exclusive OR operation between this Option and the specified Option.
     */
    public abstract Option<T> xor(Option<T> o);
}
