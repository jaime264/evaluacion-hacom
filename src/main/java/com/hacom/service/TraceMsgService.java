package com.hacom.service;

import java.time.OffsetDateTime;

import com.hacom.model.TraceMsg;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TraceMsgService {

    public Mono<TraceMsg> saveTrace(TraceMsg traceMsg);

    public Flux<TraceMsg> getTracesByDateRange(OffsetDateTime from, OffsetDateTime to);
}
