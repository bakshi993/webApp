package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.MFHolding;

import java.io.IOException;
import java.util.List;

public interface MFOps {

    List<MFHolding> getMutualFundHoldings() throws IOException, KiteException;
}
