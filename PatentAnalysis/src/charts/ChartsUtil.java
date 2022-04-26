
package charts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.util.StringHelper;

public class ChartsUtil {

    public ChartPanel showAuditGraph(HashMap process,JLabel label,int width,int height) {
        FileOutputStream out = null;
        ChartPanel chartPanel = null;  
        try {
            ArrayList<String> xAxisValues = new ArrayList<String>();
            ArrayList<Float> bar1 = new ArrayList<Float>();
            Set set = process.keySet();
            int total=0;
            for (Object key : set) {
                int value = StringHelper.n2i(process.get(key));
            total+=value;
            }

            for (Object key : set) {
                float value = (float) ((StringHelper.n2f(process.get(key)) / total) * 100);
                  DecimalFormat df = new DecimalFormat("####0.00");
                xAxisValues.add(key + "");
                bar1.add(StringHelper.n2f(df.format(value)));
            }  
//                out = new FileOutputStream(new File("D:\\newPiee.png"));
//            PieChart.getPieChart(out, xAxisValues, bar1);
            try {
                ChartModel c = new ChartModel();
                DefaultPieDataset pieDataset = new DefaultPieDataset();
                for (int i = 0; i < xAxisValues.size(); i++) {
                    pieDataset.setValue(xAxisValues.get(i), bar1.get(i));
                }
                JFreeChart chart = ChartFactory.createPieChart("Audit Statistics", pieDataset, true, true, false);
                
             BufferedImage bi= chart.createBufferedImage(width, height);
             label.setIcon(new ImageIcon(bi));
                

            } catch (Exception e) {  
                System.out.println("Problem occurred creating chart.");
            }

        } catch (Exception ex) {
            Logger.getLogger(ChartsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chartPanel;

    }
    


}
