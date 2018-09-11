package com.michaelssss.rzzl2.basicinfomanagement.service;

import com.michaelssss.rzzl2.basicinfomanagement.ProductionClassification;
import com.michaelssss.rzzl2.basicinfomanagement.respository.ProductionClassificationCatalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {
    private ProductionClassificationCatalog classificationCatalog;

    public List<ProductionClassification> getLevelOneProductionClassification() {
        return null;
    }

    public ProductionClassification getById(Long id) {
        return classificationCatalog.findOne(id);
    }
}
