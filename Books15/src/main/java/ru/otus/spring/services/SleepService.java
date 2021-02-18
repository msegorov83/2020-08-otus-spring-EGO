package ru.otus.spring.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SleepService {

    public void sleepRandom()  {
        Random random = new Random();
        int randomNum =  random.nextInt(3 ) + 1;
        if (randomNum == 3 )
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
