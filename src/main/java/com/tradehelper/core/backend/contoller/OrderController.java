package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.OrderOpsImpl;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Order;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class OrderController {

    @Autowired
    private OrderOpsImpl orderOps;

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public List<Order> getOrders() {
        try {
            return orderOps.getAllOrders();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
