package edu.austral.ingsis.math;

import java.util.List;

public interface Function {
    Double solve();

    String print();

    List<String> getVariableNames();

    void addVariableNames(List<String> result);

    boolean isComposite();
}
