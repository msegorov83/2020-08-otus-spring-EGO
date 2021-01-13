package ru.otus.spring.integration.Service;

import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Butterfly;
import ru.otus.spring.integration.domain.Caterpillar;

@Service
public class CocoonService {

    public Butterfly born(Caterpillar caterpillar) throws Exception {
        System.out.println("Transform: " + caterpillar.getName());
        Thread.sleep(2000);
        return new Butterfly(caterpillar.getName());
    }

}
