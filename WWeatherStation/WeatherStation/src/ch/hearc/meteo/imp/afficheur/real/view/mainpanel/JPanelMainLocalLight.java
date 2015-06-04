
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;

public class JPanelMainLocalLight extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMainLocalLight(Manager manager)
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
		// JComponent : Instanciation
		sliderAltitude = new JSlider();

		setLayout(new BorderLayout());

		// JComponent : add
		}

	private void control()
		{

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
	private JSlider sliderAltitude;
	private JSlider sliderPressure;
	private JSlider sliderTemperature;

	}
