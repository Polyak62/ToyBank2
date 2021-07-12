package service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SberbankService {
    private Integer balanceBank;
    private int rand = (int) (Math.random() * 1);

    public SberbankService(Integer balanceBank) {
        this.balanceBank = balanceBank;
    }

    public CompletableFuture<Integer> getBalanceBank() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(rand);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return balanceBank;
        });
        return future;
    }
}
