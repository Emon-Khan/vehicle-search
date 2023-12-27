package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.ManufacturerDAO;
import com.shikbeTumio.vehicle.api.vehiclesearch.dao.ModelDao;
import com.shikbeTumio.vehicle.api.vehiclesearch.dao.TrimTypeDao;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ManufacturerNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ModelNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.TrimTypeNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ManufacturerService;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ModelTrimServiceImpl implements ModelTrimService {
    @Autowired
    private ModelDao modelDAO;
    @Autowired
    private TrimTypeDao trimTypeDAO;
    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public Model saveModel(Model model) {
        Model saveModel = modelDAO.save(model);
        return saveModel;
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeDAO.save(trimType);
    }

    @Override
    public List<Model> getAllModels() {
        List<Model> saveModels = modelDAO.findAll();
        return saveModels;
    }

    @Override
    public Model getModelById(int id) throws ModelNotFoundException {
        Optional<Model> checkModelId = modelDAO.findById(id);
        if (!checkModelId.isPresent()) {
            throw new ModelNotFoundException("No Model found in DB with ID- " + id);
        }
        return checkModelId.get();
    }

    @Override
    public TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException {
        Optional<TrimType> checkTrimTypeId = trimTypeDAO.findById(id);
        if (!checkTrimTypeId.isPresent()) {
            throw new TrimTypeNotFoundException("No trim type found in DB with ID- " + id);
        }
        return checkTrimTypeId.get();
    }

    @Override
    public Model modifyModel(int id, Model model) throws ModelNotFoundException {
        Model detailsOfModel = getModelById(id);
        if (detailsOfModel != null && Objects.nonNull(model)) {
            if (Objects.nonNull(model.getModelName()) && !"".equalsIgnoreCase(model.getModelName())) {
                detailsOfModel.setModelName(model.getModelName());
            }
            detailsOfModel = modelDAO.save(detailsOfModel);
        }
        return detailsOfModel;
    }

    @Override
    public TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException {
        TrimType detailsOfTrimType = getTrimTypeById(id);
        if (detailsOfTrimType != null && Objects.nonNull(trimType)) {
            if (Objects.nonNull(trimType.getTrimType()) && !"".equalsIgnoreCase(trimType.getTrimType())) {
                detailsOfTrimType.setTrimType(trimType.getTrimType());
            }
            detailsOfTrimType = trimTypeDAO.save(detailsOfTrimType);
        }
        return detailsOfTrimType;
    }

    @Override
    public void deleteModelById(int id) throws ModelNotFoundException {
        Model modelID = getModelById(id);
        modelDAO.deleteById(id);
    }

    @Override
    public List<Model> getModelsByManufacturerId(int manufacturerId) throws ManufacturerNotFoundException {
        Optional<Manufacturer> manufacturerIdFromDB = manufacturerDAO.findById(manufacturerId);
        if (!manufacturerIdFromDB.isPresent()) {
            throw new ManufacturerNotFoundException("Manufacturer is not found for the id " + manufacturerId);
        }
        Manufacturer detailsOfManufacturer = manufacturerIdFromDB.get();
        List<Model> modelList = modelDAO.findByManufacturer(detailsOfManufacturer);
        return modelList;
    }

    @Override
    public Model updateModel_Trim(int modelId, Model model)
            throws ModelNotFoundException, TrimTypeNotFoundException {
        Model modelDetails = getModelById(modelId);
        List<TrimType> trimTypeDetailsList = model.getTrimTypeList();
        Manufacturer manufacturerDetails = model.getManufacturer();
        if (modelDetails != null) {
            if ((!"".equalsIgnoreCase(model.getModelName())) &&
                    !Objects.equals(modelDetails.getModelName(), model.getModelName())) {
                modelDetails.setModelName(model.getModelName());
            }
            for (TrimType trimType : trimTypeDetailsList) {
                TrimType trimTypeDetails = getTrimTypeById(trimType.getId());
                if (trimTypeDetails != null && !(modelDetails.getTrimTypeList().contains(trimType))) {
                    modelDetails.getTrimTypeList().add(trimTypeDetails);
                }
            }
            for (int i = trimTypeDetailsList.size(); i < modelDetails.getTrimTypeList().size(); i++) {
                if (trimTypeDetailsList.size() != modelDetails.getTrimTypeList().size()) {
                    modelDetails.getTrimTypeList().remove(i);
                }
            }
            if ((manufacturerService.getManufacturerById(manufacturerDetails.getId()) != null) &&
                    !manufacturerDetails.equals(modelDetails.getManufacturer())) {
                modelDetails.setManufacturer(manufacturerDetails);
            }
            modelDetails = modelDAO.save(modelDetails);
        }
        return modelDetails;
    }

}
