package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.datas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.moo.Sensor;

public class JPanelDatas extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelDatas(Manager manager)
		{
		this.manager = manager;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updatePortCom(String portCom) {
		TimeSeries altitudeSeries = manager.getSeriesFromPort(portCom, Sensor.ALTITUDE);
		TimeSeries pressureSeries = manager.getSeriesFromPort(portCom, Sensor.PRESSURE);
		TimeSeries temperatureSeries = manager.getSeriesFromPort(portCom, Sensor.TEMPERATURE);

		repaintDatas(altitudeSeries,pressureSeries,temperatureSeries);
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

	private void repaintDatas(TimeSeries altitudeSeries, TimeSeries pressureSeries, TimeSeries temperatureSeries)
		{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 1;
		c.gridy = 0;
		add(new JLabel("MIN"));
		c.gridx = 2;
		add(new JLabel("MAX"));
		c.gridx = 3;
		add(new JLabel("LIVE"));

		c.gridx = 0;
		c.gridy = 1;
		add(labelAltitude);
		c.gridx = 1;
		add(new JLabel(""+altitudeSeries.getMinY()));
		c.gridx = 2;
		add(new JLabel(""+altitudeSeries.getMaxY()));
		c.gridx = 3;
		add(new JLabel(""+altitudeSeries.getValue(altitudeSeries.getItemCount() - 1)));


		}

	private void geometry()
		{
		labelAltitude = new JLabel("Altitude");
		labelPressure = new JLabel("Pressure");
		labelTemperature = new JLabel("Temperature");

		setLayout(new GridBagLayout());
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
	private JLabel labelAltitude;
	private JLabel labelPressure;
	private JLabel labelTemperature;

	}
