package edu.austral.ingsis.math;

import java.text.DecimalFormat;

public class PrintVisitor implements FunctionVisitor {

    String result;

    @Override
    public void visitVariable(Variable variable) {
        Double value = variable.getValue();
        Operand altOperand = variable.getAltOperand();
        String name = variable.getName();

        if(value.isNaN()) {
            if(altOperand == Operand.SQRT) {
                result = String.format("%s(%s)", altOperand.getString(), name);
                return;
            }
            if(altOperand == Operand.ABS) {
                result = String.format("%s%s%s", altOperand.getString(), name, altOperand.getString());
                return;
            }
            result = name;
            return;
        }
        if(altOperand == Operand.SQRT) {
            result = String.format("%s(%s)", altOperand.getString(), new DecimalFormat("#.##").format(value));
            return;
        }
        if(altOperand == Operand.ABS) {
            result = String.format("%s%s%s", altOperand.getString(), new DecimalFormat("#.##").format(value), altOperand.getString());
            return;
        }
        result = new DecimalFormat("#.##").format(value);
    }

    @Override
    public void visitExpression(Expression expression) {
        Operand altOperand = expression.getAltOperand();
        Operand operand = expression.getOperand();
        Function left = expression.getLeft();
        Function right = expression.getRight();

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
        builder.append(getString(left));
        if(left.isComposite()) builder.append(')');

        builder.append(' ').append(operand.getString()).append(' ');

        if(right.isComposite()) builder.append('(');
        builder.append(getString(right));
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

        result = builder.toString();
    }

    private String getString(Function function) {
        function.accept(this);
        return this.result;
    }
}
