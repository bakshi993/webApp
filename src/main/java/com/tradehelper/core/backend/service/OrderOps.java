package com.tradehelper.core.backend.service;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Order;

import java.io.IOException;
import java.util.List;

public interface OrderOps {

    List<Order> getAllOrders() throws IOException, KiteException;
}
