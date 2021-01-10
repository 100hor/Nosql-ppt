package com.nosql.MongoEntity;


import com.nosql.Memento.Memento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Printer")
public class PrinterMongo {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "price")
    private Double price;

    @Field(name = "color")
    private String color;

    @Field(name = "type")
    private TypeMongo typeMongo;

    @Field(name = "model")
    private ModelMongo modelMongo;
    @Field(name = "company")
    private CompanyMongo companyMongo;

    public PrinterMongo() {
    }
    public void setState(PrinterMongo p) {
        this.id = p.getId();
        this.price = p.getPrice();
        this.color=p.getColor();
        this.typeMongo=p.getTypeMongo();
        this.modelMongo=p.getModelMongo();
        this.companyMongo = p.getCompanyMongo();
    }


    public Memento saveState() {
        return new Memento(this);
    }

    public void restoreState(Memento memento) {
        this.setState(memento.getPrinter());
    }
    public PrinterMongo(String name, Double price, String color, TypeMongo typeMongo, ModelMongo modelMongo, CompanyMongo companyMongo) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.typeMongo = typeMongo;
        this.modelMongo = modelMongo;
        this.companyMongo = companyMongo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public TypeMongo getTypeMongo() {
        return typeMongo;
    }

    public void setTypeMongo(TypeMongo typeMongo) {
        this.typeMongo = typeMongo;
    }

    public ModelMongo getModelMongo() {
        return modelMongo;
    }

    public void setModelMongo(ModelMongo modelMongo) {
        this.modelMongo = modelMongo;
    }

    public CompanyMongo getCompanyMongo() {
        return companyMongo;
    }

    public void setCompanyMongo(CompanyMongo companyMongo) {
        this.companyMongo = companyMongo;
    }

    @Override
    public String toString() {
        return "PrinterMongo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", typeMongo=" + typeMongo +
                ", modelMongo=" + modelMongo +
                ", companyMongo=" + companyMongo +
                '}';
    }
}
