package com.nosql.SQLEntity;


import javax.persistence.*;
import java.util.List;

@Entity
public class CompanySQL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firm", nullable = true)
    private String firm;
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<PrinterSQL> printersSQL;

    public CompanySQL() {
    }

    public CompanySQL(String firm, String country) {
        this.firm = firm;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<PrinterSQL> getPrintersSQL() {
        return printersSQL;
    }

    public void setPrintersSQL(List<PrinterSQL> printersSQL) {
        this.printersSQL = printersSQL;
    }

    @Override
    public String toString() {
        return "CompanySQL{" +
                "id=" + id +
                ", firm='" + firm + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
