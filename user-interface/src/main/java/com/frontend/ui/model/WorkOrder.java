package com.frontend.ui.model;


public class WorkOrder {

    private Long workOrderId;
    private String customerName;
    private String address;
    private Integer telNo;

    private String exchange;
    private String so_number;
    private String installer;
    private String plan;
    private String cbr;

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelNo() {
        return telNo;
    }

    public void setTelNo(Integer telNo) {
        this.telNo = telNo;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr;
    }
}
