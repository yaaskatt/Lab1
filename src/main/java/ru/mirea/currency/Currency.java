package ru.mirea.currency;

public class Currency {
    public int id;
    public int userId;
    public String currency;

    public Currency(int id, int userId, String currency) {
        this.id = id;
        this.userId = userId;
        this.currency = currency;
    }
}
