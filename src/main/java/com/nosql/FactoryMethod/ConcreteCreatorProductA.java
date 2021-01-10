package com.nosql.FactoryMethod;

public class ConcreteCreatorProductA extends Creator{
    @Override
    public ProductInterface factoryMethod() {
        return new ProductA();
    }
}
