package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ModelNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.TrimTypeNotFoundException;

import java.util.List;

public interface ModelTrimService {
    Model saveModel(Model model);

    TrimType saveTrimType(TrimType trimType);

    List<Model> getAllModels();
    Model getModelById(int id) throws ModelNotFoundException;
    TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException;

    Model modifyModel(int id, Model model) throws ModelNotFoundException;

    TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException;
    void deleteModelById(int id) throws ModelNotFoundException;
}
