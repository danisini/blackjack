package controller;

@FunctionalInterface
public interface RequestHandler<T> {
    void execute(T request);
}
