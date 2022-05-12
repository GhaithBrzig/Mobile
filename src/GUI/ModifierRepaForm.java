/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Categories;
import entities.Repa;
import services.ServiceCategories;
import services.ServiceRepas;
/**
 *
 * @author bhk
 */
public class ModifierRepaForm extends Form{

    
    public ModifierRepaForm(Form previous,Repa ro) {
        setTitle("Update repa");
        setLayout(BoxLayout.y());
        
        TextField tfLib = new TextField(ro.getLibProd(),"Nom");
        TextField tfDescription= new TextField(ro.getDescription(), "Description");
       // TextField tfCategorie= new TextField("", "Categorie");
       ComboBox<String> cbc = new ComboBox();
       ComboBox<String> cbr = new ComboBox();
       for(Categories c :  ServiceCategories.getInstance().getAllCategories())
       {
       cbc.addItem(c.getCategorie());
       
       }
      // cbc.setSelectedIndex(Integer.parseInt(r.getRemise()));
        TextField tfPrix= new TextField(Integer.toString(ro.getPrixProd()), "Prix");
        TextField tfQuantite= new TextField(Integer.toString(ro.getQuantite()), "Quantite");
        
        cbr.addItem("0%");
        cbr.addItem("20%");
        cbr.addItem("40%");
        cbr.addItem("60%");
        cbr.addItem("80%");
        cbr.setSelectedIndex(Integer.parseInt(ro.getRemise()));
        Button btnValider = new Button("Update");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLib.getText().length()==0)||(tfDescription.getText().length()==0)||(tfPrix.getText().length()==0)||(tfQuantite.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Repa r = new Repa(tfLib.getText().toString(), tfDescription.getText().toString(), Integer.parseInt(tfPrix.getText()), Integer.parseInt(tfQuantite.getText()), Integer.toString(cbr.getCurrentSelected()),ServiceCategories.getInstance().getAllCategories().get(cbc.getCurrentSelected()).getCategorie(),"ok" );
                        r.setId(ro.getId());
                        if( ServiceRepas.getInstance().updateRepa(r))
                        {
                           Dialog.show("Success","Repa modifié",new Command("OK"));
                          // new HomeForm().show();
                                new ListRepasForm(new HomeForm()).show();

                          
                        }else
                            Dialog.show("ERROR", "Nom deja existe", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix or quantite must be a number", new Command("OK"));
                    }
                    
                }
              //  System.out.println(ServiceCategories.getInstance().getAllCategories().get(cbc.getCurrentSelected()).getCategorie());
                
            }
        });
        
        addAll(tfLib,tfDescription,tfPrix,tfQuantite,cbr,cbc,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
