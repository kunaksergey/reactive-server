package ua.local.server.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.local.server.domain.Quote;

import java.util.List;

public interface QuoteMongoBlockingRepository extends CrudRepository<Quote,String> {
    @Query("{ id: { $exists: true }}")
    List<Quote> retrieveAllQuotesPaged(final Pageable page);
}
