package com.nosql.SQLEntity;


import javax.persistence.*;

@Entity
public class PrinterSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "color", nullable = false)
    private String color;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanySQL company;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeSQL type;


    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id", nullable = false)
    private ModelSQL model;

    public PrinterSQL() {

    }


    public PrinterSQL(String name, Double price, String color, CompanySQL company, TypeSQL type, ModelSQL model) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.company = company;
        this.type = type;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CompanySQL getCompany() {
        return company;
    }

    public void setCompany(CompanySQL company) {
        this.company = company;
    }

    public TypeSQL getType() {
        return type;
    }

    public void setType(TypeSQL type) {
        this.type = type;
    }

    public ModelSQL getModel() {
        return model;
    }

    public void setModel(ModelSQL model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "PrinterSQL{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", company=" + company +
                ", type=" + type +
                ", model=" + model +
                '}';
    }
}
