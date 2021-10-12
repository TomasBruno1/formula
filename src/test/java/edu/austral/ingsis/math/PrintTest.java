package edu.austral.ingsis.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrintTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldPrintFunction1() {
        Function function = new Expression(new Variable(1), new Variable(6), Operand.SUM);

        final String expected = "1 + 6";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldPrintFunction2() {
        Function function = new Expression(new Variable(12), new Variable(2), Operand.DIV);

        final String expected = "12 / 2";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldPrintFunction3() {
        Function function = new Expression(new Expression(new Variable(9), new Variable(2), Operand.DIV), new Variable(3), Operand.MUL);

        final String expected = "(9 / 2) * 3";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldPrintFunction4() {
        Function function = new Expression(new Expression(new Variable(27), new Variable(6), Operand.DIV), new Variable(2), Operand.POW);

        final String expected = "(27 / 6) ^ 2";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction6() {
        Function function = new Expression(new Variable("value", Double.NaN, Operand.ABS), new Variable(8), Operand.SUB);

        final String expected = "|value| - 8";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction7() {
        Function function = new Expression(new Variable("value", Double.NaN, Operand.ABS), new Variable(8), Operand.SUB);

        final String expected = "|value| - 8";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldPrintFunction8() {
        Function function = new Expression(new Expression(new Variable(5), new Variable("i"), Operand.SUB), new Variable(8), Operand.MUL);

        final String expected = "(5 - i) * 8";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (sqrt(x - 1)) * 5
     */
    @Test
    public void shouldResolveSimpleFunction9() {
        Function function = new Expression(new Expression(new Variable("x"), new Variable(1), Operand.SUB, Operand.SQRT), new Variable(5), Operand.MUL);

        final String expected = "(sqrt(x - 1)) * 5";

        PrintVisitor printer = new PrintVisitor();
        function.accept(printer);
        final String result = printer.result;

        assertThat(result, equalTo(expected));
    }
}
