package entity;

import service.SberbankService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BackSystem {
    private AtomicInteger balanceBank;
    private final Lock lock = new ReentrantLock();
    List <SberbankService> listSbS;

    public BackSystem(List<SberbankService> listSbS) throws ExecutionException, InterruptedException {
        this.listSbS = listSbS;
        {
            try {
                balanceBank = new AtomicInteger(getBalanceBank());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("На данный момент баланс банка равен: " + getBalanceBank());
    }

    public AtomicInteger getAtomicBalanceBank() {
        return balanceBank;
    }

    private Integer getBalanceBank () throws ExecutionException, InterruptedException {
        Integer result = new Integer(0);
        for(int i = 1; i < listSbS.size(); i++){
          result += listSbS.get(i).getBalanceBank().get();
        }
        return result;
    }

    public void handlerRequest(Request request) throws ExecutionException, InterruptedException {
        lock.lock();
        int inputMoney = request.getMoney();
        switch (request.getTypeRequest()) {
            case CREDIT:
                if (getAtomicBalanceBank().get() >= inputMoney) {
                    balanceBank.addAndGet(-inputMoney);
                    System.out.println("Заявка на кредит" + request + " обработана банком, баланс банка: "+ getAtomicBalanceBank());
                } else {
                    System.out.println("В банке столько денег нет, баланс банка: " + getBalanceBank());
                }
            case REPAYMENT:
                balanceBank.addAndGet(inputMoney);
                System.out.println("Заявка на погашение кредита" + request + " обработана банком, баланс банка: "+ getAtomicBalanceBank());
        }
        lock.unlock();
    }
}
