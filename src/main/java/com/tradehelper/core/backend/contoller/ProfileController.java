package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.ProfileOpsImpl;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProfileController {

    @Autowired
    ProfileOpsImpl profileOps;

    @Cacheable
    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Profile> getProfile() {
        try {
            return new ResponseEntity<>(profileOps.getUserProfile(), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/profile/margins", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String, Margin>> getMargins() {
        try {
            return new ResponseEntity<>(profileOps.getMargins(), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
