package com.tradehelper.core.db.collection;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("watchlist")
public class WatchListEntity {

    @Id
    private Long watchListId;

    private ArrayList<Long> watchList;
}
