package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.subpanels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.utils.JPanelSimpleGraph;

public class JPanelStationGraphs extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationGraphs(Manager manager)
		{
		this.manager = manager;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setSeriesVisible(int serie, boolean visible) {
		panelAltitude.setSeriesVisible(serie, visible);
		panelPressure.setSeriesVisible(serie, visible);
		panelTemperature.setSeriesVisible(serie, visible);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelAltitude = new JPanelSimpleGraph("Altitude", manager.getCollectionAltitude());
		panelPressure = new JPanelSimpleGraph("Pression", manager.getCollectionPression());
		panelTemperature = new JPanelSimpleGraph("Temperature", manager.getCollectionTemperature());

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

	// Inputs
	private Manager manager;

	// Tools
	private JPanelSimpleGraph panelTemperature;
	private JPanelSimpleGraph panelPressure;
	private JPanelSimpleGraph panelAltitude;


	}

