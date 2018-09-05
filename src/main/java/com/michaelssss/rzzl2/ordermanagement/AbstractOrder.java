package com.michaelssss.rzzl2.ordermanagement;

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
@Table(name = "`order`", indexes =
        {
                @Index(name = "idx_code", columnList = "code", unique = true),
                @Index(name = "idx_date", columnList = "orderDate")
        })
@ToString
@Setter
@Getter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractOrder implements Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long uid;
    @Column(length = 64)
    protected String code;

    protected Date orderDate;

    protected String status = New;

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
        this.status = Confirm;
    }

    @Override
    public void terminate() {
        checkEditable();
        this.status = Terminate;
    }

    protected void checkEditable() {
        if (!this.status.equals(New)) {
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
