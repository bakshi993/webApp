package com.tradehelper.core.db.collection;

import com.zerodhatech.models.Instrument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("instrument")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentEntity {

    @Id
    private Long instrumentToken;

    private Instrument instrument;
}
