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
import com.mycompany.myapp.entities.Stock;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceStock {

    public ArrayList<Stock> stocks;
    
    public static ServiceStock instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceStock() {
         req = new ConnectionRequest();
    }

    public static ServiceStock getInstance() {
        if (instance == null) {
            instance = new ServiceStock();
        }
        return instance;
    }

    public boolean addStock(String nom,String unite,String quantite, String prix_unitaire) {
         String url = Statics.BASE_URL + "JsonNew?nom=" 
               + nom + 
               "&unite=" + unite+ 
               "&quantite=" + quantite+
               "&prixunitaire=" + prix_unitaire+
                "&prixtotal=" + Integer.parseInt(prix_unitaire)*Integer.parseInt(quantite)
;
     
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
  
        
         public boolean updateStock(int id,String nom,String unite ,String quantite,String prix_unitaire) {
        
       String url = Statics.BASE_URL + "UpdateJSON?id=" 
               + id+
               "&nom=" + nom+ 
               "&unite=" + unite+
               "&quantite=" + quantite+ 
               "&prixunitaire=" + prix_unitaire+
                  "&prixtotal=" + Integer.parseInt(prix_unitaire)*Integer.parseInt(quantite);
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

    public ArrayList<Stock> parseStocks(String jsonText){
        try {
            stocks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> stocksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)stocksListJson.get("root");
            for(Map<String,Object> obj : list){
                Stock t = new Stock();
                float id = Float.parseFloat(obj.get("id").toString());               
                float quantite = Float.parseFloat(obj.get("quantite").toString());               
                float prix_unitaire = Float.parseFloat(obj.get("prixUnitaire").toString());
                float prix_total = Float.parseFloat(obj.get("prixTotal").toString());


                t.setId((int)id);
                t.setUnite(obj.get("unite").toString());               
                t.setQuantite((int)quantite);                
                t.setPrix_unitaire(prix_unitaire);
                t.setPrix_total(prix_total);

                if (obj.get("nom")==null)
              t.setNom("null");
                else
                    t.setNom(obj.get("nom").toString());
                stocks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return stocks;
    }
    

    public ArrayList<Stock> getAllStocks(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"JsonGet";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stocks = parseStocks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stocks;
    }
    
     public boolean deleteStock(int id) {
         String url = Statics.BASE_URL + "DeleteJSON?id=" 
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
