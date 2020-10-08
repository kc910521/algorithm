package ind.ck.concurrent.future;

public class FutureTask<T> implements Future<T> {

    private T result;

    private boolean isDone = false;

    private final Object LOCK = new Object();


    @Override
    public T get() {
        synchronized (LOCK) {
            while (!isDone) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void finish(T result) {
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
