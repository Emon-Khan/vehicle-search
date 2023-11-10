package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.ModelDao;
import com.shikbeTumio.vehicle.api.vehiclesearch.dao.TrimTypeDao;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelTrimServiceImpl implements ModelTrimService {
    @Autowired
    private ModelDao modelDAO;
    @Autowired
    private TrimTypeDao trimTypeDAO;

    @Override
    public Model saveModel(Model model) {
        Model saveModel = modelDAO.save(model);
        return saveModel;
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeDAO.save(trimType);
    }
}
