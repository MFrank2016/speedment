package com.speedment.runtime.join;

import com.speedment.common.function.QuadFunction;
import com.speedment.common.function.TriFunction;
import com.speedment.common.injector.annotation.InjectKey;
import com.speedment.common.tuple.Tuple;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.field.trait.HasComparableOperators;
import com.speedment.runtime.join.trait.HasDefaultBuild;
import com.speedment.runtime.join.trait.HasJoins;
import com.speedment.runtime.join.trait.HasOnPredicates;
import com.speedment.runtime.join.trait.HasWhere;
import java.util.function.BiFunction;
import com.speedment.common.function.Function5;
import com.speedment.common.function.Function6;
import com.speedment.common.tuple.TuplesOfNullables;
import com.speedment.common.tuple.nullable.Tuple2OfNullables;
import com.speedment.common.tuple.nullable.Tuple3OfNullables;
import com.speedment.common.tuple.nullable.Tuple4OfNullables;
import com.speedment.common.tuple.nullable.Tuple5OfNullables;
import com.speedment.common.tuple.nullable.Tuple6OfNullables;

/**
 * a JoinComponent can be used to create builders for creating Join objects.
 * Join objects, in turn, can be used to stream over joined tables.
 * <p>
 * It is unspecified if objects returned by builder-type of objects return
 * itself or a new builder. Users should make no assumption of either case and
 * use a fluent API style of programming.
 * <p>
 * All methods that takes objects will throw a {@code NullPointerException } if
 * provided with one or several {@code null } values.
 * <p>
 * Intermediate builder methods are not thread safe.
 * <p>
 * Currently, this component can build joins of grade 2, 3, 4, 5 or 6
 *
 * @author Per Minborg
 */
@InjectKey(JoinComponent.class)
public interface JoinComponent {

    /**
     * The maximum number of tables than can be joined.
     */
    int MAX_DEGREE = 6;
    
    /**
     * Adds a provided {@code firstManager} to the collection of joined
     * managers. Rows are joined from the provided {@code firstManager}
     * depending on how subsequent managers are added to the builder.
     *
     * @param <T0> type of entities for the first manager
     * @param firstManager to use
     * @return a builder where the provided {@code firstManager} is added
     *
     * @throws NullPointerException if the provided {@code firstManager} is
     * {@code null}
     */
    <T0> JoinBuilder1<T0> from(TableIdentifier<T0> firstManager);

    interface JoinBuilder1<T0> extends
        HasJoins<JoinBuilder1.AfterJoin<T0, ?>, JoinBuilder1.JoinBuilder2<T0, ?>>,
        HasWhere<T0, JoinBuilder1<T0>> {

        @Override
        <T1> AfterJoin<T0, T1> innerJoinOn(HasComparableOperators<T1, ?> joinedField);

        @Override
        <T1> AfterJoin<T0, T1> leftJoinOn(HasComparableOperators<T1, ?> joinedField);

        @Override
        <T1> AfterJoin<T0, T1> rightJoinOn(HasComparableOperators<T1, ?> joinedField);

//        @Override
//        <T1> AfterJoin<T0, T1> fullOuterJoinOn(HasComparableOperators<T1, ?> joinedField);

        @Override
        <T1> JoinBuilder2<T0, T1> crossJoin(TableIdentifier<T1> joinedTable);

        interface AfterJoin<T0, T1> extends
            HasOnPredicates<JoinBuilder2<T0, T1>> {

        }

        interface JoinBuilder2<T0, T1> extends
            HasJoins<JoinBuilder2.AfterJoin<T0, T1, ?>, JoinBuilder2.JoinBuilder3<T0, T1, ?>>,
            HasWhere<T1, JoinBuilder2<T0, T1>>,
            HasDefaultBuild<Tuple2OfNullables<T0, T1>> {

            @Override
            <T2> AfterJoin<T0, T1, T2> innerJoinOn(HasComparableOperators<T2, ?> joinedField);

            @Override
            <T2> AfterJoin<T0, T1, T2> leftJoinOn(HasComparableOperators<T2, ?> joinedField);

            @Override
            <T2> AfterJoin<T0, T1, T2> rightJoinOn(HasComparableOperators<T2, ?> joinedField);

//            @Override
//            <T2> AfterJoin<T0, T1, T2> fullOuterJoinOn(HasComparableOperators<T2, ?> joinedField);

            @Override
            <T2> JoinBuilder3<T0, T1, T2> crossJoin(TableIdentifier<T2> joinedTable);

            @Override
            default Join<Tuple2OfNullables<T0, T1>> build() {
                return build(TuplesOfNullables::ofNullables);
            }

            /**
             * Creates and returns a new Join object where elements in the Join
             * object's stream method is created using the provided
             * {@code constructor}.
             *
             * @param <T> the type of element in the Join object's stream
             * method.
             * @param constructor to use to create stream elements.
             * @return a new Join object where elements in the Join object's
             * stream method is of a default {@link Tuple} type
             *
             * @throws NullPointerException if the provided {@code constructor }
             * is {@code null}
             */
            <T> Join<T> build(BiFunction<T0, T1, T> constructor);

            interface AfterJoin<T0, T1, T2> extends
                HasOnPredicates<JoinBuilder3<T0, T1, T2>> {

            }

            interface JoinBuilder3<T0, T1, T2> extends
                HasJoins<JoinBuilder3.AfterJoin<T0, T1, T2, ?>, JoinBuilder3.JoinBuilder4<T0, T1, T2, ?>>,
                HasWhere<T2, JoinBuilder3<T0, T1, T2>>,
                HasDefaultBuild<Tuple3OfNullables<T0, T1, T2>> {

                @Override
                <T3> AfterJoin<T0, T1, T2, T3> innerJoinOn(HasComparableOperators<T3, ?> joinedField);

                @Override
                <T3> AfterJoin<T0, T1, T2, T3> leftJoinOn(HasComparableOperators<T3, ?> joinedField);

                @Override
                <T3> AfterJoin<T0, T1, T2, T3> rightJoinOn(HasComparableOperators<T3, ?> joinedField);

//                @Override
//                <T3> AfterJoin<T0, T1, T2, T3> fullOuterJoinOn(HasComparableOperators<T3, ?> joinedField);

                @Override
                <T3> JoinBuilder4<T0, T1, T2, T3> crossJoin(TableIdentifier<T3> joinedTable);

                @Override
                default Join<Tuple3OfNullables<T0, T1, T2>> build() {
                    return build(TuplesOfNullables::ofNullables);
                }

                /**
                 * Creates and returns a new Join object where elements in the
                 * Join object's stream method is created using the provided
                 * {@code constructor}.
                 *
                 * @param <T> the type of element in the Join object's stream
                 * method.
                 * @param constructor to use to create stream elements.
                 * @return a new Join object where elements in the Join object's
                 * stream method is of a default {@link Tuple} type
                 *
                 * @throws NullPointerException if the provided {@code constructor
                 * } is {@code null}
                 */
                <T> Join<T> build(TriFunction<T0, T1, T2, T> constructor);

                interface AfterJoin<T0, T1, T2, T3> extends
                    HasOnPredicates<JoinBuilder4<T0, T1, T2, T3>> {

                }

                interface JoinBuilder4<T0, T1, T2, T3> extends
                    HasJoins<JoinBuilder4.AfterJoin<T0, T1, T2, T3, ?>, JoinBuilder4.JoinBuilder5<T0, T1, T2, T3, ?>>,
                    HasWhere<T3, JoinBuilder4<T0, T1, T2, T3>>,
                    HasDefaultBuild<Tuple4OfNullables<T0, T1, T2, T3>> {

                    @Override
                    <T4> AfterJoin<T0, T1, T2, T3, T4> innerJoinOn(HasComparableOperators<T4, ?> joinedField);

                    @Override
                    <T4> AfterJoin<T0, T1, T2, T3, T4> leftJoinOn(HasComparableOperators<T4, ?> joinedField);

                    @Override
                    <T4> AfterJoin<T0, T1, T2, T3, T4> rightJoinOn(HasComparableOperators<T4, ?> joinedField);

//                    @Override
//                    <T4> AfterJoin<T0, T1, T2, T3, T4> fullOuterJoinOn(HasComparableOperators<T4, ?> joinedField);

                    @Override
                    <T4> JoinBuilder5<T0, T1, T2, T3, T4> crossJoin(TableIdentifier<T4> joinedTable);

                    @Override
                    default Join<Tuple4OfNullables<T0, T1, T2, T3>> build() {
                        return build(TuplesOfNullables::ofNullables);
                    }

                    /**
                     * Creates and returns a new Join object where elements in
                     * the Join object's stream method is created using the
                     * provided {@code constructor}.
                     *
                     * @param <T> the type of element in the Join object's
                     * stream method.
                     * @param constructor to use to create stream elements.
                     * @return a new Join object where elements in the Join
                     * object's stream method is of a default {@link Tuple} type
                     *
                     * @throws NullPointerException if the provided {@code constructor
                     * } is {@code null}
                     * @throws IllegalStateException if fields that are added
                     * via the {@code on()
                     * } method refers to tables that are not a part of the
                     * join.
                     */
                    <T> Join<T> build(QuadFunction<T0, T1, T2, T3, T> constructor);

                    interface AfterJoin<T0, T1, T2, T3, T4> extends
                        HasOnPredicates<JoinBuilder5<T0, T1, T2, T3, T4>> {

                    }

                    interface JoinBuilder5<T0, T1, T2, T3, T4> extends
                        HasJoins<JoinBuilder5.AfterJoin<T0, T1, T2, T3, T4, ?>, JoinBuilder5.JoinBuilder6<T0, T1, T2, T3, T4, ?>>,
                        HasWhere<T4, JoinBuilder5<T0, T1, T2, T3, T4>>,
                        HasDefaultBuild<Tuple5OfNullables<T0, T1, T2, T3, T4>> {

                        @Override
                        <T5> AfterJoin<T0, T1, T2, T3, T4, T5> innerJoinOn(HasComparableOperators<T5, ?> joinedField);

                        @Override
                        <T5> AfterJoin<T0, T1, T2, T3, T4, T5> leftJoinOn(HasComparableOperators<T5, ?> joinedField);

                        @Override
                        <T5> AfterJoin<T0, T1, T2, T3, T4, T5> rightJoinOn(HasComparableOperators<T5, ?> joinedField);

//                        @Override
//                        <T5> AfterJoin<T0, T1, T2, T3, T4, T5> fullOuterJoinOn(HasComparableOperators<T5, ?> joinedField);

                        @Override
                        <T5> JoinBuilder6<T0, T1, T2, T3, T4, T5> crossJoin(TableIdentifier<T5> joinedTable);

                        @Override
                        default Join<Tuple5OfNullables<T0, T1, T2, T3, T4>> build() {
                            return build(TuplesOfNullables::ofNullables);
                        }

                        /**
                         * Creates and returns a new Join object where elements
                         * in the Join object's stream method is created using
                         * the provided {@code constructor}.
                         *
                         * @param <T> the type of element in the Join object's
                         * stream method.
                         * @param constructor to use to create stream elements.
                         * @return a new Join object where elements in the Join
                         * object's stream method is of a default {@link Tuple}
                         * type
                         *
                         * @throws NullPointerException if the provided {@code constructor
                         * } is {@code null}
                         * @throws IllegalStateException if fields that are
                         * added via the {@code on()
                         * } method refers to tables that are not a part of the
                         * join.
                         */
                        <T> Join<T> build(Function5<T0, T1, T2, T3, T4, T> constructor);

                        interface AfterJoin<T0, T1, T2, T3, T4, T5> extends
                            HasOnPredicates<JoinBuilder6<T0, T1, T2, T3, T4, T5>> {

                        }

                        interface JoinBuilder6<T0, T1, T2, T3, T4, T5> extends
                            HasWhere<T5, JoinBuilder6<T0, T1, T2, T3, T4, T5>>,
                            HasDefaultBuild<Tuple6OfNullables<T0, T1, T2, T3, T4, T5>> {

                            @Override
                            default Join<Tuple6OfNullables<T0, T1, T2, T3, T4, T5>> build() {
                                return build(TuplesOfNullables::ofNullables);
                            }

                            /**
                             * Creates and returns a new Join object where
                             * elements in the Join object's stream method is
                             * created using the provided {@code constructor}.
                             *
                             * @param <T> the type of element in the Join
                             * object's stream method.
                             * @param constructor to use to create stream
                             * elements.
                             * @return a new Join object where elements in the
                             * Join object's stream method is of a default
                             * {@link Tuple} type
                             *
                             * @throws NullPointerException if the provided {@code constructor
                             * } is {@code null}
                             * @throws IllegalStateException if fields that are
                             * added via the {@code on()
                             * } method refers to tables that are not a part of
                             * the join.
                             */
                            <T> Join<T> build(Function6<T0, T1, T2, T3, T4, T5, T> constructor);

                        }

                    }

                }

            }

        }

    }

}
