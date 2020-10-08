package ind.ck.concurrent.future;

public interface TaskService<T, P> {

    /**
     * result no need
     * @param r
     * @return
     */
    Future<?> submit(Runnable r);


    /**
     * result needed
     * @param t
     * @param param
     * @return
     */
    Future<?> submit(Task<T,P> t, P param);

}
