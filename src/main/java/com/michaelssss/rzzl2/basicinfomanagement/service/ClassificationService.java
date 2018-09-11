package com.michaelssss.rzzl2.basicinfomanagement.service;

import com.michaelssss.rzzl2.basicinfomanagement.ProductionClassification;
import com.michaelssss.rzzl2.basicinfomanagement.domain.ProductionClassificationImpl;
import com.michaelssss.rzzl2.basicinfomanagement.respository.ProductionClassificationCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassificationService {
    private ProductionClassificationCatalog classificationCatalog;

    @Autowired
    public ClassificationService(ProductionClassificationCatalog classificationCatalog) {
        this.classificationCatalog = classificationCatalog;
    }

    public List<ProductionClassification> getLevelOneProductionClassification() {
        ProductionClassificationImpl sample = new ProductionClassificationImpl();
        sample.setLevel(0);
        return new ArrayList<>(classificationCatalog.findAll(Example.of(sample)));
    }

    public ProductionClassification getById(Long id) {
        return classificationCatalog.findOne(id);
    }
}
