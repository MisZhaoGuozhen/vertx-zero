package com.vie.fun.error;

@FunctionalInterface
public interface JeSupplier<T> {

    T get() throws Exception;
}