/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import services.ServiceRepas;
import entities.Repa;
import java.io.IOException;
import static java.util.concurrent.ThreadLocalRandom.current;
import GUI.ListRepasForm;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import entities.Categories;
import services.ServiceCategories;
import utils.Statics;

public class ListCategoriesForm extends Form {
    Form current;

    public ListCategoriesForm(Form previous) {
        current = this;
        setTitle("List categories");
        setLayout(BoxLayout.y());
        Container cn = null;
        // SpanLabel sp = new SpanLabel();
        for (Categories c : ServiceCategories.getInstance().getAllCategories()) {
            cn = new Container(BoxLayout.x());
            SpanLabel sp = new SpanLabel(c.getCategorie());
            Button delcat = new Button("delete");
            delcat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent l) {
                    try {
                        if (Dialog.show("Delete", "Are you sure?", "Yes", "No")) {
                            if (ServiceCategories.getInstance().deleteCategorie(c.getId())) {
                                Dialog.show("Removed", "Categorie deleted!", new Command("OK"));
                                //this(previous).show();
                                new ListCategoriesForm(previous).show();
                                

                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            };
                        }
                    } catch (Exception e) {
                        Dialog.show("ERROR", e.getMessage(), new Command("OK"));
                    }
                }

            });
            cn.add(sp);
            cn.add(delcat);
            add(cn);
        }
        /* sp.setText(ServiceCategories.getInstance().getAllCategories().toString());
        add(sp);*/
        Button addcat = new Button("add categorie");
        addcat.addActionListener(l->{new AddCategorieForm(current).show();});
        add(addcat);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
    
}
