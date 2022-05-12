/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamations;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(String nomClient,String emailClient,String description) {
         String url = Statics.BASE_URL + "JsonNewRec?nomClient=" 
               + nomClient + 
               "&emailClient=" + emailClient+ 
               "&description=" + description;
             

     
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
        
         public boolean updateReclamation(int id) {
        
       String url = Statics.BASE_URL + "UpdateReclamationJSON?id=" 
               + id;
      // String url = Statics.BASE_URL + "addRepaJSON";
    
       req.setUrl(url);
               req.setPost(true);

     //  req.setPost(false);
    /*   req.addArgument("libProd", r.getLibProd());
       req.addArgument("description", r.getDescription());
       req.addArgument("prixProd", r.getPrixProd());
       req.addArgument("quantite", r.getQuantite());
       req.addArgument("remise", r.getRemise());
       req.addArgument("categorie", r.getCategorie());*/
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    
    }

    public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> reclamationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)reclamationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString()); 
              

                


                t.setId((int)id);
                t.setNomClient(obj.get("nomclient").toString());               
                t.setEmailClient(obj.get("emailclient").toString());   
                                t.setDescription(obj.get("description").toString());                

              
                reclamations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
    

    public ArrayList<Reclamation> getAllReclamations(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"JsonGetReclamation";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    
     public boolean deleteReclamation(int id) {
         String url = Statics.BASE_URL + "DeleteReclamationJSON?id=" 
               + id;
        req.setUrl(url);
        req.setPost(true);
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
