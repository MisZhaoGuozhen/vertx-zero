package io.vertx.up.exception;

public class PluginOptionException extends WebException {

    public PluginOptionException(final Class<?> clazz,
                                 final String name) {
        super(clazz, name);
    }

    @Override
    public int getCode() {
        return -40015;
    }
}
