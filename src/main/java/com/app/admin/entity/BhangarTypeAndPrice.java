package com.app.admin.entity;

import javax.persistence.*;

@Entity
public class BhangarTypeAndPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String bhangarType;

    @Column(nullable = false)
    private String bhangarUnit;

    @Column(nullable = false)
    private Double bhangarPrice;

    public BhangarTypeAndPrice() {
        super();
    }

    public BhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice) {
        this.bhangarType = bhangarType;
        this.bhangarUnit = bhangarUnit;
        this.bhangarPrice = bhangarPrice;
    }

    public Long getId() {
        return id;
    }

    public String getBhangarType() {
        return bhangarType;
    }

    public void setBhangarType(String bhangarType) {
        this.bhangarType = bhangarType;
    }

    public String getBhangarUnit() {
        return bhangarUnit;
    }

    public void setBhangarUnit(String bhangarUnit) {
        this.bhangarUnit = bhangarUnit;
    }

    public Double getBhangarPrice() {
        return bhangarPrice;
    }

    public void setBhangarPrice(Double bhangarPrice) {
        this.bhangarPrice = bhangarPrice;
    }
}
