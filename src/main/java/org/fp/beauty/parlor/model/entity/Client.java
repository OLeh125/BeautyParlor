package org.fp.beauty.parlor.model.entity;

public class Client{
    private int id;
    private String nameUa;
    private String surnameUa;
    private String nameEn;
    private String surnameEn;
    private String password;
    private String email;


    public static class Builder{
        private Client newClient;

        public Builder() {
            newClient = new Client();
        }

        public Builder withId(int id){
            newClient.id = id;
            return this;
        }

        public Builder withNameEn(String nameEn){
            newClient.nameEn = nameEn;
            return this;
        }

        public Builder withSurnameEn(String surnameEn){
            newClient.surnameEn = surnameEn;
            return this;
        }

        public Builder withNameUa(String nameUa){
            newClient.nameUa = nameUa;
            return this;
        }

        public Builder withSurnameUa(String surnameUa){
            newClient.surnameUa = surnameUa;
            return this;
        }

        public Builder withPassword(String password){
            newClient.password = password;
            return this;
        }

        public Builder withEmail(String email){
            newClient.email = email;
            return this;
        }

        
        public Client build(){
            return newClient;
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
