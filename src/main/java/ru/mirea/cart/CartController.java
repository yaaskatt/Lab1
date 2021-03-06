package ru.mirea.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.balance.BalanceService;
import ru.mirea.pets.Pet;
import ru.mirea.pets.PetService;
import ru.mirea.stuff.Stuff;
import ru.mirea.stuff.StuffService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private PetService petService;
    @Autowired
    private StuffService stuffService;
    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = "cart/{id}", method = GET)
    @ResponseBody
    public List<Cart> get(@PathVariable int id) {
        return cartService.cart(id);
    }

    @RequestMapping(value = "cart/pets/{userId}", method = GET)
    @ResponseBody
    public List<Pet> getPets(@PathVariable int userId) {
        return cartService.getPets(userId);
    }

    @RequestMapping(value = "cart/stuff/{userId}", method = GET)
    @ResponseBody
    public List<Stuff> getStuff(@PathVariable int userId) {
        return cartService.getStuff(userId);
    }

    @RequestMapping(value = "cart/{userId}/{itemId}", method = PUT)
    @ResponseBody
    public void put(@PathVariable int userId, @PathVariable int itemId) {
        cartService.put(userId, itemId);
    }

    @RequestMapping(value = "cart/{userId}/{itemId}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable int userId, @PathVariable int itemId) {
        cartService.delete(userId, itemId);
    }

    @RequestMapping(value = "cart/{userId}", method = POST)
    @ResponseBody
    public void post(@PathVariable int userId) {
        cartService.post(userId);
    }
}
