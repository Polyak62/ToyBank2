package entity;


import java.util.concurrent.ExecutionException;

public class HandlerRequest implements Runnable {
    private FrontSystem frontSystem;
    private BackSystem backSystem;
    private String handlerName;

    public HandlerRequest(FrontSystem frontSystem, BackSystem backSystem,String handlerName) {
        this.frontSystem = frontSystem;
        this.backSystem = backSystem;
        this.handlerName = handlerName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    // 2 потока Handler остаются висеть в состоянии wait.
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Request request = frontSystem.getRequest();
            if (request != null){
                try {
                    backSystem.handlerRequest(request);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getHandlerName() + " обрабатывает заявку "+ request);
            }
        }
    }

}
