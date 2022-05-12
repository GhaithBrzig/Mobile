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
import com.mycompany.myapp.entities.StockCategory;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceStockCategory {

    public ArrayList<StockCategory> stockCategories;
    
    public static ServiceStockCategory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceStockCategory() {
         req = new ConnectionRequest();
    }

    public static ServiceStockCategory getInstance() {
        if (instance == null) {
            instance = new ServiceStockCategory();
        }
        return instance;
    }

    public boolean addStockCategory(String nom) {
         String url = Statics.BASE_URL + "JsonNewCat?nom=" 
               + nom ;

     
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
  
        
         public boolean updateStockCategory(int id,String nom) {
        
       String url = Statics.BASE_URL + "UpdateCatJSON?id=" 
               + id+
               "&nom=" + nom;
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

    public ArrayList<StockCategory> parseStockCategories(String jsonText){
        try {
            stockCategories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> stockCategoriesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)stockCategoriesListJson.get("root");
            for(Map<String,Object> obj : list){
                StockCategory c = new StockCategory();
                float id = Float.parseFloat(obj.get("id").toString());               
              


                c.setId((int)id);


                if (obj.get("nom")==null)
              c.setNom("null");
                else
                    c.setNom(obj.get("nom").toString());
                stockCategories.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return stockCategories;
    }
    

    public ArrayList<StockCategory> getAllStockCategories(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"JsonGetCat";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stockCategories = parseStockCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stockCategories;
    }
    
     public boolean deleteStockCategory(int id) {
         String url = Statics.BASE_URL + "DeleteCatJSON?id=" 
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
