package com.hacom.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.stereotype.Component;

@Component
public class MetricsService {

    private final Counter insertCounter;

    public MetricsService(MeterRegistry meterRegistry) {
        this.insertCounter = meterRegistry.counter("hacom.test.developer.insert.rx");
    }

    public void incrementInsertCounter() {
        insertCounter.increment();
    }

}
