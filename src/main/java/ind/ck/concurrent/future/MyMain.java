package ind.ck.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class MyMain {


    public static void main(String[] args) {
        TaskService<String, String> taskService = new TaskServiceImpl<>();
        final AtomicInteger a = new AtomicInteger(0);

        taskService.submit(new Task<String, String>() {
            @Override
            public String doTask(String param) {
                try {
                    Thread.sleep(1200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return param + "_" + a.addAndGet(1);
            }
        }, "a");

        System.out.println(a.get());
        CompletableFuture.allOf().join();



        //

    }

}
