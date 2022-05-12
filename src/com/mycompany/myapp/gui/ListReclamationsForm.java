/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.mycompany.myapp.entities.Reclamation;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceStock;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListReclamationsForm extends Form {

  public ListReclamationsForm() {
        setTitle("Reclamations");
        getToolbar().addMaterialCommandToSideMenu("Stock List", FontImage.MATERIAL_EVENT, e -> 
                new ListStocksForm().show()
        );
        getToolbar().addMaterialCommandToSideMenu("Categories List ", FontImage.MATERIAL_BOOK, e -> 
                new ListStockCategoriesForm().show()
        );
         getToolbar().addMaterialCommandToSideMenu("Reclamations List ", FontImage.MATERIAL_BOOK, e -> 
                new ListReclamationsForm().show()
        );
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
                new AddUpdateReclamationForm(this,true,new Reclamation()).show();
            
        });
         
        Container reclamationContainer = new Container();

        ArrayList<Reclamation> reclamations = ServiceReclamation.getInstance().getAllReclamations();

        
        
        
        for (Reclamation r : reclamations) {
         reclamationContainer.add(this.addReservationsHolder(r));
        }
                
        this.addAll(reclamationContainer);

    }

    private Container addReservationsHolder(Reclamation r) {
        try {
           
            Container holderContainer = new Container(BoxLayout.x());
            Container detailsContainer = new Container(BoxLayout.y());
            Container buttonContainer = new Container(BoxLayout.x());

            // EncodedImage spinner = EncodedImage.create("/spinner.png");
            //String url = "https://avatars.dicebear.com/api/bottts/" + c.getNom() + ".png";
            //Image gameImage = URLImage.createToStorage(spinner, url, url, URLImage.RESIZE_SCALE);
            //ImageViewer image = new ImageViewer(gameImage);
 
   SpanLabel sp = new SpanLabel();
   sp.setText("nom Client :");
            Label lbNom = new Label(r.getNomClient());
                 SpanLabel sp2 = new SpanLabel();
   sp2.setText("Email Client :");
            Label lbUnite = new Label(r.getEmailClient());
               SpanLabel sp3 = new SpanLabel();
   sp3.setText("Description :");
            Label lbQuantite = new Label(r.getDescription());
         
             SpanLabel sp6 = new SpanLabel();
   sp.setText("");
    SpanLabel sp7 = new SpanLabel();
   sp.setText("");
            
         

            



            Button btUpdate = new Button("Solve");
            Button btDelete = new Button("Supprimer");
            
            btUpdate.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0x1E90FF).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btUpdate.getAllStyles().setFgColor(0xffffff);
            
            btDelete.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xFF0000).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btDelete.getAllStyles().setFgColor(0xffffff);
            
            
            buttonContainer.addAll(btUpdate, btDelete);
            detailsContainer.addAll(sp,lbNom,sp2, lbUnite,sp3,lbQuantite,sp6,sp7,buttonContainer);
            holderContainer.addAll(detailsContainer);

            btUpdate.addActionListener(ee -> {
                ServiceReclamation.getInstance().updateReclamation(r.getId());
                                new ListReclamationsForm().show();

            });
 
           btDelete.addActionListener(ee -> {
                    ServiceReclamation.getInstance().deleteReclamation(r.getId());
                new ListReclamationsForm().show();
            });
            
            return holderContainer;
            
        } catch (NullPointerException ee) {
            System.err.println(ee.getMessage());
        } 
        
        return new Container(BoxLayout.x());
    }
}
