/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.civilregistry.Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author crist
 */
@Entity
public class Husband implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idHusband;
    @Basic
    private String nameHusband;
    private String lastNameHusband;
    private String dniHusband;
    private String birthdateHusband;
    @OneToOne
    private Wife wife;

    public Husband() {
    }

    public Husband(int idHusband, String nameHusband, String lastNameHusband, String dniHusband, String birthdateHusband, Wife wife) {
        this.idHusband = idHusband;
        this.nameHusband = nameHusband;
        this.lastNameHusband = lastNameHusband;
        this.dniHusband = dniHusband;
        this.birthdateHusband = birthdateHusband;
        this.wife = wife;
    }

    public int getIdHusband() {
        return idHusband;
    }

    public void setIdHusband(int idHusband) {
        this.idHusband = idHusband;
    }

    public String getNameHusband() {
        return nameHusband;
    }

    public void setNameHusband(String nameHusband) {
        this.nameHusband = nameHusband;
    }

    public String getLastNameHusband() {
        return lastNameHusband;
    }

    public void setLastNameHusband(String lastNameHusband) {
        this.lastNameHusband = lastNameHusband;
    }

    public String getDniHusband() {
        return dniHusband;
    }

    public void setDniHusband(String dniHusband) {
        this.dniHusband = dniHusband;
    }

    public String getBirthdateHusband() {
        return birthdateHusband;
    }

    public void setBirthdateHusband(String birthdateHusband) {
        this.birthdateHusband = birthdateHusband;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
    
    
}
