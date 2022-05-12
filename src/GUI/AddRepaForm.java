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
public class AddRepaForm extends Form {

    Image img = null;
    String filePath = "";

    public AddRepaForm(Form previous) {
        setTitle("Add new repa");
        setLayout(BoxLayout.y());

        TextField tfLib = new TextField("", "Nom");
        TextField tfDescription = new TextField("", "Description");
        // TextField tfCategorie= new TextField("", "Categorie");
        ComboBox<String> cbc = new ComboBox();
        ComboBox<String> cbr = new ComboBox();
        Label lremise = new Label("Remise");
        Label lcategorie = new Label("Categorie");
        for (Categories c : ServiceCategories.getInstance().getAllCategories()) {
            cbc.addItem(c.getCategorie());

        }
        TextField tfPrix = new TextField("", "Prix");
        TextField tfQuantite = new TextField("", "Quantite");

        cbr.addItem("0%");
        cbr.addItem("20%");
        cbr.addItem("40%");
        cbr.addItem("60%");
        cbr.addItem("80%");

        ImageViewer imge = new ImageViewer();
        Button bpath = new Button("select image");
        // FileChooser fpath = new FileChooser();
        //  fpath.
        Button btnValider = new Button("Add repa");

        bpath.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        if (ev != null && ev.getSource() != null) {
                            filePath = (String) ev.getSource();

                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
                            String fileName = filePath.substring(fileNameIndex);

                            try {
                                img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                              //  System.out.println(FileSystemStorage.getInstance().openInputStream(filePath) + "***file path" + filePath + "**** index " + fileNameIndex + "***** file name" + fileName + "***** image " + img);

                                imge.setImage(img);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLib.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (tfQuantite.getText().length() == 0) || img == null) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                           Repa r = new Repa(tfLib.getText().toString(), tfDescription.getText().toString(), Integer.parseInt(tfPrix.getText()), Integer.parseInt(tfQuantite.getText()), Integer.toString(cbr.getCurrentSelected()), ServiceCategories.getInstance().getAllCategories().get(cbc.getCurrentSelected()).getCategorie(), "FrontOffice/images/Repas/Vegeterian.png");
                        if (ServiceRepas.getInstance().addRepa(r)) {
                            Dialog.show("Success", "Repa Ajouté!", new Command("OK"));
                            new HomeForm().show();
                        } else {
                            Dialog.show("ERROR", "Nom deja existe", new Command("OK"));
                        }
                     //  ImageManipulation();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix or quantite must be a number", new Command("OK"));
                    } 

                }
                //  System.out.println(ServiceCategories.getInstance().getAllCategories().get(cbc.getCurrentSelected()).getCategorie());

            }
        });

        addAll(tfLib, tfDescription, tfPrix, tfQuantite, lremise, cbr, lcategorie, cbc, bpath, btnValider, imge);
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
