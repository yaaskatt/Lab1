package ru.mirea.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PetController {


    @Autowired
    private PetService petService;

    @RequestMapping(value = "pets", method = GET)
    @ResponseBody
    public List<Pet> pets() {
        return petService.pets();
    }

    
}
