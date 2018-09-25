package com.michaelssss.business.ordermanagement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "all_order", indexes =
        {
                @Index(name = "idx_code", columnList = "code", unique = true),
                @Index(name = "idx_date", columnList = "orderDate")
        })
@ToString
@Setter
@Getter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrder implements Order {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long uid;
    @Column(length = 64)
    protected String code;

    protected Date orderDate;

    protected String status = NEW;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    protected Collection<OrderGood> goods = new ArrayList<>();//委托采购商品信息

    @Override
    public BigDecimal getOrderGoodTotalPrice() {
        BigDecimal result = BigDecimal.ZERO;
        for (OrderGood good : this.goods) {
            result = result.add(good.getUnitPrice().multiply(good.getTotal()));
        }
        return result;
    }

    @Override
    public Collection<OrderGood> getGoods() {
        return Collections.unmodifiableCollection(goods);
    }

    /**
     * 每个业务应该自己重写自己的业务命名规则
     *
     * @return
     */
    protected abstract String generateCode();

    @Override
    public void confirm() {
        checkEditable();
        this.status = CONFIRM;
    }

    @Override
    public void terminate() {
        checkEditable();
        this.status = TERMINATE;
    }

    protected void checkEditable() {
        if (!this.status.equals(NEW)) {
            throw new RuntimeException("订单已确认或已生效，不可修改");
        }
    }

    @Override
    public void addOrderGood(OrderGood good) {
        checkEditable();
        this.goods.add(good);

    }

    @Override
    public void removeOrderGood(OrderGood good) {
        checkEditable();
        this.goods.remove(good);

    }
}
