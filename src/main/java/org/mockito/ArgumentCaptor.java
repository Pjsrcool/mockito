/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito;

import org.mockito.internal.matchers.CapturingMatcher;

import javax.annotation.Nullable;
import java.util.List;

import static org.mockito.internal.util.Primitives.defaultValue;

public class ArgumentCaptor<T> {

    private final CapturingMatcher<T> capturingMatcher = new CapturingMatcher<T>();
    private final Class<? extends T> clazz;

    private ArgumentCaptor(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Use it to capture the argument. This method <b>must be used inside of verification</b>.
     * <p>
     * Internally, this method registers a special implementation of an {@link ArgumentMatcher}.
     * This argument matcher stores the argument value so that you can use it later to perform assertions.
     * <p>
     * See examples in javadoc for {@link ArgumentCaptor} class.
     *
     * @return null or default values
     */
    @Nullable
    public T capture() {
        Mockito.argThat(capturingMatcher);
        return defaultValue(clazz);
    }

    /**
     * Returns the captured value of the argument. When capturing varargs use {@link #getAllValues()}.
     * <p>
     * If verified method was called multiple times then this method it returns the latest captured value.
     * <p>
     * See examples in javadoc for {@link ArgumentCaptor} class.
     *
     * @return captured argument value
     */
    public T getValue() {
        return this.capturingMatcher.getLastValue();
    }

    /**
     * Returns all captured values. Use it when capturing varargs or when the verified method was called multiple times.
     * When varargs method was called multiple times, this method returns merged list of all values from all invocations.
     * <p>
     * Example:
     * <pre class="code"><code class="java">
     *   mock.doSomething(new Person("John");
     *   mock.doSomething(new Person("Jane");
     *
     *   ArgumentCaptor&lt;Person&gt; peopleCaptor = ArgumentCaptor.forClass(Person.class);
     *   verify(mock, times(2)).doSomething(peopleCaptor.capture());
     *
     *   List&lt;Person&gt; capturedPeople = peopleCaptor.getAllValues();
     *   assertEquals("John", capturedPeople.get(0).getName());
     *   assertEquals("Jane", capturedPeople.get(1).getName());
     * </pre>
     *
     * Example of capturing varargs:
     * <pre class="code"><code class="java">
     *   mock.countPeople(new Person("John"), new Person("Jane"); //vararg method
     *
     *   ArgumentCaptor&lt;Person&gt; peopleCaptor = ArgumentCaptor.forClass(Person.class);
     *
     *   verify(mock).countPeople(peopleCaptor.capture());
     *
     *   List expected = asList(new Person("John"), new Person("Jane"));
     *   assertEquals(expected, peopleCaptor.getAllValues());
     * </code></pre>
     * See more examples in javadoc for {@link ArgumentCaptor} class.
     *
     * @return captured argument value
     */
    public List<T> getAllValues() {
        return this.capturingMatcher.getAllValues();
    }

    /**
     * Build a new <code>ArgumentCaptor</code>.
     * <p>
     * Note that an <code>ArgumentCaptor</code> <b>*doesn't do any type checks*</b>. It is only there to avoid casting
     * in your code. This might however change (type checks could be added) in a
     * future major release.
     *
     * @param clazz Type matching the parameter to be captured.
     * @param <S> Type of clazz
     * @param <U> Type of object captured by the newly built ArgumentCaptor
     * @return A new ArgumentCaptor
     */
    public static <U, S extends U> ArgumentCaptor<U> forClass(Class<S> clazz) {
        return new ArgumentCaptor<U>(clazz);
    }
}
