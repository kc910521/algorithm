package ind.ck.concurrent.future;

public interface Future<T> {
    /**
     *
     * @return get result
     */
    T get();

    /**
     * task is finished or not
     * @return
     */
    boolean done();
}
