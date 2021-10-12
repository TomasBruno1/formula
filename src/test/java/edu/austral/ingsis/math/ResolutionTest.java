package edu.austral.ingsis.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldResolveSimpleFunction1() {
        Function function = new Expression(new Variable(1), new Variable(6), Operand.SUM);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(7d));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldResolveSimpleFunction2() {
        Function function = new Expression(new Variable(12), new Variable(2), Operand.DIV);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(6d));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldResolveSimpleFunction3() {
        Function function = new Expression(new Expression(new Variable(9), new Variable(2), Operand.DIV), new Variable(3), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(13.5d));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldResolveSimpleFunction4() {
        Function function = new Expression(new Expression(new Variable(27), new Variable(6), Operand.DIV), new Variable(2), Operand.POW);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(20.25d));
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    public void shouldResolveSimpleFunction5() {
        Function function = new Expression(new Variable(36), new Expression(new Variable(1), new Variable(2), Operand.DIV), Operand.POW);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |136|
     */
    @Test
    public void shouldResolveSimpleFunction6() {
        Function function = new Variable(136, Operand.ABS);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(136d));
    }

    /**
     * Case |-136|
     */
    @Test
    public void shouldResolveSimpleFunction7() {
        Function function = new Variable(-136, Operand.ABS);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(136d));
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    public void shouldResolveSimpleFunction8() {
        Function function = new Expression(new Expression(new Variable(5), new Variable(5), Operand.SUB), new Variable(8), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(0d));
    }

    /**
     * Case (sqrt(10 - 1)) * 5
     */
    @Test
    public void shouldResolveSimpleFunction9() {
        Function function = new Expression(new Expression(new Variable(10), new Variable(1), Operand.SUB, Operand.SQRT), new Variable(5), Operand.MUL);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(15d));
    }

    /**
     * Case ((5 * 3) - 5) / 2
     */
    @Test
    public void shouldResolveSimpleFunction10() {
        Function function = new Expression(new Expression(new Expression(new Variable(5), new Variable(3), Operand.MUL), new Variable(5), Operand.SUB), new Variable(2), Operand.DIV);

        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(5d));
    }
}
