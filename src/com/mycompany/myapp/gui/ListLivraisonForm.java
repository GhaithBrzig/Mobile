/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.entities.livraisonCN;
import com.mycompany.myapp.service.ServiceLivraison;
/**
 *
 * @author islemferchichi
 */

    
public class ListLivraisonForm extends Form {

    public ListLivraisonForm(Form previous) {
        setTitle("List Livraison");

        SpanLabel sp = new SpanLabel();
        String list ="";
        for(livraisonCN l : ServiceLivraison.getInstance().getAllLivraison())
        {
            list += l.toString() + "\n";
        }
        sp.setText(list);
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}



       


