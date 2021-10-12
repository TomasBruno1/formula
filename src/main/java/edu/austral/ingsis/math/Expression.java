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
    public boolean isComposite() {
        return true;
    }

    @Override
    public void accept(FunctionVisitor visitor) {
        visitor.visitExpression(this);
    }

    public Function getLeft() {
        return left;
    }

    public Function getRight() {
        return right;
    }

    public Operand getOperand() {
        return operand;
    }

    public Operand getAltOperand() {
        return altOperand;
    }
}
