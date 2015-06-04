
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.subpanels;

import java.awt.FlowLayout;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelSlider extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSlider(Manager manager)
		{
		this.manager = manager;

		geometry();
		apparence();
		control();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		int valueAltitude = sliderAltitude.getValue();
		int valuePressure = sliderPressure.getValue();
		int valueTemperature = sliderTemperature.getValue();
		sliderAltitude.setValue(valueAltitude);
		sliderPressure.setValue(valuePressure);
		sliderTemperature.setValue(valueTemperature);
		setTitleBorder(valueAltitude, valuePressure, valueTemperature);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		int min = 100;
		int max = 1200;
		int valueAltitude;
		int valuePressure;
		int valueTemperature;
		try
			{
			valueAltitude = (int)manager.getMeteoServiceOptions().getAltitudeDT();
			valuePressure = (int)manager.getMeteoServiceOptions().getPressionDT();
			valueTemperature = (int)manager.getMeteoServiceOptions().getTemperatureDT();
			}
		catch (RemoteException e)
			{
			valueAltitude = (min + max) / 2;
			valuePressure = (min + max) / 2;
			valueTemperature = (min + max) / 2;
			e.printStackTrace();
			}

		sliderAltitude = new JSlider(min, max, valueAltitude);
		sliderPressure = new JSlider(min, max, valuePressure);
		sliderTemperature = new JSlider(min, max, valueTemperature);

		borderAltitude = BorderFactory.createTitledBorder("");
		borderPressure = BorderFactory.createTitledBorder("");
		borderTemperature = BorderFactory.createTitledBorder("");

		setTitleBorder(valueAltitude, valuePressure, valueTemperature);

		sliderAltitude.setBorder(borderAltitude);
		sliderPressure.setBorder(borderPressure);
		sliderTemperature.setBorder(borderTemperature);

		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(sliderAltitude);
		add(sliderPressure);
		add(sliderTemperature);
		}

	private void apparence()
		{
		//setBackground(Color.ORANGE);

		sliderTemperature.setOrientation(SwingConstants.HORIZONTAL);
		}

	private void control()
		{
		sliderAltitude.addChangeListener(createChangeListener());
		sliderPressure.addChangeListener(createChangeListener());
		sliderTemperature.addChangeListener(createChangeListener());
		}

	private ChangeListener createChangeListener()
	{
		return new ChangeListener()
		{

			@Override public void stateChanged(ChangeEvent e)
				{
				int valueAltitude = sliderAltitude.getValue();
				int valuePressure = sliderPressure.getValue();
				int valueTemperature = sliderTemperature.getValue();

				try
					{
					MeteoServiceOptions meteoServiceOption = new MeteoServiceOptions(manager.getMeteoServiceOptions());
					meteoServiceOption.setAltitudeDT(valueAltitude);
					meteoServiceOption.setPressionDT(valuePressure);
					meteoServiceOption.setTemperatureDT(valueTemperature);

					setTitleBorder(valueAltitude, valuePressure, valueTemperature);
					manager.setMeteoServiceOptions(meteoServiceOption);
					}
				catch (RemoteException e1)
					{
					System.err.println("[JPanelSlider] remote update failed");
					e1.printStackTrace();
					}

				}
		};
	}

	private void setTitleBorder(int valueAltitude, int valuePressure, int valueTemperature)
		{
		borderAltitude.setTitle("\u0394 Altitude =" + valueAltitude + " (ms)");
		borderPressure.setTitle("\u0394 Pressure =" + valuePressure + " (ms)");
		borderTemperature.setTitle("\u0394 Temperature =" + valueTemperature + " (ms)");
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
	private TitledBorder borderAltitude;
	private TitledBorder borderPressure;
	private TitledBorder borderTemperature;
	}
