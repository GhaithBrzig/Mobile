
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.livreurCN;
import com.mycompany.myapp.conn.connCN;
import com.mycompany.myapp.entities.livraisonCN;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author islemferchichi
 */
public class ServiceLivraison {
     public ArrayList<livraisonCN> livraison;
     public static ServiceLivraison instance; 
     public boolean resultOk;
     private ConnectionRequest req;

    public ServiceLivraison() {
        req = new ConnectionRequest();
    }
    public static ServiceLivraison getInstance(){
      if (instance == null)
        instance = new ServiceLivraison();
     return instance;
    }
    
    public boolean suppLivreur(int id) {
    
    
     System.out.println(id);
       System.out.println("********");
       String url = connCN.BASE_URL + "deleteLivreurJSON/" 
               + id; 
        req.setUrl(url);
        req.setPost(true);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
      
    
    
    }
    
    
    public boolean updateLivreur(int id , livreurCN l ){
       System.out.println(l);
       System.out.println("********");
       String url = connCN.BASE_URL + "updateLivreurJSON/" + id + "?nomlivreur=" 
               + l.getNomLivreur() +
               "&prenomlivreur=" + l.getPrenomLivreur()+
               "&tellivreur=" + l.getTelLivreur();
               
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
    public boolean addLivreur(livreurCN l){
       System.out.println(l);
       System.out.println("********");
       String url = connCN.BASE_URL + "addLivreurJSON?nomlivreur=" 
               + l.getNomLivreur() +
               "&prenomlivreur=" + l.getPrenomLivreur()+
               "&tellivreur=" + l.getTelLivreur();
               
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
       /* String url = connCN.BASE_URL+"/addLivreurJSON/"+l.getNomLivreur()+"/"+l.getPrenomLivreur()+"/"+l.getTelLivreur();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOk= req.getResponseCode()==200;
              req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return resultOk;*/
       
                } 
    public ArrayList<livraisonCN> parseLivraison(String jsonText){
        try {
        livraison = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> livraisonListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        List<Map<String,Object>> list = (List<Map<String,Object>>) livraisonListJSON.get("root");
            for(Map<String,Object> obj : list){
            livraisonCN l= new livraisonCN();
            l.setNomLivreur(obj.get("idlivreur").toString());
            l.setCommande(obj.get("idCommande").toString());
            l.setFraisdeLivraison(Float.parseFloat(obj.get("fraisdelivraison").toString())); 
      //l.setIdLivraison(Integer.parseInt(obj.get("idlivraison").toString()));
             livraison.add(l);    
        }
       
        } catch (IOException ex) {
                
        }
        return livraison;
    
    }
    
    public ArrayList<livraisonCN> getAllLivraison(){
        
        String url = connCN.BASE_URL + "getLivraisonJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               livraison = parseLivraison(new String(req.getResponseData() ));
               req.removeResponseListener(this);
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraison;
        
}
    
    
}

