
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

	private void updateSlider(JSlider slider, int value) {
		if(value > slider.getMaximum()) {
			slider.setMaximum(value + 50);
		}
		slider.setValue(value);
	}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		int valueAltitude = (int) meteoServiceOptions.getAltitudeDT();
		int valuePressure = (int) meteoServiceOptions.getPressionDT();
		int valueTemperature = (int) meteoServiceOptions.getTemperatureDT();

		updateSlider(sliderAltitude, valueAltitude);
		updateSlider(sliderPressure, valuePressure);
		updateSlider(sliderTemperature, valueTemperature);

		setTitleBorders(valueAltitude, valuePressure, valueTemperature);
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

		setTitleBorders(valueAltitude, valuePressure, valueTemperature);

		sliderAltitude.setBorder(borderAltitude);
		sliderPressure.setBorder(borderPressure);
		sliderTemperature.setBorder(borderTemperature);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(sliderAltitude, c);

		c.gridy = 1;
		add(sliderPressure, c);

		c.gridy = 2;
		add(sliderTemperature, c);
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

					setTitleBorders(valueAltitude, valuePressure, valueTemperature);
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

	private void setTitleBorders(int valueAltitude, int valuePressure, int valueTemperature)
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
