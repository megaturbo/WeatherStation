package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ch.hearc.meteo.affichage.utils.JPanelSimpleGraph;
import ch.hearc.meteo.imp.afficheur.real.moo.Manager;

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

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelAltitude = new JPanelSimpleGraph("Temperature", manager.getCollectionAltitude());
		panelPressure = new JPanelSimpleGraph("Pression", manager.getCollectionPression());
		panelTemperature = new JPanelSimpleGraph("Altitude", manager.getCollectionTemperature());

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

