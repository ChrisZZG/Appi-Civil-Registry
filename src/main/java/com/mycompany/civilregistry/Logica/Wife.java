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


/**
 *
 * @author crist
 */
@Entity
public class Wife implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idWife;
    @Basic
    private String nameWife;
    private String lastNameWife;
    private String dniWife;
    private String birthdateWife;

    public Wife() {
    }

    public Wife(int idWife, String nameWife, String lastNameWife, String dniWife, String birthdateWife) {
        this.idWife = idWife;
        this.nameWife = nameWife;
        this.lastNameWife = lastNameWife;
        this.dniWife = dniWife;
        this.birthdateWife = birthdateWife;
    }

    public int getIdWife() {
        return idWife;
    }

    public void setIdWife(int idWife) {
        this.idWife = idWife;
    }

    public String getNameWife() {
        return nameWife;
    }

    public void setNameWife(String nameWife) {
        this.nameWife = nameWife;
    }

    public String getLastNameWife() {
        return lastNameWife;
    }

    public void setLastNameWife(String lastNameWife) {
        this.lastNameWife = lastNameWife;
    }

    public String getDniWife() {
        return dniWife;
    }

    public void setDniWife(String dniWife) {
        this.dniWife = dniWife;
    }

    public String getBirthdateWife() {
        return birthdateWife;
    }

    public void setBirthdateWife(String birthdateWife) {
        this.birthdateWife = birthdateWife;
    }

    
    
}
