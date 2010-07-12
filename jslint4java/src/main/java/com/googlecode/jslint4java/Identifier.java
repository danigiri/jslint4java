package com.googlecode.jslint4java;

/**
 * A JavaScript identifer, and it's location.
 *
 * @author hdm
 *
 */
public class Identifier {

    private final int line;
    private final String name;

    public Identifier(String name, int line) {
        super();
        this.name = name;
        this.line = line;
    }

    /** The line that the identifier occurs on. */
    public int getLine() {
        return line;
    }

    /** The name of the identifier. */
    public String getName() {
        return name;
    }

}