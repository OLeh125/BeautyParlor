package org.fp.beauty.parlor.model.entity;

import java.util.List;

public class Master {
    private int id;
    private String nameUa;
    private String surnameUa;
    private String nameEn;
    private String surnameEn;
    private String password;
    private String email;
    private double rating;
    //TODO superfluous
    private List<Treatment> listOfTreatments;
    private List<Order> orders;

    public Master() {

    }



    public static class Builder  {
        private Master newMaster;
        public Builder() {
            newMaster = new Master();
        }

        public Builder withId(int id){
            newMaster.id = id;
            return this;
        }

        public Builder withNameUa(String nameUa){
            newMaster.nameUa = nameUa;
            return this;
        }

        public Builder withSurnameUa(String surnameUa){
            newMaster.surnameUa = surnameUa;
            return this;
        }

        public Builder withNameEn(String nameEn){
            newMaster.nameEn = nameEn;
            return this;
        }

        public Builder withSurnameEn(String surnameEn){
            newMaster.surnameEn = surnameEn;
            return this;
        }

        public Builder withPassword(String password){
            newMaster.password = password;
            return this;
        }

        public Builder withEmail(String email){
            newMaster.email = email;
            return this;
        }


        public Builder withRating(double rating){
            newMaster.rating = rating;
            return this;
        }

        public Builder withTreatments(List <Treatment> treatments){
            newMaster.listOfTreatments = treatments;
            return this;
        }
        public Builder withOrders(List<Order> orders){
            newMaster.orders = orders;
            return this;
        }

        public Master build(){
            return newMaster;
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getSurnameUa() {
        return surnameUa;
    }

    public void setSurnameUa(String surnameUa) {
        this.surnameUa = surnameUa;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public void setSurnameEn(String surnameEn) {
        this.surnameEn = surnameEn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Treatment> getListOfTreatments() {
        return listOfTreatments;
    }

    public void setListOfTreatments(List<Treatment> listOfTreatments) {
        this.listOfTreatments = listOfTreatments;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
