package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.UpdateOpsImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/")
public class UpdateController {

    @Autowired
    private UpdateOpsImpl updateOps;

    @GetMapping(value = "/listener", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> subscribeToken(@RequestParam("token") Long token) {
        final ArrayList<Long> tokens = new ArrayList<>();
        tokens.add(token);
        updateOps.subscribeTokens(tokens);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/listener/disconnect", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> disconnectTicker() {
        updateOps.disconnectKiteListener();
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
}
