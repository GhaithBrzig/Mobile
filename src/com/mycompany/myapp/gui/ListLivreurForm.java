/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.service.ServiceLivreur;
import com.mycompany.myapp.entities.livreurCN;
/**
 *
 * @author islemferchichi
 */

    
public class ListLivreurForm extends Form {

    public ListLivreurForm(Form previous) {
        setTitle("List Livreur");

        SpanLabel sp = new SpanLabel();
        String list ="";
        for(livreurCN l : ServiceLivreur.getInstance().getAllLivreurs())
        {
            list += l.toString() + "\n";
        }
        sp.setText(list);
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}



       


