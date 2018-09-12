package com.michaelssss.business.riskmanagement;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "eval_set")
@Setter
@Getter
public class EvalSet implements Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @NotEmpty
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<AbstractEvaluate> evaluates = new HashSet<>();

    @Override
    public BigDecimal eval() {
        BigDecimal result = BigDecimal.ZERO;
        for (Evaluate evaluate : this.evaluates) {
            result.add(evaluate.eval());
        }
        return result;
    }
}
