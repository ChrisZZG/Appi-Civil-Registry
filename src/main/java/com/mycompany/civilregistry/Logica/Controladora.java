/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.civilregistry.Logica;

import com.mycompany.civilregistry.Persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author crist
 */
public class Controladora {
    //INSTANCIAS Y VARIABLES GLOBALES
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void save(String nameHusband, String lastNameHusband, String dniHusband, 
            String birthdateHusband, String nameWife, String lastNameWife, String dniWife,
            String birthdateWife) {
        
         //WIFE
        Wife wife = new Wife();
        //SETTEAR SUS VALORES
        wife.setBirthdateWife(birthdateWife);
        wife.setDniWife(dniWife);
        wife.setLastNameWife(lastNameWife);
        wife.setNameWife(nameWife);
        
        
        //HUSBAND
        Husband husband = new Husband();
        //SETTEAR SUS VALORES
        husband.setBirthdateHusband(birthdateHusband);
        husband.setDniHusband(dniHusband);
        husband.setLastNameHusband(lastNameHusband);
        husband.setNameHusband(nameHusband);
        husband.setWife(wife);
       
        
        
       
        
        //CREAR EL METODO PARA GUARDAR HUSBAND Y WIFE EN LA CONTROLADORA DE PERSISTENCIA
        controlPersis.save(husband, wife);
        
    }

    /*LLENAR ESTA LISTA CON TODAS LAS ENTIDADES RELACIONADAS QUE TENGO EN LA BASE DE
    DATOS
    */
    public List<Husband> getMarriages() {
        return controlPersis.findMarriagesEntities();
    }
    
    //ELIMINAR UN MATRIMONIO A PARTIR DE ELIMINAR UN HUSBAND POR ID
    public void delete(int idHusband) {
        controlPersis.delete(idHusband);
    }

    //METODO PARA ENCONTRA UN HUSBAND A PARTIR DEL ID
    public Husband findHusband(int idHusband) {
        return controlPersis.findHusband(idHusband);
    }

    //METODO MODIFICAR UN HUSBAND Y GUARDARLO EN LA BASE DE DATOS
    public void modifyHusband(Husband husband, String nameHusband, String lastNameHusband,
            String dniHusband, String birthdateHusband, String nameWife, 
            String lastNameWife, String dniWife, String birthdateWife) {

        //HUSBAND
        
        /*SETTEO LOS VALORES DE HUSBAND ORIGINAL Q TRAJE POR ID CON LO QUE SE
        CAMBIO POR LOS IMPUTS Y SE REASIGNO
        */
        
        husband.setNameHusband(nameHusband);
        husband.setLastNameHusband(lastNameHusband);
        husband.setDniHusband(dniHusband);
        husband.setBirthdateHusband(birthdateHusband);
        /*LE PASAO A LA CONTROLADORA LAS MODIFICACIONES DE HUSBAND EN UN
        METODO INDEPENDIENTE
        */
        controlPersis.modifyHusband(husband);
    
        //WIFE
        /*PARA LOS DATOS DE WIFE, CREO UN METODO QUE ME TRAIGA A WIFE A PARTIR
        DEL ID RELACIONADO, LE SETTEO LOS VALORES Y LO GUARDO EN LA CONTROLADORA
        DE PERSISTENCIA A TRAVES DE UN METODO INDEPENDIENTE
        */
        Wife wife = this.findWife(husband.getWife().getIdWife());
        
        /*Una vez que el metodo fue fue a la controladora de persistencia y de 
        alli a la base de datos y me trajo Wife relacionado, le Setteo sus valores
        */
        
        wife.setNameWife(nameWife);
        wife.setLastNameWife(lastNameWife);
        wife.setDniWife(dniWife);
        wife.setLastNameWife(lastNameWife);
        
        /*Una vez modificado, creo un metodo que almacene estas modificaciones
        y las lleve a la Base de Datos
        */
        this.modifyWife(wife);
       
    
        
    }

    private Wife findWife(int idWife) {
        return controlPersis.findWife(idWife);
    }

    private void modifyWife(Wife wife) {
        controlPersis.modifyWife(wife);
    }
}
