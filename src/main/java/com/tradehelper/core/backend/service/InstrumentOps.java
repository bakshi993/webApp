package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.Quote;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InstrumentOps {

    List<Instrument> getAllInstrument() throws IOException, KiteException;

    List<Instrument> getInstrumentsByExchange(String exchange) throws IOException, KiteException;

    Map<String, Quote> getMarketQuotes(String[] instrument) throws IOException, KiteException;
}
