package edu.austral.ingsis.math;

public interface Function {

    boolean isComposite();

    void accept(FunctionVisitor visitor);

}
