package com.creating.www.parsers;

public interface Parser<I,O> {
public O parse(I args0);
public O parse();
}
