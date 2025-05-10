package com.tradehelper.core.backend.service.impl;

import com.tradehelper.core.backend.client.KClient;
import com.tradehelper.core.backend.service.OrderOps;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Order;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderOpsImpl implements OrderOps {

    @Override
    public List<Order> getAllOrders() throws IOException, KiteException {
        return KClient.getKiteConnect().getOrders();
    }
}
