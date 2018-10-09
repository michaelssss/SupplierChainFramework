package com.michaelssss.business.ordermanagement;

public class FakeReceivedInfo {

  public static ReceivedInfo getMock() {
    ReceivedInfo receivedInfo = new ReceivedInfo();
    receivedInfo.setRemark("TestRemark");
    receivedInfo.setBankName("ICBC");
    receivedInfo.setBankAccount("testAccount");
    receivedInfo.setBankAddress("testAddress");
    receivedInfo.setCompanyAddress("testCompanyAddress");
    receivedInfo.setCompanyFullName("testCompanyFullName");
    return receivedInfo;
  }
}
