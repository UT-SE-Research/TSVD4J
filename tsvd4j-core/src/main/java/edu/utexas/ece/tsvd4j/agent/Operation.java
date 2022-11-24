package edu.utexas.ece.tsvd4j.agent;

public enum Operation {

    WRITE(0), READ(1);

    private int value;

    Operation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
