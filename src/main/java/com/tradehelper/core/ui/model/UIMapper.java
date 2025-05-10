package com.tradehelper.core.ui.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UIMapper<T> {

    public long instrumentToken;

    public String tradingSymbol;

    public String name;

    public String exchange;

    public String instrumentType;

    public T object;
}
