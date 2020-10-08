package ind.ck.concurrent.future;

/**
 *
 */
public interface Task<T,P> {
    T doTask(P param);
}
