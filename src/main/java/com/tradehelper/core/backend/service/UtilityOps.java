package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import java.io.IOException;

public interface UtilityOps {
    boolean refreshInstrumentLists() throws IOException, KiteException;
}
