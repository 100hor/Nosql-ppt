package com.nosql.AggragionEntity;

import org.springframework.data.annotation.Id;

public class Count {
    @Id
    String name;
    int count;

    public Count() {
    }

    public Count(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "PrinterCount{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
