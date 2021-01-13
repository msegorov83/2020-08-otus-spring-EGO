package ru.otus.spring.integration;

import java.util.Collection;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.Butterfly;
import ru.otus.spring.integration.domain.Caterpillar;

@MessagingGateway
public interface Cocoon {

    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
    Collection<Butterfly> process(Collection<Caterpillar> caterpillars);
}
