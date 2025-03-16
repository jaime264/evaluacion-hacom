package com.hacom.controller;

import com.hacom.metrics.MetricsService;
import com.hacom.model.TraceMsg;
import com.hacom.service.TraceMsgService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trace")
@Log4j2
public class TraceMsgController {

    private final TraceMsgService service;
    private final MetricsService metricsService;

    @PostMapping
    public Mono<TraceMsg> saveTrace(@RequestBody @Valid TraceMsg traceMsg) {

        log.info("Receiving insertion request: {}", traceMsg);

        metricsService.incrementInsertCounter();
        return service.saveTrace(traceMsg)
                .doOnSuccess(saved -> log.info("Saved in MongoDB: {}", saved))
                .doOnError(error -> log.error("Error saving in MongoDB", error));
    }

    @GetMapping
    public Flux<TraceMsg> getTraces(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {

        log.info("📅 Fetching records between {} and {}", from, to);

        return service.getTracesByDateRange(from, to)
                .doOnComplete(() -> log.info("Search completed"))
                .doOnError(error -> log.error("Error retrieving records", error));
    }

}
