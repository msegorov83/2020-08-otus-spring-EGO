package ru.otus.spring.integration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.stereotype.Component;
import ru.otus.spring.integration.domain.Butterfly;
import ru.otus.spring.integration.domain.Caterpillar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


@Component
class InitApp {

    private static final String[] INSECTS = {
            "Капустница ( 0_0 )(,)(,)(,)",
            "Махаон ( 0_0 )(,)(,)(,)",
            "Краснохвостка ( 0_0 )(,)(,)(,)",
            "Адмирал ( 0_0 )(,)(,)",
            "Пяденица ( 0_0 )(,)" };

    @Bean
    public QueueChannel caterpillarChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 10 ).maxMessagesPerPoll(1).get();
    }

    @Bean
    public IntegrationFlow cocoonFlow() {
        return IntegrationFlows.from( "caterpillarChannel" )
                .split()
                .handle( "cocoonService", "born" )
                .transform(Butterfly.class, b -> {
                    String caterpillarASCII = "( 0_0 )(,)(,)(,)";
                    String butterflyASCII = "} 0|0 {";
                    String kittyASCII = "=>-_-<=";
                    return b.getName().endsWith(caterpillarASCII) ? new Butterfly(b.getName().replace(caterpillarASCII,butterflyASCII)) :  new Butterfly(kittyASCII);
                } )
                .aggregate()
                .channel( "butterflyChannel" )
                .get();
    }

    public void run() throws InterruptedException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        Cocoon cocoon = ctx.getBean(Cocoon.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while ( true ) {
            Thread.sleep( 1000 );
            pool.execute( () -> {
                Collection<Caterpillar> caterpillars = generateCaterpillars();
                System.out.println( "Caterpillars: " +
                        caterpillars.stream().map(Caterpillar::getName)
                                .collect( Collectors.joining( ", " ) ) );
                Collection<Butterfly> butterflies = cocoon.process(caterpillars);
                System.out.println( "Butterflys: " + butterflies.stream()
                        .map(Butterfly::getName)
                        .collect(Collectors.joining( ", " )));
            } );
        }
    }

    private static Collection<Caterpillar> generateCaterpillars() {
        List<Caterpillar> caterpillars = new ArrayList<>();
        for ( int i = 0; i < RandomUtils.nextInt( 1, 5 ); ++ i ) {
            caterpillars.add( generateCaterpillar() );
        }
        return caterpillars;
    }

    private static Caterpillar generateCaterpillar() {
        return new Caterpillar( INSECTS[ RandomUtils.nextInt( 0, INSECTS.length ) ] );
    }

}
