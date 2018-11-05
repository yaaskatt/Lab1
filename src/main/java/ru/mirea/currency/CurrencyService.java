package ru.mirea.currency;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CurrencyService {

    List<Currency> currency = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() {
        currency.add(new Currency(0, 3, "RUB"));
        currency.add(new Currency(1, 1, "EUR"));
        currency.add(new Currency(2, 2, "RUB"));
    }

    public Currency currency(int id) {
        Currency userCur = new Currency (-1, -1, "");
        for (int i=0; i<currency.size(); i++) {
            if (id == currency.get(i).userId) {
                userCur = currency.get(i);
                break;
            }
        }
        if (userCur.id == -1) throw new NullPointerException("User not found");
        return userCur;
    }
}
