

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.mycompany.myapp.entities.Stock;
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
import com.mycompany.myapp.entities.Stock;
import com.mycompany.myapp.services.ServiceStock;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class AddUpdateStockForm extends Form{
 private Stock stock;

    public AddUpdateStockForm(Form previous, boolean add, Stock s) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 
                    ee -> previous.showBack()
            );
            this.setTitle("Stock");

            Container nomContainer = new Container(BoxLayout.x());
             Container uniteContainer = new Container(BoxLayout.x());
            Container quantiteContainer = new Container(BoxLayout.x());
             Container prixUnitaireContainer = new Container(BoxLayout.y()); 
             Container categorieContainer = new Container(BoxLayout.y());

            
            
            TextArea descriptionTA = new TextArea("", 10,100);
            descriptionTA.setMaxSize(9999);
            descriptionTA.getAllStyles().setAlignment(TextArea.CENTER);

            TextField nomTF = new TextField();
            nomTF.getAllStyles().setAlignment(TextArea.CENTER);
            TextField uniteTF = new TextField();
            uniteTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField quantiteTF = new TextField();
            quantiteTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField prixUnitaireTF = new TextField();
            prixUnitaireTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            
            Button submitBtn = new Button("");
           
            nomContainer.addAll(new Label("nom: "),nomTF);
            uniteContainer.addAll(new Label("unite: "),uniteTF);
            quantiteContainer.addAll(new Label("quantite: "),quantiteTF);
            prixUnitaireContainer.addAll(new Label("prixUnitaire: "),prixUnitaireTF);

            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
            
            ComboBox<String> StockCB = new ComboBox<>();
            ArrayList<Stock> stocks = ServiceStock.getInstance().getAllStocks();
            for (Stock stock : stocks) {
                StockCB.addItem(Integer.toString(2));
            }
            categorieContainer.addAll(new Label("Categorie:"),StockCB);
            
            submitBtn.addActionListener(ee->{
                if(nomTF.getText()==""||uniteTF.getText()==""||quantiteTF.getText()==""||prixUnitaireTF.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
            ServiceStock.getInstance().addStock(nomTF.getText(),uniteTF.getText(),quantiteTF.getText(),prixUnitaireTF.getText());
            new ListStocksForm().show();
            });
                
              this.addAll(nomContainer,uniteContainer, quantiteContainer,prixUnitaireContainer,categorieContainer, submitBtn);

            }
            else
            {
            submitBtn.setText("Modifier");
            
            nomTF.setText(s.getNom());
            uniteTF.setText(s.getUnite());
            quantiteTF.setText(String.valueOf(s.getQuantite()));
            prixUnitaireTF.setText(String.valueOf(s.getPrix_unitaire()));

            submitBtn.addActionListener(ee->{
                                if(nomTF.getText()==""||uniteTF.getText()==""||quantiteTF.getText()==""||prixUnitaireTF.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
             ServiceStock.getInstance().updateStock(s.getId(),nomTF.getText(),uniteTF.getText(),quantiteTF.getText(),prixUnitaireTF.getText());
            new ListStocksForm().show();
            });
            
                this.addAll(nomContainer,uniteContainer, quantiteContainer,prixUnitaireContainer, submitBtn);

            }

            this.setLayout(BoxLayout.y());

    }

   
    
}
