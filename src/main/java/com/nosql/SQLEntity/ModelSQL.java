package com.nosql.SQLEntity;


import javax.persistence.*;
import java.util.List;

@Entity
public class ModelSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "print_capacity", nullable = false)
    private Integer print_capacity;

    @Column(name = "life_time", nullable = false)
    private Double life_time;

    @Column(name = "ink_type")
    private String ink_type;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<PrinterSQL> printersSQL;

    public static class Builder {
        private final String model;
        private final Integer print_capacity;
        private Double life_time;
        private String ink_type;
        public Builder(String model,Integer print_capacity) {
            this.model = model;
            this.print_capacity = print_capacity;
        }

        public Builder life_time(Double life_time) {
            this.life_time = life_time;
            return this;
        }
        public Builder ink_type(String ink_type) {
            this.ink_type = ink_type;
            return this;
        }

        public ModelSQL build() {
            return new ModelSQL(this);
        }
    }

    private ModelSQL(Builder builder) {
        this.model = builder.model;
        this.print_capacity = builder.print_capacity;
        this.life_time=builder.life_time;
        this.ink_type=builder.ink_type;
    }

    public ModelSQL() {
    }

    public ModelSQL(String model, Integer print_capacity, Double life_time, String ink_type) {
        this.model = model;
        this.print_capacity = print_capacity;
        this.life_time = life_time;
        this.ink_type = ink_type;
    }

    public List<PrinterSQL> getPrintersSQL() {
        return printersSQL;
    }

    public void setPrintersSQL(List<PrinterSQL> printersSQL) {
        this.printersSQL = printersSQL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "ModelSQL{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", print_capacity=" + print_capacity +
                ", life_time=" + life_time +
                ", ink_type='" + ink_type + '\'' +
                '}';
    }
}

