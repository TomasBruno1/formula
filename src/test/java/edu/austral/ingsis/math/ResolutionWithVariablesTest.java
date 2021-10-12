package edu.austral.ingsis.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionWithVariablesTest {

    /**
     * Case 1 + x where x = 3
     */
    @Test
    public void shouldResolveFunction1() {
        Function function = new Expression(new Variable(1), new Variable("x", 3), Operand.SUM);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(4d));
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    public void shouldResolveFunction2() {
        Function function = new Expression(new Variable(12), new Variable("div", 4), Operand.DIV);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(3d));
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    public void shouldResolveFunction3() {
        Function function = new Expression(new Expression(new Variable(9), new Variable("x", 3), Operand.DIV), new Variable("y", 4), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(12d));
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    public void shouldResolveFunction4() {
        Function function = new Expression(new Expression(new Variable(27), new Variable("a", 9), Operand.DIV), new Variable("b", 3), Operand.POW);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(27d));
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    public void shouldResolveFunction5() {
        Function function = new Expression(new Variable("z", 36), new Expression(new Variable(1), new Variable(2), Operand.DIV), Operand.POW);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    public void shouldResolveFunction6() {
        Function function = new Expression(new Variable("value", 8, Operand.ABS), new Variable(8), Operand.SUB);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(0d));
    }

    /**
     * Case |value| - 8 where value = -8
     */
    @Test
    public void shouldResolveFunction7() {
        Function function = new Expression(new Variable("value", -8, Operand.ABS), new Variable(8), Operand.SUB);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(0d));
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    public void shouldResolveFunction8() {
        Function function = new Expression(new Expression(new Variable(5), new Variable("i", 2), Operand.SUB), new Variable(8), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(24d));
    }

    /**
     * Case (sqrt(x - 1)) * 5 where x = 10
     */
    @Test
    public void shouldResolveSimpleFunction9() {
        Function function = new Expression(new Expression(new Variable("x", 10), new Variable(1), Operand.SUB, Operand.SQRT), new Variable(5), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(15d));
    }
}
