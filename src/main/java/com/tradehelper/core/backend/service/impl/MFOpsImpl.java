package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.MFOps;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.MFHolding;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MFOpsImpl implements MFOps {

    @Override
    public List<MFHolding> getMutualFundHoldings() throws IOException, KiteException {
        return KClient.getKiteConnect().getMFHoldings();
    }
}
