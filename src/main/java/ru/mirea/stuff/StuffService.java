package ru.mirea.stuff;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StuffService {

    private List<Stuff> stuff = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init(){
        stuff.add(new Stuff(4, "brush", 1589));
        stuff.add(new Stuff( 5, "shampoo", 3777));
        stuff.add(new Stuff(6, "mouse_toy",  400));
    }

    public List<Stuff> stuff() {
        return stuff;
    }
}
