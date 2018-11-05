package ru.mirea.balance;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BalanceService {

    private List<Balance> balance = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() {
        balance.add(new Balance(0, 3, 4000));
        balance.add(new Balance(1, 1, 10000));
        balance.add(new Balance(2, 2, 3000));
        balance.add(new Balance( 3, 4, 0));
    }


    public Balance balance(int id) {
        Balance userBalance = new Balance(-1, -1, -1);
        for (int i = 0; i < balance.size(); i++) {
            if (balance.get(i).userId == id) {
                userBalance = balance.get(i);
                break;
            }
        }
        if (userBalance.id == -1) throw new NullPointerException("User not found");
        return userBalance;
    }

    public void put(int id, double newBal) {
        for (int i=0; i<balance.size(); i++) {
            if (balance.get(i).userId == id) {
                balance.get(i).bal = newBal;
                break;
            }
        }
    }
}

