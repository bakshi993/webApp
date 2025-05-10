package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.Profile;

import java.io.IOException;
import java.util.Map;

public interface ProfileOps {
    Profile getUserProfile() throws IOException, KiteException;

    Map<String, Margin> getMargins() throws IOException, KiteException;
}
