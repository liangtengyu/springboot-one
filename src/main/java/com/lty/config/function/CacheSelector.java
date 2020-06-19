package com.lty.config.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
