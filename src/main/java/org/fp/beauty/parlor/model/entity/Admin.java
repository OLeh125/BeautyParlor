package org.fp.beauty.parlor.model.entity;

public class Admin {
    private int id;
    private String nameUa;
    private String surnameUa;
    private String nameEn;
    private String surnameEn;
    private String password;
    private String email;


    public Admin() {
    }

    public static class Builder{
        private Admin newAdmin;

        public Builder() {
            newAdmin = new Admin();
        }

        public Builder withId(int id){
            newAdmin.id = id;
            return this;
        }

        public Builder withNameUa(String nameUa){
            newAdmin.nameUa = nameUa;
            return this;
        }

        public Builder withSurnameUa(String surnameUa){
            newAdmin.surnameUa = surnameUa;
            return this;
        }

        public Builder withNameEn(String nameEn){
            newAdmin.nameEn = nameEn;
            return this;
        }

        public Builder withSurnameEn(String surnameEn){
            newAdmin.surnameEn = surnameEn;
            return this;
        }

        public Builder withPassword(String password){
            newAdmin.password = password;
            return this;
        }

        public Builder withEmail(String email){
            newAdmin.email = email;
            return this;
        }


        public Admin build(){
            return newAdmin;
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

}
