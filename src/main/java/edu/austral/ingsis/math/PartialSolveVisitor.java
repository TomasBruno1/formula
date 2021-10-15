package edu.austral.ingsis.math;

import edu.austral.ingsis.math.exception.InvalidOperandException;

public class PartialSolveVisitor implements FunctionVisitor {
    Function result;

    @Override
    public void visitVariable(Variable variable) {
        Double value = variable.getValue();
        Operand altOperand = variable.getAltOperand();

        if(value.isNaN()) {
            result = new Variable(variable.name, value, altOperand);
            return;
        }

        if(altOperand == null) {
            result = new Variable(variable.name, value, altOperand);
            return;
        }

        switch (altOperand){
            case ABS:
                result = new Variable(variable.name, Math.abs(value), altOperand);
                return;
            case SQRT:
                result = new Variable(variable.name, Math.sqrt(value), altOperand);
                return;
        }

        result = new Variable(variable.name, value, altOperand);
    }

    @Override
    public void visitExpression(Expression expression) {
        Operand operand = expression.getOperand();
        Operand altOperand = expression.getAltOperand();
        Function left = expression.getLeft();
        Function right = expression.getRight();

        Function result;

        if(!left.isComposite() && !right.isComposite()) {
            Variable leftVar = (Variable) left;
            Variable rightVar = (Variable) right;
            double value;
            if(!leftVar.value.isNaN() && !rightVar.value.isNaN()) {
                switch (operand) {
                    case SUM:
                        value = leftVar.value + rightVar.value;
                        result = new Variable(value, altOperand);
                        break;
                    case SUB:
                        value = leftVar.value - rightVar.value;
                        result = new Variable(value, altOperand);
                        break;
                    case MUL:
                        value = leftVar.value * rightVar.value;
                        result = new Variable(value, altOperand);
                        break;
                    case DIV:
                        value = leftVar.value / rightVar.value;
                        result = new Variable(value, altOperand);
                        break;
                    case POW:
                        value = Math.pow(leftVar.value, rightVar.value);
                        result = new Variable(value, altOperand);
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
                        this.result = new Variable(Math.sqrt(value));
                        break;
                    case ABS:
                        this.result = new Variable(Math.abs(value));
                        break;
                    default:
                        throw new InvalidOperandException("That operand is not valid as an altOperand.");
                }
            } else {
                left.accept(this);
                right.accept(this);
                this.result = new Expression(left, right, operand, altOperand);
            }
        } else {
            left.accept(this);
            Function leftResult = this.result;
            right.accept(this);
            Function rightResult = this.result;
            this.result = new Expression(leftResult, rightResult, operand, altOperand);
            if(!leftResult.isComposite() && !rightResult.isComposite()) {
                Variable leftVar = (Variable) leftResult;
                Variable rightVar = (Variable) rightResult;
                if(!leftVar.value.isNaN() && !rightVar.value.isNaN()) {
                    this.result.accept(this);
                }
            }
        }
    }
}
