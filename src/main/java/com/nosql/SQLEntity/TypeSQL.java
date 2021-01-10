package com.nosql.SQLEntity;

import javax.persistence.*;
import java.util.List;


@Entity
public class TypeSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<PrinterSQL> printersSQL;

    public TypeSQL() {
    }

    public TypeSQL(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PrinterSQL> getPrintersSQL() {
        return printersSQL;
    }

    public void setPrintersSQL(List<PrinterSQL> printersSQL) {
        this.printersSQL = printersSQL;
    }

    @Override
    public String toString() {
        return "TypeSQL{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
