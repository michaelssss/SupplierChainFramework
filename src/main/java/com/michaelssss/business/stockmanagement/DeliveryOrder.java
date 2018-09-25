package com.michaelssss.business.stockmanagement;

import com.michaelssss.annotation.PhoneNumber;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "delivery_order",
        indexes = {@Index(name = "idx_saleordercodeclientname", columnList = "saleOrderCode,clientName")})
@Data
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;//业务无关主键ID
    @NotEmpty
    @Column(length = 64)
    private String saleOrderCode;//销售订单编号
    @NotEmpty
    @Column(length = 64)
    private String clientName;//客户名称
    @NotEmpty
    private String carNo;//委托车辆车牌号
    @NotEmpty
    private String pickUpName;//提货人姓名
    @NotEmpty
    private String pickUpIDCard;//提货人身份证号
    private Date pickUpDate;//提货日期
    @PhoneNumber
    private String contactPhone;//联系电话
    @NotEmpty
    private String status = "NEW";//状态：NEW,CONFIRM,RECEIVED_CONFIRM,TERMINATE
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cargo> cargos;//货物列表

    public String getSaleOrderCode() {
        return saleOrderCode;
    }

    void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    void deliveryConfirm() {
        this.status = "CONFIRM";
    }

    void clientReceivedConfirm() {
        this.status = "RECEIVED_CONFIRM";
    }

    void deliverTerminate() {
        this.status = "TERMINATE";
    }

    public BigDecimal getTotal() {
        BigDecimal result = BigDecimal.ZERO;
        for (Cargo cargo : cargos) {
            result = result.add(cargo.getShould());
        }
        return result;
    }

}
