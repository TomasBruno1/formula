package edu.austral.ingsis.math;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListVariablesTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldListVariablesFunction1() {
        Function function = new Expression(new Variable(1), new Variable(6), Operand.SUM);

        final List<String> result = function.getVariableNames();

        assertThat(result, empty());
    }

    /**
     * Case 12 / div
     */
    @Test
    public void shouldListVariablesFunction2() {
        Function function = new Expression(new Variable(12), new Variable("div"), Operand.DIV);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("div"));
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    public void shouldListVariablesFunction3() {
        Function function = new Expression(new Expression(new Variable(9), new Variable("x"), Operand.DIV), new Variable("y"), Operand.MUL);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("x", "y"));
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    public void shouldListVariablesFunction4() {
        Function function = new Expression(new Expression(new Variable(27), new Variable("a"), Operand.DIV, null), new Variable("b"), Operand.POW);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("a", "b"));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldListVariablesFunction5() {
        Function function = new Expression(new Variable("z"), new Expression(new Variable(1), new Variable(2), Operand.DIV), Operand.POW);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("z"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction6() {
        Function function = new Expression(new Variable("value", 8, Operand.ABS), new Variable(8), Operand.SUB);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction7() {
        Function function = new Expression(new Variable("value", 8, Operand.ABS), new Variable(8), Operand.SUB);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldListVariablesFunction8() {
        Function function = new Expression(new Expression(new Variable(5), new Variable("i"), Operand.SUB), new Variable(8), Operand.MUL);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("i"));
    }

    /**
     * Case (sqrt(x - 1)) * y
     */
    @Test
    public void shouldResolveSimpleFunction9() {
        Function function = new Expression(new Expression(new Variable("x"), new Variable(1), Operand.SUB, Operand.SQRT), new Variable("y"), Operand.MUL);

        final List<String> result = function.getVariableNames();

        assertThat(result, containsInAnyOrder("x", "y"));
    }
}
