package com.tradehelper.core.ui;

import com.tradehelper.core.backend.client.KClient;

import com.tradehelper.core.backend.service.impl.HPOpsImpl;
import com.tradehelper.core.backend.service.impl.UpdateOpsImpl;
import com.tradehelper.core.config.cache.CacheStore;
import com.tradehelper.core.db.repo.WatchListRepo;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import com.zerodhatech.models.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;

import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Controller
public class LandingPageController {

    @Autowired
    private UpdateOpsImpl updateOps;

    @Autowired
    private WatchListRepo watchListRepo;

    @Autowired
    private HPOpsImpl hpOps;

    @Autowired
    private CacheStore<Long, Instrument> instrumentCacheStore;

    @Autowired
    private CacheStore<Long, ArrayList<Long>> watchListCacheStore;

    @GetMapping(value = "/")
    public RedirectView getLandingPage(@RequestParam("action") String action,
                                       @RequestParam("status") String status,
                                       @RequestParam("request_token") String requestToken) throws KiteException {
        log.info(action + ":" + status) ;
        KClient.requestToken = requestToken;
        updateOps.connectKiteListener();
        return new RedirectView("homePage");
    }

    @GetMapping(value = "/homePage")
    public String getHomePage(Model model) throws IOException, KiteException {
        final ArrayList<Long> watchListTokenList = new ArrayList<>();
        watchListCacheStore.getAllKeyValues().forEach((k,v) -> {
            watchListTokenList.addAll(v);
        });

        List<Instrument> watchListInstruments = watchListTokenList.stream()
                .map(token -> instrumentCacheStore.get(token))
                .collect(Collectors.toList());

        model.addAttribute("watchListEntries", watchListInstruments);
        //model.addAttribute("netPositions", netPositions);
        //List<Position> netPositions = hpOps.getAllPositions().get("net");

        // TODO: 10-03-2024 mutual funds 
        return "homePage";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "loginPage";
    }
}
