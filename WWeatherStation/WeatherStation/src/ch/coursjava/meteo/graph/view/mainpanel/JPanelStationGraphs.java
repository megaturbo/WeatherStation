package ch.coursjava.meteo.graph.view.mainpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ch.coursjava.meteo.graph.utils.JPanelSimpleGraph;

public class JPanelStationGraphs extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationGraphs()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void addValue(double x, double y) {
		panelTemperature.addValue(x, y);
		panelPressure.addValue(x, y);
		panelAltitude.addValue(x, y);
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
		panelTemperature = new JPanelSimpleGraph("Temperature");
		panelPressure = new JPanelSimpleGraph("Pression");
		panelAltitude = new JPanelSimpleGraph("Altitude");

		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);

		add(panelTemperature);
		add(panelPressure);
		add(panelAltitude);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelSimpleGraph panelTemperature;
	private JPanelSimpleGraph panelPressure;
	private JPanelSimpleGraph panelAltitude;


	}

