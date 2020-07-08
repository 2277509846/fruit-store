package com.fjp.entity;

/*
 * 水果类
 */
public class Fruit {
    private Integer id;
    private String name;
    private Double price;
    private String unit;
    private Integer count;
    private Integer sortId;
    private String sort;

    public Fruit() {
    }

    public Fruit(Integer id, String name, Double price, String unit, Integer count, Integer sortId) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.count = count;
        this.sortId = sortId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
