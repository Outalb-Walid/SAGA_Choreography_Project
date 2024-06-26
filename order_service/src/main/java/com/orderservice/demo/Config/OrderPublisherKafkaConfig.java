package com.orderservice.demo.Config;

import com.dtos.demo.Events.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherKafkaConfig {

    /*
    * https://projectreactor.io/docs/core/3.4.0-SNAPSHOT/api/reactor/core/publisher/Sinks.MulticastSpec.html
    * */
    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();

    }

    /*
    * Bean that supply the order event
    * */
    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks){
        return sinks::asFlux;
    }

}
