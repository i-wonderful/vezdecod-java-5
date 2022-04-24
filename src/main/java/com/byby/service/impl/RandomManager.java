package com.byby.service.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class RandomManager {
    Random random;


    @PostConstruct
    public void init() {
        random = new Random();
    }

    public boolean getLocal(){
        return random.nextBoolean();
    }

    public int nextInt(int bound){
        return random.nextInt(bound);
    }

}
