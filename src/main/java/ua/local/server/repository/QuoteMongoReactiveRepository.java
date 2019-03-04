package ua.local.server.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ua.local.server.domain.Quote;

public interface QuoteMongoReactiveRepository extends ReactiveCrudRepository<Quote,String> {
    @Query("{id: {$exists: true}}")
    Flux<Quote> retrieveAllQuotesPaged(final Pageable page);
}
