package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/model-trim")
public class ModelTrimController {
    @Autowired
    private ModelTrimService modelTrimService;
    @PostMapping
    public ResponseEntity<Model> creatModelTrim(@RequestBody Model model){
       Model saveRecord = modelTrimService.saveModel(model);
       return new ResponseEntity<>(saveRecord, HttpStatus.CREATED);
    }
    @PostMapping("/trim-type")
    public ResponseEntity<TrimType> createTrimType(@RequestBody TrimType trimType){
        TrimType saveTrimRecord = modelTrimService.saveTrimType(trimType);
        return new ResponseEntity<>(saveTrimRecord, HttpStatus.CREATED);
    }
}
