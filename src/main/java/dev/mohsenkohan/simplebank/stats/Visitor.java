package dev.mohsenkohan.simplebank.stats;

import java.util.function.Consumer;

public interface Visitor<T, R> extends Consumer<T> {

    R result();
}
