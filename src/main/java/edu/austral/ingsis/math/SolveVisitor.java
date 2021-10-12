package edu.austral.ingsis.math;

import edu.austral.ingsis.math.exception.InvalidOperandException;

public class SolveVisitor implements FunctionVisitor {

    Double result;

    @Override
    public void visitVariable(Variable variable) {
        double value = variable.getValue();
        Operand altOperand = variable.getAltOperand();

        if(altOperand == null) {
            result = value;
            return;
        }

        switch (altOperand){
            case ABS:
                result = Math.abs(value);
                return;
            case SQRT:
                result = Math.sqrt(value);
                return;
        }

        result = value;
    }

    @Override
    public void visitExpression(Expression expression) {
        Operand operand = expression.getOperand();
        Operand altOperand = expression.getAltOperand();
        Function left = expression.getLeft();
        Function right = expression.getRight();

        double result;

        switch (operand) {
            case SUM:
                result = getResult(left) + getResult(right);
                break;
            case SUB:
                result = getResult(left) - getResult(right);
                break;
            case MUL:
                result = getResult(left) * getResult(right);
                break;
            case DIV:
                result = getResult(left) / getResult(right);
                break;
            case POW:
                result = Math.pow(getResult(left), getResult(right));
                break;
            default:
                throw new InvalidOperandException("That operand is not valid.");
        }

        if(altOperand == null) {
            this.result = result;
            return;
        }

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

        this.result = result;
    }

    private double getResult(Function function) {
        function.accept(this);
        return result;
    }
}
