package com.tradehelper.core.db.collection;

import com.tradehelper.core.pojo.InstrumentDetails;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("bucket")
public class BucketEntity {

    @Id
    private String bucketId;

    private String bucketName;

    private String bucketDescription;

    private Date createdOn;

    private Date modifiedOn;

    private Date deletedOn;

    private boolean isDeleted;

    private boolean isActive;

    private Integer stopLoss;

    private Integer target;

    private List<InstrumentDetails> instrumentDetailsList;
}
