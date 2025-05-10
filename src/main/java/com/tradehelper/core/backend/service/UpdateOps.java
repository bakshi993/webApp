package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import java.util.ArrayList;

public interface UpdateOps {

    boolean connectKiteListener() throws KiteException;

    void subscribeTokens(ArrayList<Long> tokens);

    void disconnectKiteListener();
}
