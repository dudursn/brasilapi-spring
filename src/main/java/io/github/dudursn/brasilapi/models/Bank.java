package io.github.dudursn.brasilapi.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String ispb;

    @NotNull
    private String name;

    @NotNull
    private int code;

    @NotNull
    @Column(name = "full_name")
    private String fullName;


    public long getId() {
        return id;
    }

    public String getIspb() {
        return ispb;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setIspb(String ispb) {
        this.ispb = ispb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}