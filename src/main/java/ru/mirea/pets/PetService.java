package ru.mirea.pets;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PetService {


    private List<Pet> pets = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init(){
        pets.add(new Pet(1,"dog", "m", "brown", 2000));
        pets.add(new Pet( 2, "cat", "f", "black", 1600));
        pets.add(new Pet(3, "rabbit", "f", "white", 4000));
    }

    public List<Pet> pets() {
        return pets;
    }

}
