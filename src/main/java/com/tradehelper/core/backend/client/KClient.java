package com.tradehelper.core.backend.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

import java.io.IOException;
import java.util.Objects;

public class KClient {

    public static KiteConnect kiteConnect;

    public static String requestToken;

    public static final String userId = "ZV4850";

    public static final String apiKey = "hmm8pea3mlgdqe3h";

    public static final String secretKey = "nt0cyxrqu9dosns2416rm8yf2bnxxvz0";

    private static void createClient() {
        final KiteConnect kc = new KiteConnect(apiKey);
        try {
            kc.setUserId(userId);
            User user = kc.generateSession(requestToken, secretKey);
            kc.setAccessToken(user.accessToken);
            kc.setPublicToken(user.publicToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KiteException e) {
            throw new RuntimeException(e);
        }
        // TODO: 15-02-2024 expire hook
        kiteConnect = kc;
    }

    public static KiteConnect getKiteConnect() {
        if (Objects.isNull(kiteConnect)) {
            createClient();
        }
        return kiteConnect;
    }
}
