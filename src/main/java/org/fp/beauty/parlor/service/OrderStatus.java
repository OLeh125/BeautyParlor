package org.fp.beauty.parlor.service;

public enum  OrderStatus {
    SUBMITTED("��Ĳ�����"),
    CONFIRMED("ϲ����������"),
    PAID("���������"),
    CANCELED("²�̲����"),
    DONE("��������");

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
