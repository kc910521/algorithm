package ind.ck.concurrent.future;

public class TaskServiceImpl<T, P> implements TaskService <T, P> {

    @Override
    public Future<?> submit(Runnable r) {
        final FutureTask<Void> ft = new FutureTask<>();
        new Thread(() -> {
            r.run();
        }, Thread.currentThread().getName()).start();
        return ft;
    }

    @Override
    public Future<?> submit(Task<T,P> task, P param) {
        final FutureTask<T> ft = new FutureTask<>();
        new Thread(() -> {
            T taskResult = task.doTask(param);
            ft.finish(taskResult);
        }, Thread.currentThread().getName()).start();
        return ft;
    }
}
