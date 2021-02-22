package org.fp.beauty.parlor.model.entity;

public class Treatment {
    private int id;
    private int price;
    private String nameUa;
    private String nameEn;

    public static class Builder{
        private Treatment newTreatment;

        public Builder() {
            newTreatment = new Treatment();
        }

        public Builder withId(int id){
            newTreatment.id = id;
            return this;
        }
        public Builder withPrice(int price){
            newTreatment.price = price;
            return this;
        }
        public Builder withNameUa(String nameUa){
            newTreatment.nameUa = nameUa;
            return this;
        }
        public Builder withNameEn(String nameEn){
            newTreatment.nameEn = nameEn;
            return this;
        }

        public Treatment build(){
            return newTreatment;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
