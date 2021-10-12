package edu.austral.ingsis.math;

import java.util.ArrayList;
import java.util.List;

public class VariableVisitor implements FunctionVisitor {
    List<String> result = new ArrayList<>();

    @Override
    public void visitVariable(Variable variable) {
        if(variable.getName() != null) result.add(variable.getName());
    }

    @Override
    public void visitExpression(Expression expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }
}
