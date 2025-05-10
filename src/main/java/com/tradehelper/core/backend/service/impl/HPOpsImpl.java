package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.HPOps;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Holding;
import com.zerodhatech.models.Position;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class HPOpsImpl implements HPOps {

    public List<Holding> getCurrentHoldings() throws IOException, KiteException {
        return KClient.getKiteConnect().getHoldings();
    }

    public Map<String, List<Position>> getAllPositions() throws IOException, KiteException {
        return KClient.getKiteConnect().getPositions();
    }
}
