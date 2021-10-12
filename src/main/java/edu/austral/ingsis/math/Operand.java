package edu.austral.ingsis.math;

public enum Operand {
    SUM("+"), SUB("-"), MUL("*"), DIV("/"), POW("^"), SQRT("sqrt"), ABS("|");

    private String string;

    Operand(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
