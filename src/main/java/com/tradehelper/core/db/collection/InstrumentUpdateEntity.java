package com.tradehelper.core.db.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("instrumentUpdates")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentUpdateEntity {

    @Id
    private long instrumentToken;

    private double lastTradedPrice;

    private double lastTradeQuantity;

    private double change;

    private Date lastTradedTime;
}
