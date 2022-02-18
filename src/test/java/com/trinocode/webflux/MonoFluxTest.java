package com.trinocode.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<String> test = Mono.just("test").log();
        test.subscribe(System.out::println);
    }

    @Test
    public void testMonoForError(){
        Mono<?> test = Mono.just("test").
                then(Mono.error(new RuntimeException("exception"))).log();
        test.subscribe(System.out::println,(e)-> System.out.println(e.getMessage()));
    }

    @Test
    public  void testFlux(){
        Flux<String> flux =Flux.just("Tom","DDS","Casa","Ville","NOR").
        concatWithValues("ROMAN").
        concatWith(Flux.error(new RuntimeException("Error in FLux"))).log();
        flux.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }
}
