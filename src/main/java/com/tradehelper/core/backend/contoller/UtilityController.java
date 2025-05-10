package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.UtilityOpsImpl;

import com.tradehelper.core.config.cache.CacheStore;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import com.zerodhatech.models.Instrument;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/")
public class UtilityController {
    @Autowired
    private UtilityOpsImpl utilityOps;

    @Autowired
    private CacheStore<Long, Instrument> instrumentCacheStore;

    @GetMapping(value = "/utility/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> refreshInstrumentList() {
        try {
            return new ResponseEntity<>(utilityOps.refreshInstrumentLists(), HttpStatus.OK);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
