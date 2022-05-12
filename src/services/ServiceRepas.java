/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Repa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;


public class ServiceRepas {

    public ArrayList<Repa> repas;

    public static ServiceRepas instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceRepas() {
        req = new ConnectionRequest();
    }

    public static ServiceRepas getInstance() {
        if (instance == null) {
            instance = new ServiceRepas();
        }
        return instance;
    }

     public boolean addRepa(Repa r) {
        System.out.println(r);
        System.out.println("********");
       String url = Statics.BASE_URL + "addRepaJSON?libProd=" 
               + r.getLibProd() +
               "&description=" + r.getDescription()+ 
               "&prixProd=" + r.getPrixProd()+
               "&quantite=" + r.getQuantite()+ 
               "&remise=" + r.getRemise()+ 
               "&categorie=" + r.getCategorie() +
               "&path=" + r.getPath();
      // String url = Statics.BASE_URL + "addRepaJSON";
    
       req.setUrl(url);
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
     
        public boolean updateRepa(Repa r) {
        System.out.println(r);
        System.out.println("********");
       String url = Statics.BASE_URL + "updateRepaJSON/"+r.getId()+"?libProd=" 
               + r.getLibProd() +
               "&description=" + r.getDescription()+ 
               "&prixProd=" + r.getPrixProd()+
               "&quantite=" + r.getQuantite()+ 
               "&remise=" + r.getRemise()+ 
               "&categorie=" + r.getCategorie();
      // String url = Statics.BASE_URL + "addRepaJSON";
    
       req.setUrl(url);
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
        
     public boolean deleteRepa(int id) {
        System.out.println(id);
        System.out.println("********");
       String url = Statics.BASE_URL + "deleteRepaJSON/"+id; 
  
      // String url = Statics.BASE_URL + "addRepaJSON";
    
       req.setUrl(url);
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
     
     
     
    public ArrayList<Repa> parseRepas(String jsonText) {
        try {
            repas = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Repa r = new Repa();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prixProd").toString());
                float quantite = Float.parseFloat(obj.get("quantiteDispo").toString());
                r.setId((int) id);
                r.setPrixProd((int) prix);
                r.setQuantite((int) quantite);
                r.setLibProd(obj.get("libProd").toString());
                r.setDescription(obj.get("description").toString());
                r.setCategorie(obj.get("categorie").toString());
                r.setRemise(obj.get("remise").toString());
              //  System.out.println(obj.get("path"));
               // r.setRemise(obj.get("remise").toString());
                r.setPath(obj.get("path").toString());
              //  System.out.println(r);

                
                repas.add(r);
            }

        } catch (IOException ex) {

        }
        return repas;
    }

    public ArrayList<Repa> getAllRepas() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "getRepasJSON";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                repas = parseRepas(new String(req.getResponseData()));
               // System.out.println(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return repas;
    }

        
        
        
}
