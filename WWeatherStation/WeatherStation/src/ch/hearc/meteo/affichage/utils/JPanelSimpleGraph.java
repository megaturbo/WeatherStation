
package ch.hearc.meteo.affichage.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JPanelSimpleGraph extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSimpleGraph(String graphTitle)
		{
		this.graphTitle = graphTitle;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void addValue(double x, double y)
		{
		XYSeries serie = series.get(0);
		serie.add(x, y);

		if (serie.getItemCount() > 100)
			{
			serie.remove(0);
			}
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		series = new ArrayList<XYSeries>();

		series.add(new XYSeries("Berne"));

		dataset = new XYSeriesCollection();

		for(XYSeries serie:series)
			{
			dataset.addSeries(serie);
			}

		JFreeChart chart = createChart();
		chartPanel = new ChartPanel(chart);

		setLayout(new BorderLayout());

		add(chartPanel, BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//rien
		}

	private JFreeChart createChart()
		{
		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(graphTitle, // chart title
				"Time (s)", // x axis label
				graphTitle, // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		plot = chart.getXYPlot();

		plot.setBackgroundPaint(Color.darkGray);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.lightGray);

		// change the auto tick unit selection to integer units only...
		ValueAxis rangeAxis = plot.getRangeAxis();
		ValueAxis domainAxis = plot.getDomainAxis();

		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String graphTitle;

	// Tools
	private ArrayList<XYSeries> series;
	private XYSeriesCollection dataset;
	private ChartPanel chartPanel;
	private XYPlot plot;

	}
