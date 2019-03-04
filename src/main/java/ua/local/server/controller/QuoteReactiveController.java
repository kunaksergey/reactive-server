package ua.local.server.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.local.server.domain.Quote;
import ua.local.server.repository.QuoteMongoReactiveRepository;

import java.awt.*;
import java.time.Duration;

@RestController
public class QuoteReactiveController {
    private static final int DELAY_PER_ITEM_MS=100;
    private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveController(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @GetMapping("/quotes-reactive")
    public Flux<Quote> getQuoteFlux(){
        return quoteMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    @GetMapping(path="/quotes-reactive-paged", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Quote> getQuoteFlux(final @RequestParam(name="page") int page,
                                    final @RequestParam(name="size") int size){
        return quoteMongoReactiveRepository.retrieveAllQuotesPaged(PageRequest.of(page,size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }
}
