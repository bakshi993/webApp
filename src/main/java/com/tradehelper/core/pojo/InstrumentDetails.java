package com.tradehelper.core.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InstrumentDetails {

    private Long instrumentToken;

    private String positionType;
}
