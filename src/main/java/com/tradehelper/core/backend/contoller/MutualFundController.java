package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.MFOpsImpl;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.MFHolding;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class MutualFundController {

    @Autowired
    private MFOpsImpl mfOps;

    @GetMapping(value = "/mf/holdings", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MFHolding> getMFHolding() {
        try {
            return mfOps.getMutualFundHoldings();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
