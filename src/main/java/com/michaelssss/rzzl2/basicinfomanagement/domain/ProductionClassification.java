package com.michaelssss.rzzl2.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.Production;
import com.michaelssss.rzzl2.basicinfomanagement.respository.ProductionClassificationCatalog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品的分类
 */
@Table(name = "production_classification")
@Entity
@Data
public class ProductionClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "业务无关Id", readOnly = true,hidden = true)
    private Long uid;
    @ApiModelProperty(value = "分类唯一标识")
    private String code;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "分类级别", readOnly = true)
    private int level;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy
    @ApiModelProperty(value = "子分类")
    private List<ProductionClassification> child;
    @ManyToMany
    @ApiModelProperty(value = "该分类需要填写的属性名称")
    private Set<TemplateProperty> templateProperties;
    @ManyToMany
    @ApiModelProperty(value = "该分类所有的商品")
    private List<ProductionImpl> productions;

    /**
     * 往该分类放置商品
     *
     * @param production 商品信息
     * @param keyValue   商品分类属性Map
     */
    public void addProduction(Production production, Map<String, String> keyValue) {
        ProductionImpl production1 = (ProductionImpl) production;
        for (TemplateProperty templateProperty : this.templateProperties) {
            PropertyKeyValue propertyKeyValue = new PropertyKeyValue();
            propertyKeyValue.setKey(templateProperty.getPropertyName());
            propertyKeyValue.setValue(keyValue.get(templateProperty.getPropertyName()));
            production1.addProperty(propertyKeyValue);
        }
        this.productions.add(production1);
    }

    /**
     * 获取该分类及其子分类的所有商品
     *
     * @return
     */
    public List<ProductionImpl> productionInThisClassification() {
        List<ProductionImpl> productions = new ArrayList<>(this.productions);
        for (ProductionClassification productionClassification : this.child) {
            productions.addAll(productionClassification.productionInThisClassification());
        }
        return productions;
    }

    public void addSubClassification(ProductionClassification productionClassification) {
        productionClassification.setLevel(this.level + 1);
        this.child.add(productionClassification);
    }

    public void save() {
        SpringContextHolder.getBean(ProductionClassificationCatalog.class).save(this);
    }
}