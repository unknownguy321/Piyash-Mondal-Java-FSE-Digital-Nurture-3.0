package com.example.bookstore.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class BookstoreMetrics {

    private final Counter booksSoldCounter;

    public BookstoreMetrics(MeterRegistry meterRegistry) {
        this.booksSoldCounter = meterRegistry.counter("bookstore.books.sold");
    }

    public void incrementBooksSold() {
        booksSoldCounter.increment();
    }
}
