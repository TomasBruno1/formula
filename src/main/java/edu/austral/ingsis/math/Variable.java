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

    @Override
    public Double solve() {
        if(altOperand == null) return value;

        switch (altOperand){
            case ABS:
                return Math.abs(value);
            case SQRT:
                return Math.sqrt(value);
        }

        return value;
    }

    @Override
    public String print() {
        if(value.isNaN()) {
            if(altOperand == Operand.SQRT) return String.format("%s(%s)", altOperand.getString(), name);
            if(altOperand == Operand.ABS) return String.format("%s%s%s", altOperand.getString(), name, altOperand.getString());
            return name;
        }
        if(altOperand == Operand.SQRT) return String.format("%s(%s)", altOperand.getString(), new DecimalFormat("#.##").format(value));
        if(altOperand == Operand.ABS) return String.format("%s%s%s", altOperand.getString(), new DecimalFormat("#.##").format(value), altOperand.getString());
        return new DecimalFormat("#.##").format(value);
    }

    @Override
    public List<String> getVariableNames() {
        List<String> result = new ArrayList<>();
        if(name != null) result.add(name);
        return result;
    }

    @Override
    public void addVariableNames(List<String> result) {
        if(name != null) result.add(name);
    }

    @Override
    public boolean isComposite() {
        return false;
    }
}
