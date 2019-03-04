package ua.local.server.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.local.server.domain.Quote;
import ua.local.server.repository.QuoteMongoBlockingRepository;

@RestController
public class QuoteBlockingController {
    private static final int DELAY_PER_ITEM_MS=100;
    private QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    public QuoteBlockingController(final QuoteMongoBlockingRepository quoteMongoBlockingRepository) {
        this.quoteMongoBlockingRepository = quoteMongoBlockingRepository;
    }

    @GetMapping("/quotes-blocking")
    public Iterable<Quote> getQuotesBlocking() throws Exception{
        Thread.sleep(DELAY_PER_ITEM_MS*quoteMongoBlockingRepository.count());
        return quoteMongoBlockingRepository.findAll();
    }

    @GetMapping("/quotes-blocking-paged")
    public Iterable<Quote> getQuotesBlocking(final @RequestParam(name="page") int page,
                                             final @RequestParam(name="size") int size  ) throws InterruptedException {
        Thread.sleep(DELAY_PER_ITEM_MS*size);
        return quoteMongoBlockingRepository.retrieveAllQuotesPaged(PageRequest.of(page,size));
    }
}
