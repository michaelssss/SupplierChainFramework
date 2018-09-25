package com.michaelssss.business.stockmanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.business.stockmanagement.repository.StockRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StockTest extends SpringBootTestBasic {
    @Autowired
    private StockRepository repository;

    @Test
    public void testSave() {
        StockImpl stock = new StockImpl();
        stock.setCode("testCode");
        stock = repository.saveAndFlush(stock);
        repository.delete(stock);
    }

    @Test
    public void testAdd() {
        ReceivedOrder receivedOrder = new ReceivedOrder();
        Cargo receivedOrderCargo = new Cargo();
        receivedOrderCargo.setName("testName");
        receivedOrderCargo.setModel("testModel");
        receivedOrderCargo.setUnit("testUnit");
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(receivedOrderCargo);
        receivedOrder.setCargos(cargos);
        receivedOrder.setPurchaseOrderCode("testCode");
        receivedOrder.setCarNo("1234567");
        receivedOrder.setDeliverName("testName");
        receivedOrder.setContactPhone("18124601060");
        receivedOrder.setStatus("Test");
        StockImpl stock = new StockImpl();
        stock.setCode("testCode");
        stock.receivedCargoOrderAdd(receivedOrder);
        stock = this.repository.saveAndFlush(stock);
        this.repository.delete(stock);
    }

    @Test
    public void testGetCargos() {
        ReceivedOrder receivedOrder = new ReceivedOrder();
        Cargo receivedOrderCargo = new Cargo();
        receivedOrderCargo.setName("testName");
        receivedOrderCargo.setModel("testModel");
        receivedOrderCargo.setUnit("testUnit");
        receivedOrderCargo.setName("testName");
        receivedOrderCargo.setModel("testModel");
        receivedOrderCargo.setUnit("testUnit");
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(receivedOrderCargo);
        receivedOrder.setCargos(cargos);
        receivedOrder.setPurchaseOrderCode("testCode");
        receivedOrder.setCarNo("1234567");
        receivedOrder.setDeliverName("testName");
        receivedOrder.setContactPhone("18124601060");
        receivedOrder.setStatus("Test");
        StockImpl stock = new StockImpl();
        stock.setCode("testCode");
        stock.receivedCargoOrderAdd(receivedOrder);
        stock = this.repository.saveAndFlush(stock);
        Assert.assertEquals(1, stock.getStockCargo().size());
        this.repository.delete(stock);
    }
}
