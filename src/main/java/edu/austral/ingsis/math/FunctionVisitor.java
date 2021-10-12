package edu.austral.ingsis.math;

public interface FunctionVisitor {
    void visitVariable(Variable variable);
    void visitExpression(Expression expression);
}
