package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;

import java.util.List;

public interface ModelTrimService {
    Model saveModel(Model model);
    TrimType saveTrimType(TrimType trimType);
    List<Model> getAllModels();
}
