/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Repa;
import services.ServiceRepas;

/**
 *
 * @author SBS
 */
public class MyMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Repa r = new Repa("okm","fds",5,6,"0","plat");
        
        try{
           
            System.out.println(ServiceRepas.getInstance());
        }catch(Exception e)
        {
            System.out.println(e);
        }
      //  ServiceRepas.getInstance().deleteRepa(81);
    }
    
}
