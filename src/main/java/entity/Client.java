package entity;

public class Client implements Runnable {

    private String clientName;
    private int money;
    private TypeRequest typeRequest;
    private FrontSystem frontSystem;
    private Request request;

    public Client(String clientName, Request request,FrontSystem frontSystem) {
        this.clientName = clientName;
        this.request = request;
        this.frontSystem = frontSystem;
    }

    public String getClientName() {
        return clientName;
    }


    public void run() {
        System.out.println("Клиент "+getClientName() + " подал заявку "+ request.toString());
        frontSystem.addRequest(request);
        System.out.println(Thread.currentThread().getName() + " Завершал свою работу");

    }
}
