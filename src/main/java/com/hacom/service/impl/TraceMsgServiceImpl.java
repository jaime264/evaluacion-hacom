package com.hacom.service.impl;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.hacom.model.TraceMsg;
import com.hacom.repository.TraceMsgRepository;
import com.hacom.service.TraceMsgService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TraceMsgServiceImpl implements TraceMsgService {

     private final TraceMsgRepository repository;

    public Mono<TraceMsg> saveTrace(TraceMsg traceMsg) {
        return repository.save(traceMsg);
    }

    public Flux<TraceMsg> getTracesByDateRange(OffsetDateTime from, OffsetDateTime to) {
        return repository.findByTsBetween(from, to);
    }

}
