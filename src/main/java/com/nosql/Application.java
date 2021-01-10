package com.nosql;

import com.nosql.Observer.ObjectObservable;
import com.nosql.AggragionEntity.Count;
import com.nosql.Memento.Caretaker;
import com.nosql.MongoEntity.CompanyMongo;
import com.nosql.MongoEntity.ModelMongo;
import com.nosql.MongoEntity.PrinterMongo;
import com.nosql.MongoEntity.TypeMongo;
import com.nosql.MongoRepository.CompanyMongoRepository;
import com.nosql.MongoRepository.ModelMongoRepository;
import com.nosql.MongoRepository.PrinterMongoRepository;
import com.nosql.MongoRepository.TypeMongoRepository;
import com.nosql.SQLEntity.CompanySQL;
import com.nosql.SQLEntity.ModelSQL;
import com.nosql.SQLEntity.PrinterSQL;
import com.nosql.SQLEntity.TypeSQL;
import com.nosql.SQLRepository.CompanySQLRepository;
import com.nosql.SQLRepository.ModelSQLRepository;
import com.nosql.SQLRepository.PrinterSQLRepository;
import com.nosql.SQLRepository.TypeSQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private CompanySQLRepository companySQLRepository;
    @Autowired
    private ModelSQLRepository modelSQLRepository;
    @Autowired
    private PrinterSQLRepository printerSQLRepository;
    @Autowired
    private TypeSQLRepository typeSQLRepository;

    @Autowired
    private CompanyMongoRepository companyMongoRepository;
    @Autowired
    private ModelMongoRepository modelMongoRepository;
    @Autowired
    private PrinterMongoRepository printerMongoRepository;
    @Autowired
    private TypeMongoRepository typeMongoRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        //sql();
         // mongo();
         // migrationFromSqlToMongo();
          // migrationFromMongoToSql();
        //replication();
        //aggregation();
        System.out.println(new ModelSQL.Builder("модель1",500).life_time(12.0).build().toString());
        Singleton.getInstance().addLog("ModelSQL.Builder()");
        Singleton.getInstance().addLog("ModelSQL.Builder().life_time()");
        Singleton.getInstance().addLog("ModelSQL.Builder().build()");
        Singleton.getInstance().addLog("ModelSQL.toString()");
        Singleton.getInstance().addLog("System.out.println()");
        System.out.println();
        System.out.println();
        System.out.println(Singleton.getInstance().getLog());
        System.out.println();
        System.out.println();

        ObjectObservable objectObservable = new ObjectObservable();
        objectObservable.registerObserver(Singleton.getInstance());
        System.out.println(new ModelSQL.Builder("модель1",500).life_time(12.0).ink_type("inject").build().toString());
        objectObservable.notifyObservers("ModelSQL.Builder()");
        System.out.println(Singleton.getInstance().getLog());
        System.out.println();
        System.out.println();
        Caretaker caretaker = new Caretaker();
        CompanyMongo company = new CompanyMongo("Canon", "Украина");
        TypeMongo type = new TypeMongo("тип", "описание");
        ModelMongo model = new ModelMongo("модель", 1000, 1.2, "тип чернил");
        PrinterMongo p=new PrinterMongo("принтер", 100.0, "Черный", type, model, company);

        caretaker.addMemento(p.saveState());
        p.setPrice(90.0);
        caretaker.addMemento(p.saveState());
        p.setPrice(190.0);
        caretaker.addMemento(p.saveState());
        p.setColor("Белый");
        caretaker.addMemento(p.saveState());
        p.restoreState(caretaker.getMemento(2));
        System.out.println(p.toString());
        System.out.println();
        System.out.println();


        register("Пользователь", "логин", "пароль1", Role.User);
        register("Пользователь2", "логин2", "пароль2", Role.Admin);
        Role r1 = logIn("логин", "пароль1");
        Role r2 = logIn("логин2", "пароль2");
        PrinterSQL printer = printerSQLRepository.findById((long) 23).get();
        printer.setPrice(10000.0);
        updatePrinter(printer, r1);
        updatePrinter(printer, r2);
        CompanySQL c = companySQLRepository.findById((long) 1).get();
        ModelSQL m = modelSQLRepository.findById((long) 18).get();
        TypeSQL t = typeSQLRepository.findById((long) 13).get();
        PrinterSQL p2 = new PrinterSQL("принтер", 100.0, "Черный",c, t, m);
        insertPrinter(p2, r1);
        insertPrinter(p2, r2);
        deletePrinterById((long) 23, r1);
        deletePrinterById((long) 23, r2);

    }

    public Role logIn(String login, String password) {
        Optional<User> u = userRepository.findFirstByLoginAndPassword(login, password);
        if (u.isEmpty()) {
            System.out.println("Not correct login or password");
            return null;
        } else {
            return u.get().getRole();
        }
    }

    public Boolean register(String name, String login, String password, Role role) {
        Optional<User> u = userRepository.findFirstByLoginAndPassword(login, password);
        if (u.isEmpty()) {
            userRepository.save(new User(name, login, password, role));
            return true;
        } else {
            return false;
        }
    }

    public void updatePrinter(PrinterSQL p, Role r) {
        if (r == Role.Admin) {
            printerSQLRepository.save(p);
        } else {
            System.out.println("Ошибка! Недостаточно прав для обновления!");
        }
    }

    public void deletePrinterById(Long id, Role r) {
        if (r == Role.Admin) {
            printerSQLRepository.deleteById(id);
        } else {
            System.out.println("Ошибка! Недостаточно прав для удаления!");
        }
    }

    public void insertPrinter(PrinterSQL p, Role r) {
        if (r == Role.Admin) {
            printerSQLRepository.save(p);
        } else {
            System.out.println("Ошибка! Недостаточно прав для добавления!");
        }
    }

    public void mongo() {
        CompanyMongo company = new CompanyMongo("Canon", "Украина");
        //insert
        companyMongoRepository.save(company);
        companyMongoRepository.save(new CompanyMongo("Canon2", "Украина"));
        companyMongoRepository.save(new CompanyMongo("Canon3", "Украина"));
        companyMongoRepository.save(new CompanyMongo("Canon4", "Украина"));
        companyMongoRepository.save(new CompanyMongo("Canon5", "Украина"));
        companyMongoRepository.save(new CompanyMongo("Canon6", "Украина"));
        //select
        for (CompanyMongo c : companyMongoRepository.findAll()) {
            System.out.println(c.toString());
        }
        //update
        company.setFirm("Xenon");
        companyMongoRepository.save(company);
        System.out.println(companyMongoRepository.findFirstByFirm("Xenon").get().toString());
        //delete
        companyMongoRepository.deleteAllByFirm("Xenon");

        TypeMongo type = new TypeMongo("тип", "описание");
        typeMongoRepository.save(type);
        typeMongoRepository.save(new TypeMongo("тип2", "описание"));
        typeMongoRepository.save(new TypeMongo("тип3", "описание"));
        typeMongoRepository.save(new TypeMongo("тип4", "описание"));
        typeMongoRepository.save(new TypeMongo("тип5", "описание"));

        ModelMongo model = new ModelMongo("модель", 1000, 1.2, "тип чернил");
        modelMongoRepository.save(model);
        modelMongoRepository.save(new ModelMongo("модель2", 1000, 1.2, "тип чернил"));
        modelMongoRepository.save(new ModelMongo("модель3", 1000, 1.2, "тип чернил"));
        modelMongoRepository.save(new ModelMongo("модель4", 1000, 1.2, "тип чернил"));
        modelMongoRepository.save(new ModelMongo("модель5", 1000, 1.2, "тип чернил"));

        PrinterMongo printer = new PrinterMongo("принтер1", 100.0, "Черный", type, model, company);
        printerMongoRepository.save(printer);
        printerMongoRepository.save(new PrinterMongo("принтер2", 100.0, "Черный", type, model, company));
        printerMongoRepository.save(new PrinterMongo("принтер3", 100.0, "Черный", type, model, company));
        printerMongoRepository.save(new PrinterMongo("принтер4", 100.0, "Черный", type, model, company));
        printerMongoRepository.save(new PrinterMongo("принтер5", 100.0, "Черный", type, model, company));
    }

    public void sql() {
        CompanySQL companySql = new CompanySQL("Canon", "Украина");
        //insert
        companySQLRepository.save(companySql);
        companySQLRepository.save(new CompanySQL("Canon2", "Украина"));
        companySQLRepository.save(new CompanySQL("Canon3", "Украина"));
        companySQLRepository.save(new CompanySQL("Canon4", "Украина"));
        companySQLRepository.save(new CompanySQL("Canon5", "Украина"));
        companySQLRepository.save(new CompanySQL("Canon6", "Украина"));
        //select
        for (CompanySQL c : companySQLRepository.findAll()) {
            System.out.println(c.toString());
        }
        //update
        companySql.setFirm("Xenon");
        companySQLRepository.save(companySql);
        System.out.println(companySQLRepository.findFirstByFirm("Xenon").get().toString());
        //delete
        companySQLRepository.deleteAllByFirm("Canon6");

        TypeSQL typeSQL = new TypeSQL("тип", "описание");
        typeSQLRepository.save(typeSQL);
        typeSQLRepository.save(new TypeSQL("тип2", "описание"));
        typeSQLRepository.save(new TypeSQL("тип3", "описание"));
        typeSQLRepository.save(new TypeSQL("тип4", "описание"));
        typeSQLRepository.save(new TypeSQL("тип5", "описание"));

        ModelSQL modelSQL = new ModelSQL("модель", 1000, 1.2, "тип чернил");
        modelSQLRepository.save(modelSQL);
        modelSQLRepository.save(new ModelSQL("модель2", 1000, 1.2, "тип чернил"));
        modelSQLRepository.save(new ModelSQL("модель3", 1000, 1.2, "тип чернил"));
        modelSQLRepository.save(new ModelSQL("модель4", 1000, 1.2, "тип чернил"));
        modelSQLRepository.save(new ModelSQL("модель5", 1000, 1.2, "тип чернил"));

        PrinterSQL printerSQL = new PrinterSQL("принтер1", 100.0, "Черный", companySql, typeSQL, modelSQL);
        printerSQLRepository.save(printerSQL);
        printerSQLRepository.save(new PrinterSQL("принтер2", 100.0, "Черный", companySql, typeSQL, modelSQL));
        printerSQLRepository.save(new PrinterSQL("принтер3", 100.0, "Черный", companySql, typeSQL, modelSQL));
        printerSQLRepository.save(new PrinterSQL("принтер4", 100.0, "Черный", companySql, typeSQL, modelSQL));
        printerSQLRepository.save(new PrinterSQL("принтер5", 100.0, "Черный", companySql, typeSQL, modelSQL));
    }

    public void migrationFromSqlToMongo() {
        List<CompanySQL> companySQLList = companySQLRepository.findAll();
        for (CompanySQL c : companySQLList) {
            companyMongoRepository.save(new CompanyMongo(c.getFirm(), c.getCountry()));
        }

        List<ModelSQL> modelSQLList = modelSQLRepository.findAll();
        for (ModelSQL m : modelSQLList) {
            modelMongoRepository.save(new ModelMongo(m.getModel(), m.getPrint_capacity(), m.getLife_time(), m.getInk_type()));
        }

        List<TypeSQL> typeSQLList = typeSQLRepository.findAll();
        for (TypeSQL t : typeSQLList) {
            typeMongoRepository.save(new TypeMongo(t.getType(), t.getDescription()));
        }

        List<PrinterSQL> printerSQLList = printerSQLRepository.findAll();
        for (PrinterSQL p : printerSQLList) {
            TypeMongo t=new TypeMongo(p.getType().getType(), p.getType().getDescription());
            ModelMongo m=new ModelMongo(p.getModel().getModel(),p.getModel().getPrint_capacity(), p.getModel().getLife_time(), p.getModel().getInk_type());
            CompanyMongo c = new CompanyMongo(p.getCompany().getFirm(), p.getCompany().getCountry());
            printerMongoRepository.save(new PrinterMongo(p.getName(), p.getPrice(), p.getColor(),t,m,c));
        }
    }

    public void migrationFromMongoToSql() {
        List<CompanyMongo> companyMongoList = companyMongoRepository.findAll();
        for (CompanyMongo c : companyMongoList) {
            companySQLRepository.save(new CompanySQL(c.getFirm(), c.getCountry()));
        }

        List<ModelMongo> modelMongoList = modelMongoRepository.findAll();
        for (ModelMongo m : modelMongoList) {
            modelSQLRepository.save(new ModelSQL(m.getModel(), m.getPrint_capacity(), m.getLife_time(), m.getInk_type()));
        }

        List<TypeMongo> typeMongoList = typeMongoRepository.findAll();
        for (TypeMongo t : typeMongoList) {
            typeSQLRepository.save(new TypeSQL(t.getName(), t.getDescription()));
        }

        List<PrinterMongo> printerMongoList = printerMongoRepository.findAll();
        for (PrinterMongo p : printerMongoList) {
            TypeSQL t=new TypeSQL(p.getTypeMongo().getName(),p.getTypeMongo().getDescription());
            typeSQLRepository.save(t);
            ModelSQL m=new ModelSQL(p.getModelMongo().getModel(),p.getModelMongo().getPrint_capacity(), p.getModelMongo().getLife_time(), p.getModelMongo().getInk_type());
            modelSQLRepository.save(m);
            CompanySQL c = new CompanySQL(p.getCompanyMongo().getFirm(), p.getCompanyMongo().getCountry());
            companySQLRepository.save(c);
            printerSQLRepository.save(new PrinterSQL(p.getName(), p.getPrice(), p.getColor(),c,t,m));
        }
    }

    public void replication() throws InterruptedException {
        companyMongoRepository.deleteAll();
        long startTime = System.nanoTime();
        for (Integer i = 0; i < 10000; i++) {
            insert(new CompanyMongo("Cartridge" + i.toString(), "Украина"));
        }
        long endTime = System.nanoTime();
        System.out.println("Insert: " + (double) (endTime - startTime) / 1000000000);
        startTime = System.nanoTime();
        companyMongoRepository.findAll();
        endTime = System.nanoTime();
        System.out.println("Select: " + (double) (endTime - startTime) / 1000000000);
    }

    public boolean insert(CompanyMongo companyMongo) throws InterruptedException {
        int retries = 3;
        while (retries > 0) {
            try {
                if (companyMongoRepository.save(companyMongo) != null) {
                    return true;
                }
            } catch (Exception e) {
                retries--;
                Thread.sleep(1000);
            }
        }
        System.out.println("Error to insert document");
        return false;
    }

    public void aggregation() {
        CompanyMongo company = new CompanyMongo("Canon", "Украина");
        for(int i =0; i<100000;i++) {
            companyMongoRepository.save(new CompanyMongo("Canon"+i, "Украина"+i%100));
        }
        TypeMongo type = new TypeMongo("тип", "описание");
        ModelMongo model = new ModelMongo("модель", 1000, 1.2, "тип чернил");
        for(int i =0; i<100000;i++) {
            printerMongoRepository.save(new PrinterMongo("принтер"+i, 100.0+i%10000, "Черный", type, model, company));
        }

        //1Agg
        long startTime = System.nanoTime();
        // for (Count p : printerMongoRepository.countAllByModelMongo_Brand()) {
        //    System.out.println(p);
        // }
        printerMongoRepository.countAllByCountry();
        long endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//1Not
        startTime = System.nanoTime();
        List<PrinterMongo> temp = printerMongoRepository.findAll();
        HashMap<String, Integer> countByCountry = new HashMap<>();
        for (PrinterMongo p : temp) {
            Integer frequency = countByCountry.get(p.getCompanyMongo().getCountry());
            countByCountry.put(p.getCompanyMongo().getCountry(), frequency == null ? 1 : frequency + 1);
        }
        List<Count> result = new ArrayList<Count>();
        for (Map.Entry<String, Integer> entry : countByCountry.entrySet()) {
            result.add(new Count(entry.getKey(), entry.getValue()));
        }

        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);
//2Agg
        startTime = System.nanoTime();
        // for (Count p : printerMongoRepository.countAllByModelMongo_Country()) {
        //     System.out.println(p);
        // }

        printerMongoRepository.countAllByFirm();
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//2Not
        startTime = System.nanoTime();
        temp = printerMongoRepository.findAll();
        HashMap<String, Integer> countByFirm = new HashMap<>();
        for (PrinterMongo p : temp) {
            Integer frequency = countByFirm.get(p.getCompanyMongo().getFirm());
            countByFirm.put(p.getCompanyMongo().getFirm(), frequency == null ? 1 : frequency + 1);
        }
        result = new ArrayList<Count>();
        for (Map.Entry<String, Integer> entry : countByFirm.entrySet()) {
            result.add(new Count(entry.getKey(), entry.getValue()));
        }

        //  for (Count p : result) {
        //     System.out.println(p);
        // }

        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);
//3 Agg
        startTime = System.nanoTime();

        // for (PrinterMongo p : printerMongoRepository.findAllByPriceMin()) {
        //      System.out.println(p);
        //  }

        printerMongoRepository.findAllByPriceMin();
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//3Not
        startTime = System.nanoTime();
        temp = printerMongoRepository.findAll();
        Double min_price = 1000000.0;
        for (PrinterMongo p : temp) {
            if (p.getPrice() < min_price) {
                min_price = p.getPrice();
            }
        }
        List<PrinterMongo> result3 = new ArrayList<PrinterMongo>();
        for (PrinterMongo p : temp) {
            if (p.getPrice() == min_price) {
                result3.add(p);
            }
        }
        //  for (PrinterMongo p : result3) {
        //      System.out.println(p);
        //  }
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//4 Agg
        startTime = System.nanoTime();
        // for (CartridgeMongo c : cartridgeMongoRepository.matchColors()) {
        //      System.out.println(c);
        //   }
        companyMongoRepository.matchFirm();
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

        //4Not
        startTime = System.nanoTime();
        //   for (CartridgeMongo c : cartridgeMongoRepository.findAllByColors("black")) {
        //      System.out.println(c);
        //   }
        companyMongoRepository.findAllByFirm("canon5");
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//5Agg
        startTime = System.nanoTime();
        //  for (Count c : cartridgeMongoRepository.countAllByColors()) {
        //       System.out.println(c);
        //   }
        companyMongoRepository.countAllByCountry();
        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

//5Not++
        startTime = System.nanoTime();
        List<CompanyMongo> temp2 = companyMongoRepository.findAll();
        HashMap<String, Integer> countByCountries = new HashMap<>();
        for (CompanyMongo c : temp2) {
            Integer frequency = countByCountries.get(c.getCountry());
            countByCountries.put(c.getCountry(), frequency == null ? 1 : frequency + 1);
        }
        result = new ArrayList<Count>();
        for (Map.Entry<String, Integer> entry : countByCountries.entrySet()) {
            result.add(new Count(entry.getKey(), entry.getValue()));
        }
        //  for (Count c : result) {
        //      System.out.println(c);
        //  }

        endTime = System.nanoTime();
        System.out.println((double) (endTime - startTime) / 1000000000);

    }
}
