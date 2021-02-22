package org.fp.beauty.parlor.model.entity;

import org.fp.beauty.parlor.service.OrderStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private int id;
    private LocalDate treatmentExecutionDate;
    private LocalTime treatmentExecutionTime;
    private int treatmentId;
    private int clientId;
    private int masterId;
    private OrderStatus statusEn;
    private String statusUa;

    private Client client;
    private Master master;
    private Treatment treatment;


    public static class Builder{
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder withId(int id){
            newOrder.id = id;
            return this;
        }
        public Builder withTreatmentId(int treatmentId){
            newOrder.treatmentId =treatmentId;
            return this;
        }

        public Builder withTreatment(Treatment treatment){
            newOrder.treatment = treatment;
            return this;
        }

        public Builder withClientId( int clientId ){
            newOrder.clientId = clientId;
            return this;
        }
        public Builder withClient(Client client){
            newOrder.client = client;
            return this;
        }


        public Builder withMasterId( int masterId){
            newOrder.masterId = masterId;
            return this;
        }
        public Builder withMaster(Master master ){
            newOrder.master = master;
            return this;
        }

        public Builder withTreatmentExecutionDate(LocalDate executionDate){
            newOrder.treatmentExecutionDate = executionDate;
            return this;
        }
        public Builder withTreatmentExecutionTime(LocalTime executionTime){
            newOrder.treatmentExecutionTime = executionTime;
            return this;
        }

        public Builder withStatusEn(OrderStatus statusEn){
            newOrder.statusEn = statusEn;
            newOrder.statusUa = statusEn.getUa();
            return this;
        }

        public Order build(){
            return newOrder;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getTreatmentExecutionDate() {
        return treatmentExecutionDate;
    }

    public void setTreatmentExecutionDate(LocalDate treatmentExecutionDate) {
        this.treatmentExecutionDate = treatmentExecutionDate;
    }

    public LocalTime getTreatmentExecutionTime() {
        return treatmentExecutionTime;
    }

    public void setTreatmentExecutionTime(LocalTime treatmentExecutionTime) {
        this.treatmentExecutionTime = treatmentExecutionTime;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }


    public OrderStatus getStatusEn() {
        return statusEn;
    }

    public void setStatusEn(OrderStatus statusEn) {
        this.statusEn = statusEn;
    }


    public String getStatusUa() {
        return statusUa;
    }

    public void setStatusUa(String statusUa) {
        this.statusUa = statusUa;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
