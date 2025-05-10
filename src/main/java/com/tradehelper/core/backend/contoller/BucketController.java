package com.tradehelper.core.backend.contoller;

import com.tradehelper.core.backend.service.impl.BucketOpsImpl;

import com.tradehelper.core.db.collection.BucketEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value = "/api/v1/")
public class BucketController {

    @Autowired
    private BucketOpsImpl bucketOps;

    @GetMapping(value = "/bucket", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BucketEntity> createNewBucket(@RequestParam("name") String bucketName,
                                                                      @RequestParam("desc") String description,
                                                                      @RequestParam("stopLoss") int stopLoss,
                                                                      @RequestParam("target") int target) {
        return new ResponseEntity<>(bucketOps.createNewBucket(bucketName, description,
                stopLoss, target), HttpStatus.OK);
    }

    @GetMapping(value = "/buckets", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ConcurrentMap<String, BucketEntity>> getAllBucket() {
        return new ResponseEntity<>(bucketOps.getAllBuckets(), HttpStatus.OK);
    }

    @GetMapping(value = "/bucket/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> deleteBucket(@RequestParam("id") String bucketId) {
        return new ResponseEntity<>(bucketOps.deleteBucket(bucketId), HttpStatus.OK);
    }

    @GetMapping(value = "/bucket/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BucketEntity> addPositions(@RequestParam("id") String bucketId,
                                                                   @RequestParam("token") Long instrumentToken,
                                                                   @RequestParam("type") String positionType) {
        return new ResponseEntity<>(bucketOps.addPositions(bucketId, instrumentToken, positionType), HttpStatus.OK);
    }

    @GetMapping(value = "/bucket/stopLoss", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BucketEntity> setStopLoss(@RequestParam("id") String bucketId,
                                                                  @RequestParam("stopLoss") Integer stopLoss) {
        return new ResponseEntity<>(bucketOps.setStopLoss(bucketId, stopLoss), HttpStatus.OK);
    }

    @GetMapping(value = "/bucket/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BucketEntity> removePositions(@RequestParam("id") String bucketId,
                                                                      @RequestParam("token") Long instrumentToken,
                                                                      @RequestParam("type") String positionType) {
         return new ResponseEntity<>(bucketOps.removePositions(bucketId, instrumentToken, positionType), HttpStatus.OK);
    }

    @GetMapping(value = "/bucket/target", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BucketEntity> setProfitTarget(@RequestParam("id") String bucketId,
                                                                      @RequestParam("target") Integer target) {
        return new ResponseEntity<>(bucketOps.setProfitTarget(bucketId, target), HttpStatus.OK);
    }
}
