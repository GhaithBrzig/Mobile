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
import utils.Statics;

/**
 *
 * @author bhk
 */
public class ListRepasForm extends Form {

    public ListRepasForm(Form previous) {
        setTitle("List Repas");

        SpanLabel sp = new SpanLabel();
        String list = "";

        for (Repa r : ServiceRepas.getInstance().getAllRepas()) {
            // list += r.toString() + "\n";
            Container c = new Container(BoxLayout.y());
            c.getStyle().setBgColor(0xf7cbcb);
            c.getStyle().setBgTransparency(255);
            SpanLabel nom = new SpanLabel(r.getLibProd());
            SpanLabel description = new SpanLabel(r.getDescription());
            SpanLabel prix = new SpanLabel(Integer.toString(GestRemise(r)) + "$");
            SpanLabel quantite = new SpanLabel(Integer.toString(r.getQuantite()));
            SpanLabel categorie = new SpanLabel(r.getCategorie());
            ImageViewer iv = null;
            Image img;
            try {
                EncodedImage ec = EncodedImage.create("/load.png");

                String url = "http://localhost:8080/mobProjet/public/" + r.getPath();
                img = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                //  img.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 5));
                iv = new ImageViewer(img);

            } catch (IOException ex) {
                System.out.println(ex);
            }

            Button bu = new Button("update");
            Button bd = new Button("delete");

            bd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent l) {
                    try {
                        if (Dialog.show("Delete", "Are you sure?", "Yes", "No")) {
                            if (ServiceRepas.getInstance().deleteRepa(r.getId())) {
                                Dialog.show("Removed", "Repa deleted!", new Command("OK"));
                                //this(previous).show();
                                new ListRepasForm(previous).show();
                                

                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            };
                        }
                    } catch (Exception e) {
                        Dialog.show("ERROR", e.getMessage(), new Command("OK"));
                    }
                }

            });
            Form current = this;
            bu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    new ModifierRepaForm(current, r).show();
                }
            });
            Container pq = new Container(BoxLayout.x());
            Container btns = new Container();

            pq.add(prix);
            pq.add(quantite);

            btns.add(bu);
            btns.add(bd);
            c.add(iv);
            c.add(nom);
            c.add(description);

            c.add(categorie);
            c.add(pq);
            c.add(btns);
            add(c);
            add(new Label("\n\n"));
        }
        // sp.setText(list);
        // add(sp);
        this.setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    int GestRemise(Repa r) {
        String s = r.getRemise();
        int rem = Integer.parseInt(s);
        int finalPrice = r.getPrixProd();
        if (rem == 1) {
            finalPrice = (int) (r.getPrixProd() * 0.8);
        } else if (rem == 2) {
            finalPrice = (int) (r.getPrixProd() * 0.6);
        } else if (rem == 3) {
            finalPrice = (int) (r.getPrixProd() * 0.4);
        } else if (rem == 4) {
            finalPrice = (int) (r.getPrixProd() * 0.2);
        }
        return finalPrice;
    }
}
