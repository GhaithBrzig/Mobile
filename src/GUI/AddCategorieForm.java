/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.Categories;
import entities.Repa;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import services.ServiceCategories;
import services.ServiceRepas;

/**
 *
 * @author bhk
 */
public class AddCategorieForm extends Form {

    Image img = null;
    String filePath = "";

    public AddCategorieForm(Form previous) {
        setTitle("Add new categorie");
        setLayout(BoxLayout.y());

       TextField tfcat = new TextField("", "Nom");
        Button btnValider = new Button("Add Categorie");

       

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfcat.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                           Categories c = new Categories(tfcat.getText().toString());
                        if (ServiceCategories.getInstance().addCategories(c)) {
                            Dialog.show("Success", "Categorie Ajouté!", new Command("OK"));
                            new ListCategoriesForm(new HomeForm()).show();
                        } else {
                            Dialog.show("ERROR", "Categorie deja existe", new Command("OK"));
                        }
                     //  ImageManipulation();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "erreur", new Command("OK"));
                    } 

                }
                //  System.out.println(ServiceCategories.getInstance().getAllCategories().get(cbc.getCurrentSelected()).getCategorie());

            }
        });

        addAll(tfcat, btnValider);
        //add(fpath);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    void ImageManipulation() throws IOException {
        InputStream stream = FileSystemStorage.getInstance().openInputStream(filePath);
        OutputStream out = Storage.getInstance().createOutputStream("MyImage");
        Util.copy(stream, out);
        
        Util.cleanup(stream);
        Util.cleanup(out);
        //  return FileSystemStorage.getInstance().getAppHomePath();
        System.out.println(stream + "**" + out ); 
    }

}
