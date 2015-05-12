
package ch.coursjava.meteo.graph.utils;

import java.awt.BorderLayout;
import java.awt.Color;

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
		altitudeSeries.add(x, y);
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
		altitudeSeries = new XYSeries(graphTitle);
		dataset = new XYSeriesCollection();

		dataset.addSeries(altitudeSeries);

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
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();

		plot.setBackgroundPaint(Color.darkGray);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.lightGray);

		// change the auto tick unit selection to integer units only...
		ValueAxis rangeAxis = plot.getRangeAxis();
		ValueAxis domainAxis = plot.getDomainAxis();

		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		domainAxis.setAutoRange(true);
		domainAxis.setFixedAutoRange(100);
		domainAxis.setRange(0, 100);

		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String graphTitle;

	// Tools
	private XYSeries altitudeSeries;
	private XYSeriesCollection dataset;
	private ChartPanel chartPanel;

	}
