package com.nosql.Memento;

import com.nosql.MongoEntity.PrinterMongo;


public class Memento {
    private final PrinterMongo printer;

    public Memento(PrinterMongo p) {
        this.printer =new PrinterMongo(p.getName(),p.getPrice(),p.getColor(),p.getTypeMongo(),
                p.getModelMongo(),p.getCompanyMongo());
    }

    public PrinterMongo getPrinter() {
        return printer;
    }
}
