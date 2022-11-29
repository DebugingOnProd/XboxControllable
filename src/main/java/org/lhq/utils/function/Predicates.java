package org.lhq.utils.function;


import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Predicates {
    private Predicates() {
        throw new UnsupportedOperationException("Predicates is a full static class!");
    }

    public static <T> Predicate<T> of(Predicate<T> predicate) {
        return predicate;
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> of(@NotNull PairPredicate<X, Y> predicate) {
        return predicate;
    }

    public static <T> @NotNull Predicate<T> alwaysTrue() {
        return x -> true;
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> pairAlwaysTrue() {
        return (x, y) -> true;
    }

    public static <T> @NotNull Predicate<T> alwaysFalse() {
        return x -> false;
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> pairAlwaysFalse() {
        return (x, y) -> false;
    }

    public static <T> @NotNull Predicate<T> not(@NotNull Predicate<T> predicate) {
        return predicate.negate();
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> not(@NotNull PairPredicate<X, Y> predicate) {
        return predicate.negate();
    }

    public static <T> @NotNull Predicate<T> and(@NotNull Predicate<T> a, @NotNull Predicate<T> b) {
        return a.and(b);
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> and(@NotNull PairPredicate<X, Y> a, @NotNull PairPredicate<X, Y> b) {
        return a.and(b);
    }

    public static <T> @NotNull Predicate<T> or(@NotNull Predicate<T> a, @NotNull Predicate<T> b) {
        return a.or(b);
    }

    public static <X, Y> @NotNull PairPredicate<X, Y> or(@NotNull PairPredicate<X, Y> a, @NotNull PairPredicate<X, Y> b) {
        return a.or(b);
    }

}
