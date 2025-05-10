package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.InstrumentOpsImpl;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;

import com.zerodhatech.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/")
public class InstrumentController {

    @Autowired
    private InstrumentOpsImpl instrumentOps;

    @Cacheable
    @GetMapping(value = "/instruments", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Instrument> getAllInstrument() {
        try {
            return instrumentOps.getAllInstrument();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable
    @GetMapping(value = "/instruments/exchange", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Instrument> getInstrumentsByExchange(@RequestParam("q") String exchange) {
        try {
            return instrumentOps.getInstrumentsByExchange(exchange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable
    @GetMapping(value = "/instruments/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Quote> getMarketQuote(@RequestParam("exchange") String exchange,
                                                           @RequestParam("symbol") String symbol) {
        try {
            return instrumentOps.getMarketQuotes(new String[]{exchange.concat(":").concat(symbol)});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable
    @GetMapping(value = "/instruments/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Quote> getMarketQuotes(@RequestParam("q") String query) {
        try {
            return instrumentOps.getMarketQuotes(query.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
