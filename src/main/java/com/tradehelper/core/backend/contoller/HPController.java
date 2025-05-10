package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.HPOpsImpl;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Holding;

import com.zerodhatech.models.Position;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/")
public class HPController {

    @Autowired
    private HPOpsImpl hpOps;

    @GetMapping(value = "/holdings", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Holding>> getHoldings() {
        try {
            return new ResponseEntity<>(hpOps.getCurrentHoldings(), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<Position>>> getPositions() {
        try {
            return new ResponseEntity<>(hpOps.getAllPositions(), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
