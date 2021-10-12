package edu.austral.ingsis.math;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Variable implements Function {

    final String name;
    Double value;

    final Operand altOperand;

    public Variable(double value) {
        this.name = null;
        this.value = value;
        this.altOperand = null;
    }

    public Variable(String name) {
        this.name = name;
        this.value = Double.NaN;
        this.altOperand = null;
    }

    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
        this.altOperand = null;
    }

    public Variable(String name, double value, Operand altOperand) {
        this.name = name;
        this.value = value;
        this.altOperand = altOperand;
    }

    public Variable(double value, Operand altOperand) {
        this.name = null;
        this.value = value;
        this.altOperand = altOperand;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Operand getAltOperand() {
        return altOperand;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void accept(FunctionVisitor visitor) {
        visitor.visitVariable(this);
    }
}
