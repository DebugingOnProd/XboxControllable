package org.lhq.utils.function;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 用于判断两个入参的关系
 *
 * @param <X>
 * @param <Y>
 */
@FunctionalInterface
public interface PairPredicate<X, Y> {
    boolean apply(X x, Y y);

    default PairPredicate<X, Y> negate() {
        return (x, y) -> !apply(x, y);
    }

    default PairPredicate<X, Y> and(PairPredicate<? super X, ? super Y> other) {
        Objects.requireNonNull(other);
        return (x, y) -> this.apply(x, y) && other.apply(x, y);
    }

    default PairPredicate<X, Y> or(PairPredicate<? super X, ? super Y> other) {
        Objects.requireNonNull(other);
        return (x, y) -> this.apply(x, y) || other.apply(x, y);
    }

    static <X, Y> PairPredicate<X, Y> not( PairPredicate<X, Y> target) {
        Objects.requireNonNull(target);
        return target.negate();
    }
}
