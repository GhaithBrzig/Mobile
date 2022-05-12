/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package GUI;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.FontImage;
import entities.Repa;
import services.ServiceRepas;




/**
 * Sales demo bar chart.
 */
public class StatisticsForm extends AbstractDemoChart {

  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Repas bar chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The Prix et les quantites per repa";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      ArrayList<Repa> lr = ServiceRepas.getInstance().getAllRepas();
    String[] titles = new String[] { "Quantite","prix"};
    List<double[]> values = new ArrayList<double[]>();
    double[] q = new double[30];
    double[] p = new double[30];
    int x = 0;
    for(Repa r : lr )
    {
        q[x] = r.getQuantite();
        p[x++] = r.getPrixProd();
    }
    values.add(q);
    values.add(p);
    int[] colors = new int[] { ColorUtil.CYAN, ColorUtil.BLUE };
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    renderer.setOrientation(Orientation.HORIZONTAL);
    setChartSettings(renderer, "Quantites et prix", "Noms", "values", 0.5,
        12.5, 0, 100, ColorUtil.GRAY, ColorUtil.LTGRAY);
    renderer.setXLabels(1);
    renderer.setYLabels(10);
   /* renderer.addXTextLabel(1, "Jan");
    renderer.addXTextLabel(3, "Mar");
    renderer.addXTextLabel(5, "May");
    renderer.addXTextLabel(7, "Jul");
    renderer.addXTextLabel(10, "Oct");
    renderer.addXTextLabel(12, "Dec");*/
   x = 0;
       for(Repa r : lr )
    {
        renderer.addXTextLabel(++x, r.getLibProd());
    }
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
      seriesRenderer.setDisplayChartValues(true);
    }
    
    BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
        Type.DEFAULT);
          //  f.getToolbar().addMaterialCommandToLeftBar();

            Form f = wrap("", new ChartComponent(chart));
           //Form f = new Form("Quantites");
          // ChartComponent c = new ChartComponent(chart);
           //f.add(c);
             f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().showBack());
                   
    return f;
    
  }

}
