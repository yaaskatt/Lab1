package ru.mirea.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping(value = "/currency/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Currency currency(@PathVariable int userId) {
        return currencyService.currency(userId);
    }
}
