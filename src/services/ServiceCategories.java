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
import entities.Categories;
import entities.Repa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;


public class ServiceCategories {

    public ArrayList<Categories> categories;

    public static ServiceCategories instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategories() {
        req = new ConnectionRequest();
    }

    public static ServiceCategories getInstance() {
        if (instance == null) {
            instance = new ServiceCategories();
        }
        return instance;
    }

   /*  public boolean addRepa(Repa r) {
        System.out.println(r);
        System.out.println("********");
       String url = Statics.BASE_URL + "addRepaJSON?libProd=" 
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
       req.addArgument("categorie", r.getCategorie());
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
       req.addArgument("categorie", r.getCategorie());
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
     
     
     */
    public ArrayList<Categories> parseCategories(String jsonText) {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categories c = new Categories();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
  
                c.setCategorie(obj.get("categorie").toString());


                
                categories.add(c);
            }

        } catch (IOException ex) {

        }
        return categories;
    }

    public ArrayList<Categories> getAllCategories() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "getCategoriesJSON";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
               // System.out.println(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

           public boolean addCategories(Categories c) {
        System.out.println(c);
        System.out.println("********");
       String url = Statics.BASE_URL + "addCategorieJSON?categorie=" 
               + c.getCategorie();
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
         public boolean deleteCategorie(int id) {
        System.out.println(id);
        System.out.println("********");
       String url = Statics.BASE_URL + "deleteCategorieJSON/"+id; 
  
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
        
}
