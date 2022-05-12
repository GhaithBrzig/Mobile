
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.livreurCN;
import com.mycompany.myapp.conn.connCN;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author islemferchichi
 */
public class ServiceLivreur {
     public ArrayList<livreurCN> livreurs;
     public static ServiceLivreur instance; 
     public boolean resultOk;
     private ConnectionRequest req;

    public ServiceLivreur() {
        req = new ConnectionRequest();
    }
    public static ServiceLivreur getInstance(){
      if (instance == null)
        instance = new ServiceLivreur();
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
    public ArrayList<livreurCN> parseLivreurs(String jsonText){
        try {
        livreurs = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> livreursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        List<Map<String,Object>> list = (List<Map<String,Object>>) livreursListJson.get("root");
            for(Map<String,Object> obj : list){
            livreurCN l= new livreurCN();
            int tellivreur =Integer.parseInt(obj.get("tellivreur").toString());
            float id = Float.parseFloat(obj.get("idlivreur").toString());
            l.setIdLivreur((int)id);
            l.setNomLivreur(obj.get("nomlivreur").toString());
            l.setPrenomLivreur(obj.get("prenomlivreur").toString());
            l.setTelLivreur((int) tellivreur);
           
             livreurs.add(l);    
        }
       
        } catch (IOException ex) {
                
        }
        return livreurs;
    
    }
    
    public ArrayList<livreurCN> getAllLivreurs(){
        
        String url = connCN.BASE_URL + "getLivreurJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               livreurs = parseLivreurs(new String(req.getResponseData() ));
               req.removeResponseListener(this);
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreurs;
        
}
    
    
}

