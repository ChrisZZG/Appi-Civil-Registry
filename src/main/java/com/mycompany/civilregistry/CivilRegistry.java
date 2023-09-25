/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.civilregistry;

import com.mycompany.civilregistry.IGU.Principal;

/**
 *
 * @author crist
 */
public class CivilRegistry {

    public static void main(String[] args) {
        
        //INSTANCIA DE PANTALLA PRINCIPAL
        Principal principal = new Principal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
    }
}
