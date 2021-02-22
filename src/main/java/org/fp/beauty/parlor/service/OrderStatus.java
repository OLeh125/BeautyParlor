package org.fp.beauty.parlor.service;

public enum  OrderStatus {
    SUBMITTED("мюд╡якюмн"),
    CONFIRMED("о╡дрбепдфемн"),
    PAID("гюокювемн"),
    CANCELED("б╡дл╡мемн"),
    DONE("бхйнмюмн");

    private String text;

    OrderStatus(String text) {
        this.text = text;
    }

    public String getUa() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
