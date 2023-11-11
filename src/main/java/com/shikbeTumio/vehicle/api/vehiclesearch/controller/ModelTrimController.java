package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ModelNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.TrimTypeNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/model-trim")
public class ModelTrimController {
    @Autowired
    private ModelTrimService modelTrimService;

    @PostMapping
    public ResponseEntity<Model> creatModelTrim(@RequestBody Model model) {
        Model saveRecord = modelTrimService.saveModel(model);
        return new ResponseEntity<>(saveRecord, HttpStatus.CREATED);
    }

    @PostMapping("/trim-type")
    public ResponseEntity<TrimType> createTrimType(@RequestBody TrimType trimType) {
        TrimType saveTrimRecord = modelTrimService.saveTrimType(trimType);
        return new ResponseEntity<>(saveTrimRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Model>> fetchAllModels() {
        List<Model> dbModels = modelTrimService.getAllModels();
        if (dbModels.size() > 0) {
            return new ResponseEntity<>(dbModels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable int id, @RequestBody Model model) throws ModelNotFoundException {
        Model updatedModel = modelTrimService.modifyModel(id, model);
        return new ResponseEntity<>(updatedModel, HttpStatus.OK);
    }

    @PutMapping("/trim-type/{id}")
    public ResponseEntity<TrimType> updateTrimType(@PathVariable int id, @RequestBody TrimType trimType) throws TrimTypeNotFoundException {
        TrimType updatedTrimType = modelTrimService.modifyTrimType(id, trimType);
        return new ResponseEntity<>(updatedTrimType, HttpStatus.OK);
    }
}
