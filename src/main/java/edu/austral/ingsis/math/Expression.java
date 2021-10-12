package edu.austral.ingsis.math;

import edu.austral.ingsis.math.exception.InvalidOperandException;

import java.util.ArrayList;
import java.util.List;

public class Expression implements Function{

    private final Function left;
    private final Function right;
    private final Operand operand;
    private final Operand altOperand;

    public Expression(Function left, Function right, Operand operand) {
        this.left = left;
        this.right = right;
        this.operand = operand;
        this.altOperand = null;
    }

    public Expression(Function left, Function right, Operand operand, Operand altOperand) {
        this.left = left;
        this.right = right;
        this.operand = operand;
        this.altOperand = altOperand;
    }

    @Override
    public Double solve() {
        double result;

        switch (operand) {
            case SUM:
                result = left.solve() + right.solve();
                break;
            case SUB:
                result = left.solve() - right.solve();
                break;
            case MUL:
                result = left.solve() * right.solve();
                break;
            case DIV:
                result = left.solve() / right.solve();
                break;
            case POW:
                result = Math.pow(left.solve(), right.solve());
                break;
            default:
                throw new InvalidOperandException("That operand is not valid.");
        }

        if(altOperand == null) return result;

        switch (altOperand) {
            case SQRT:
                result = Math.sqrt(result);
                break;
            case ABS:
                result = Math.abs(result);
                break;
            default:
                throw new InvalidOperandException("That operand is not valid as an altOperand.");
        }

        return result;
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();

        if(altOperand != null){
            switch (altOperand){
                case ABS:
                    builder.append(altOperand.getString());
                    break;
                case SQRT:
                    builder.append(altOperand.getString()).append('(');
                    break;
            }
        }

        if(left.isComposite()) {
            builder.append('(');
        }
        builder.append(left.print());
        if(left.isComposite()) builder.append(')');

        builder.append(' ').append(operand.getString()).append(' ');

        if(right.isComposite()) builder.append('(');
        builder.append(right.print());
        if(right.isComposite()) builder.append(')');

        if(altOperand != null){
            switch (altOperand){
                case ABS:
                    builder.append(altOperand.getString());
                    break;
                case SQRT:
                    builder.append(')');
                    break;
            }
        }

        return builder.toString();
    }

    @Override
    public List<String> getVariableNames() {
        List<String> result = new ArrayList<>();
        left.addVariableNames(result);
        right.addVariableNames(result);
        return result;
    }

    @Override
    public void addVariableNames(List<String> result) {
        left.addVariableNames(result);
        right.addVariableNames(result);
    }

    @Override
    public boolean isComposite() {
        return true;
    }
}
