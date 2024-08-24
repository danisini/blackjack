package action;

public abstract class BaseAction<T,P> {
    public abstract T doAction(P request);
}
