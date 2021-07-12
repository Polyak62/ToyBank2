package entity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//Используя очередь ArrayBlockingQueue мы ограничиваем количество потоков, которые одновременно работают с нашим методом addRequest, что
//позволяет нам не использовать Semaphore
public class FrontSystem {
    private BlockingQueue<Request> blockingQueueRequest = new ArrayBlockingQueue<>(2,true);


    public void addRequest(Request request) {
        try {
            blockingQueueRequest.put(request);
            System.out.println("Обрабатывается банком заявка " + request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public Request getRequest() {
        Request req = null;
        try {
            req = (Request) blockingQueueRequest.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return req;

    }
}
