package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.ProfileOps;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.Profile;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ProfileOpsImpl implements ProfileOps {

    public Profile getUserProfile() throws IOException, KiteException {
        return KClient.getKiteConnect().getProfile();
    }

    @Override
    public Map<String, Margin> getMargins() throws IOException, KiteException {
        return KClient.getKiteConnect().getMargins();
    }
}
