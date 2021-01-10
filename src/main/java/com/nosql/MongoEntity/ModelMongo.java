package com.nosql.MongoEntity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;


@Document(collection = "Model")
public class ModelMongo {
    @Id
    private String id;

    @Field(name = "model")
    private String model;

    @Field(name = "print_capacity")
    private Integer print_capacity;

    @Field(name = "life_time")
    private Double life_time;

    @Field(name = "ink_type")
    private String ink_type;

    public ModelMongo() {
    }

    public ModelMongo(String model, Integer print_capacity, Double life_time, String ink_type) {
        this.model = model;
        this.print_capacity = print_capacity;
        this.life_time = life_time;
        this.ink_type = ink_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrint_capacity() {
        return print_capacity;
    }

    public void setPrint_capacity(Integer print_capacity) {
        this.print_capacity = print_capacity;
    }

    public Double getLife_time() {
        return life_time;
    }

    public void setLife_time(Double life_time) {
        this.life_time = life_time;
    }

    public String getInk_type() {
        return ink_type;
    }

    public void setInk_type(String ink_type) {
        this.ink_type = ink_type;
    }

    @Override
    public String toString() {
        return "ModelMongo{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", print_capacity=" + print_capacity +
                ", life_time=" + life_time +
                ", ink_type='" + ink_type + '\'' +
                '}';
    }
}

