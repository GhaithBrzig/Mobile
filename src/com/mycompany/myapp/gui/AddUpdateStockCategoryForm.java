

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.codename1.ui.Form;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.StockCategory;
import com.mycompany.myapp.services.ServiceStock;
import com.mycompany.myapp.services.ServiceStockCategory;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class AddUpdateStockCategoryForm extends Form{
 private StockCategory stockCategory;

    public AddUpdateStockCategoryForm(Form previous, boolean add, StockCategory c) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 
                    ee -> previous.showBack()
            );
            this.setTitle("Stock Category");

            Container nomContainer = new Container(BoxLayout.x());
           

            
            
            TextArea descriptionCA = new TextArea("", 10,100);
            descriptionCA.setMaxSize(9999);
            descriptionCA.getAllStyles().setAlignment(TextArea.CENTER);

            TextField nomCF = new TextField();
            nomCF.getAllStyles().setAlignment(TextArea.CENTER);
           
            
            
            Button submitBtn = new Button("");
           
            nomContainer.addAll(new Label("nom: "),nomCF);
           

            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
            
          
            
            submitBtn.addActionListener(ee->{
                if(nomCF.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
                ServiceStockCategory.getInstance().addStockCategory(nomCF.getText());
            new ListStockCategoriesForm().show();
            });
                
              this.addAll(nomContainer, submitBtn);

            }
            else
            {
            submitBtn.setText("Modifier");
            
            nomCF.setText(c.getNom());
          

            submitBtn.addActionListener(ee->{
                                if(nomCF.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
             ServiceStockCategory.getInstance().updateStockCategory(c.getId(),nomCF.getText());
            new ListStockCategoriesForm().show();
            });
            
                this.addAll(nomContainer, submitBtn);

            }

            this.setLayout(BoxLayout.y());

    }

   
    
}
