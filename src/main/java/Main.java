import entity.*;
import service.SberbankService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FrontSystem frontSystem = new FrontSystem();
        SberbankService sbs1 = new SberbankService(1000);
        SberbankService sbs2 = new SberbankService(2000);
        SberbankService sbs3 = new SberbankService(3000);
        List <SberbankService> list = new ArrayList<>();
        list.add(sbs1);
        list.add(sbs2);
        list.add(sbs3);
        BackSystem backSystem = new BackSystem(list);


        SberbankService sbs = new SberbankService(1000);

        ExecutorService serviceClient = Executors.newFixedThreadPool(5);
        serviceClient.submit(new Client("Client1",new Request(100, TypeRequest.CREDIT,"Заявка№1"),frontSystem));
        serviceClient.submit(new Client("Client1",new Request(200, TypeRequest.REPAYMENT,"Заявка№2"),frontSystem));
        serviceClient.submit(new Client("Client1",new Request(300, TypeRequest.CREDIT,"Заявка№3"),frontSystem));
        serviceClient.submit(new Client("Client1",new Request(400, TypeRequest.REPAYMENT,"Заявка№4"),frontSystem));
        serviceClient.submit(new Client("Client1",new Request(500, TypeRequest.CREDIT,"Заявка№5"),frontSystem));
        serviceClient.shutdown();
        ExecutorService serviceHandler = Executors.newFixedThreadPool(2);
        serviceHandler.submit(new HandlerRequest(frontSystem,backSystem,"Обработчик 1"));
        serviceHandler.submit(new HandlerRequest(frontSystem,backSystem,"Обработчик 2"));
        serviceHandler.shutdown();
    }
}
