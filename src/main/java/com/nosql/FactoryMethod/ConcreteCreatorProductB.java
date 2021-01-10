package com.nosql.FactoryMethod;

public class ConcreteCreatorProductB extends Creator{
    @Override
    public ProductInterface factoryMethod() {
        return new ProductB();
    }
}
