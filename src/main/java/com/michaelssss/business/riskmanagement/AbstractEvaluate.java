package com.michaelssss.business.riskmanagement;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
public abstract class AbstractEvaluate implements Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long uid;
    @NotEmpty
    private String name;
}
