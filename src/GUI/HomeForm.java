/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ListRepasForm;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddRepa = new Button("Add Repa");
        Button btnListRepas = new Button("List Repas");
        Button btnCategories = new Button("List Categories");

        Button btnCharts = new Button("Chart");

        btnAddRepa.addActionListener(e -> new AddRepaForm(current).show());
        btnListRepas.addActionListener(e -> new ListRepasForm(current).show());
       //btnCharts.addActionListener(e -> new ChartForm().createPieChartForm(current).show());
       btnCharts.addActionListener(e -> new StatisticsForm().execute().show());
        btnCategories.addActionListener(e -> new ListCategoriesForm(current).show());
        addAll(btnAddRepa, btnListRepas,btnCategories, btnCharts);

    }

}
