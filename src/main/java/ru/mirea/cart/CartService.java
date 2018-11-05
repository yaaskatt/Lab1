package ru.mirea.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.balance.BalanceService;
import ru.mirea.pets.Pet;
import ru.mirea.pets.PetService;
import ru.mirea.stuff.Stuff;
import ru.mirea.stuff.StuffService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CartService {

    private PetService petService;

    private StuffService stuffService;

    private BalanceService balanceService;

    private List<Cart> carts= Collections.synchronizedList(new ArrayList<>());
    private int cartId = 0;

    @PostConstruct
    public void init() {
        carts.add(new Cart(cartId++, 3, 4));
        carts.add(new Cart(cartId++, 3, 1));
        carts.add(new Cart(cartId++, 2, 4));
        carts.add(new Cart(cartId++, 1, 2));
        carts.add(new Cart(cartId++, 1, 5));
        carts.add(new Cart(cartId++, 1, 6));
        carts.add(new Cart(cartId++, 1, 3));
        carts.add(new Cart(cartId++, 4, 2)); //Недостаточно средств
        carts.add(new Cart(cartId++, 5, 1)); //Нет баланса
        carts.add(new Cart(cartId++, 6, 10)); //Товар не существует
    }

    public List<Cart> cart(int id) {
        List<Cart> userCart = new ArrayList<>();
        int count = 0;
        for (int i=0; i<carts.size(); i++) {
            if (carts.get(i).userId == id) {
                userCart.add(carts.get(i));
                count++;
            }
        }
        if (count == 0) throw new NullPointerException("Cart not found");
        return userCart;
    }

    public List<Pet> getPets(int id) {

        List<Cart> userCart = cart(id);
        List<Pet> userPets = new ArrayList<>();
        List<Pet> pets = petService.pets();
        for (int i=0; i<userCart.size(); i++) {
            for (int j=0; j<pets.size(); j++)
                if (userCart.get(i).itemId == pets.get(j).id) {
                    userPets.add(pets.get(j));
            }
        }
        return userPets;
    }

    public List<Stuff> getStuff(int id) {

        List<Cart> userCart = cart(id);
        List<Stuff> userStuff = new ArrayList<>();
        List<Stuff> stuff = stuffService.stuff();
        for (int i=0; i<userCart.size(); i++) {
            for (int j=0; j<stuff.size(); j++)
                if (userCart.get(i).itemId == stuff.get(j).id) {
                    userStuff.add(stuff.get(j));
                }
        }
        return userStuff;
    }

    public void put(int userId, int itemId) {
        int count = 0;
        List<Pet> pets = petService.pets();
        List<Stuff> stuff = stuffService.stuff();
        for (int i=0; i<stuff.size(); i++) {
            if (itemId == stuff.get(i).id) {
                count++;
                break;
            }
        }
        if (count == 0) {
            for (int i=0; i<pets.size(); i++) {
                if (itemId == pets.get(i).id) {
                    count++;
                    break;
                }
            }
        }
        if (count != 0)
            carts.add(new Cart(cartId++, userId, itemId));
        else throw new NullPointerException("No product found");
    }

    public void delete(int userId, int itemId) {
        int count = 0;
        for (int i=0; i<carts.size(); i++) {
            if (carts.get(i).userId == userId && carts.get(i).itemId == itemId) {
                count++;
                carts.remove(i);
            }
        }
        if (count == 0) throw new NullPointerException("Record not found");
    }

    public void post(int userId) {
        double totalPrice = 0;
        double userBal = balanceService.balance(userId).bal;

        List<Cart> userCart = cart(userId);

        List<Pet> userPets = getPets(userId);
        List<Stuff> userStuff = getStuff(userId);
        for (int i=0; i<userPets.size(); i++) {
            totalPrice += userPets.get(i).price;
        }
        for (int i=0; i<userStuff.size(); i++) {
            totalPrice += userStuff.get(i).price;
        }

        if (totalPrice <= userBal) {
            balanceService.put(userId, userBal-totalPrice);

            for (int i=0; i<userCart.size(); i++) {
                delete(userId, userCart.get(i).itemId);
            }
        }
        else throw new UnsupportedOperationException("Not enough balance");
    }

    @Autowired
    public void setPetService(PetService petService) {
        this.petService = petService;
    }

    @Autowired
    public void setStuffService(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }
}
