package edu.austral.ingsis.math;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartialResolutionTest {
    /**
     * Case 1 + 6
     */
    @Test
    public void shouldPartiallyResolveFunction1() {
        Function function = new Expression(new Variable(1), new Variable(6), Operand.SUM);

        final String expected = "7";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 12 / div
     */
    @Test
    public void shouldPartiallyResolveFunction2() {
        Function function = new Expression(new Variable(12), new Variable("div"), Operand.DIV);

        final String expected = "12 / div";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    public void shouldPartiallyResolveFunction3() {
        Function function = new Expression(new Expression(new Variable(9), new Variable("x"), Operand.DIV), new Variable("y"), Operand.MUL);

        final String expected = "(9 / x) * y";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    public void shouldPartiallyResolveFunction4() {
        Function function = new Expression(new Expression(new Variable(27), new Variable("a"), Operand.DIV, null), new Variable("b"), Operand.POW);

        final String expected = "(27 / a) ^ b";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldPartiallyResolveFunction5() {
        Function function = new Expression(new Variable("z"), new Expression(new Variable(1), new Variable(2), Operand.DIV), Operand.POW);

        final String expected = "z ^ 0.5";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPartiallyResolveFunction6() {
        Function function = new Expression(new Variable("value", Double.NaN, Operand.ABS), new Variable(8), Operand.SUB);

        final String expected = "|value| - 8";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case ((5 * 3) - 5) / 2
     */
    @Test
    public void shouldPartiallyResolveFunction7() {
        Function function = new Expression(new Expression(new Expression(new Variable(5), new Variable(3), Operand.MUL), new Variable(5), Operand.SUB), new Variable(2), Operand.DIV);

        final String expected = "5";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldPartiallyResolveFunction8() {
        Function function = new Expression(new Expression(new Variable(5), new Variable("i"), Operand.SUB), new Variable(8), Operand.MUL);

        final String expected = "(5 - i) * 8";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / x) * (y + (5*2))
     */
    @Test
    public void shouldPartiallyResolveFunction9() {
        Function function = new Expression(new Expression(new Variable(9), new Variable("x"), Operand.DIV), new Expression(new Variable("y"), new Expression(new Variable(5), new Variable(2), Operand.MUL), Operand.SUM), Operand.MUL);

        final String expected = "(9 / x) * (y + 10)";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (27 / a) ^ b with a = 9
     */
    @Test
    public void shouldPartiallyResolveFunction10() {
        Function function = new Expression(new Expression(new Variable(27), new Variable("a", 9), Operand.DIV, null), new Variable("b"), Operand.POW);

        final String expected = "3 ^ b";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / x) * (y + (5*2))
     */
    @Test
    public void shouldPartiallyResolveFunction11() {
        Function function = new Expression(new Expression(new Variable(9), new Variable("x"), Operand.DIV), new Expression(new Variable("y", 1), new Expression(new Variable(5), new Variable(2), Operand.MUL), Operand.SUM), Operand.MUL);

        final String expected = "(9 / x) * 11";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (((5 * a) - 5) / 2) - b with a = 3
     */
    @Test
    public void shouldPartiallyResolveFunction12() {
        Function function = new Expression(new Expression(new Expression(new Expression(new Variable(5), new Variable("a", 3), Operand.MUL), new Variable(5), Operand.SUB), new Variable(2), Operand.DIV), new Variable("b"), Operand.SUB);

        final String expected = "5 - b";

        PartialSolveVisitor partialSolver = new PartialSolveVisitor();
        function.accept(partialSolver);

        final Function solvedFunction = partialSolver.result;
        PrintVisitor printer = new PrintVisitor();
        solvedFunction.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }
}
