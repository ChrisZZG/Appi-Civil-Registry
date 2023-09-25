/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.civilregistry.Persistencia;

import com.mycompany.civilregistry.Logica.Husband;
import com.mycompany.civilregistry.Logica.Wife;
import com.mycompany.civilregistry.Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crist
 */
public class ControladoraPersistencia {
    //INSTANCIAS Y VARIABLES GLOBALES
    HusbandJpaController husbandJpa = new HusbandJpaController();
    WifeJpaController wifeJpa = new WifeJpaController();

    public void save(Husband husband, Wife wife) {
        
         //GUARDAR WIFE EN LA BASE DE DATOS
        wifeJpa.create(wife);
        //GUARDAR HUSBAND EN LA BASE DE DATOS
        husbandJpa.create(husband);
       
    }

    /*METODO QUE ME TRAE TODAS LAS ENTIDADES RELACIONADAS QUE TENGO
    EN LA BASE DE DATOS PARA QUE PUEDA MOSTRARLAS EN LA VISTA 
    
    USO LA ENTIDAD HUSBAND PARA TRAER LOS DATOS DE WIFE, YA QUE 
    DEVIDO A LA RELACION ONE TO ONE, HUSBAND CONOCE A WIFE
    */
    public List<Husband> findMarriagesEntities() {
        return husbandJpa.findHusbandEntities();
    }

    //ELIMINAR UNA RELACION DE TIPO MARRIED A PARTIR DEL UN HUSBAND ESPECIFICO
    public void delete(int idHusband) {
        try {
            husbandJpa.destroy(idHusband);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ENCONTRAR HUSBAND POR ID
    public Husband findHusband(int idHusband) {
        return husbandJpa.findHusband(idHusband);
    }
    //MODIFICAR HUSBAND Y GUARDAR EN LA BASE DE DATOS
    public void modifyHusband(Husband husband) {
        try {
            husbandJpa.edit(husband);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //ENCONTRAR WIFE POR ID
    public Wife findWife(int idWife) {
        return wifeJpa.findWife(idWife);
    }
    //MODIFICAR WIFE Y GUARDARLO EN LA BASE DE DATOS
    public void modifyWife(Wife wife) {
        try {
            wifeJpa.edit(wife);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
