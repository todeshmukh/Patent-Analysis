package charts;

import java.io.OutputStream;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	public static JFreeChart getPieChart( ChartModel c,
			ArrayList<String> xAxisValues, ArrayList<Integer> bar1) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		for (int i = 0; i < xAxisValues.size(); i++)
			pieDataset.setValue(xAxisValues.get(i), bar1.get(i));

		JFreeChart chart = ChartFactory.createPieChart(c.graphTitle, // Title
				pieDataset, // Dataset
				true, true, false);
//		try {
//			ChartUtilities.writeChartAsPNG(out, chart, c.getWidth(),
//					c.getHeight());
//		} catch (Exception e) {
//			System.out.println("Problem occurred creating chart.");
//		}
        return chart;
	}

	public static JFreeChart getPieChart(
			ArrayList<String> xAxisValues, ArrayList<Integer> bar1) {
		ChartModel c = new ChartModel();
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		for (int i = 0; i < xAxisValues.size(); i++)
		pieDataset.setValue(xAxisValues.get(i), bar1.get(i));
		JFreeChart chart = ChartFactory.createPieChart(c.graphTitle, // Title
				pieDataset, // Dataset
				true, true, false);
return chart; 
//		try {
//			ChartUtilities.writeChartAsPNG(out, chart, c.getWidth(),
//					c.getHeight());
//		} catch (Exception e) {
//			System.out.println("Problem occurred creating chart.");
//		}
	}
}
