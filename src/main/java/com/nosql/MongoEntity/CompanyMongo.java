package com.nosql.MongoEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "company")
public class CompanyMongo {
    @Id
    private String id;

    @Field(name = "firm")
    private String firm;
    @Field(name = "country")
    private String country;

    public CompanyMongo() {
    }

    public CompanyMongo(String firm, String country) {
        this.firm = firm;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CompanyMongo{" +
                "id='" + id + '\'' +
                ", firm='" + firm + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
