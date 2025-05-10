package com.tradehelper.core.backend.service.impl;

import com.google.gson.Gson;
import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.InstrumentOps;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.Quote;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InstrumentOpsImpl implements InstrumentOps {

    @Override
    public List<Instrument> getAllInstrument() throws IOException, KiteException {
        return KClient.getKiteConnect().getInstruments();
    }

    @Override
    public List<Instrument> getInstrumentsByExchange(String exchange) throws IOException, KiteException {
        return KClient.getKiteConnect().getInstruments(exchange);
    }

    @Override
    public Map<String, Quote> getMarketQuotes(String[] instrument) throws IOException, KiteException {

        return KClient.getKiteConnect().getQuote(instrument);
    }
}
