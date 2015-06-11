
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.datas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.moo.Sensor;

public class JPanelDatas extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelDatas(ManagerLocal manager)
		{
		this.manager = manager;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refresh()
		{
		updatePortCom(portCom);
		}

	public void updatePortCom(String portCom)
		{
		this.portCom = portCom;
		TimeSeries altitudeSeries = manager.getSeriesFromPort(portCom, Sensor.ALTITUDE);
		TimeSeries pressureSeries = manager.getSeriesFromPort(portCom, Sensor.PRESSURE);
		TimeSeries temperatureSeries = manager.getSeriesFromPort(portCom, Sensor.TEMPERATURE);

		repaintDatas(altitudeSeries, pressureSeries, temperatureSeries);
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
		this.removeAll();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(new JLabel("MIN"), c);
		c.gridx = 2;
		add(new JLabel("MAX"), c);
		c.gridx = 3;
		add(new JLabel("LIVE"), c);

		displayRow(labelAltitude, altitudeSeries, c, 1);
		displayRow(labelPressure, pressureSeries, c, 2);
		displayRow(labelTemperature, temperatureSeries, c, 3);

		this.updateUI();
		}

	private void displayRow(JLabel rowLabel, TimeSeries series, GridBagConstraints c, int gridy)
		{
		String min;
		String max;
		String liv;
		try
			{
			min = String.format("%.1f%n", series.getMinY());
			max = String.format("%.1f%n", series.getMaxY());
			liv = String.format("%.1f%n", series.getValue(series.getItemCount() - 1));
			}
		catch (NullPointerException e)
			{
			min = "W";
			max = "W";
			liv = "W";
			}

		c.gridy = gridy;

		c.gridx = 0;
		add(rowLabel, c);
		c.gridx = 1;
		add(new JLabel(min), c);
		c.gridx = 2;
		add(new JLabel(max), c);
		c.gridx = 3;
		add(new JLabel(liv), c);
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
	private ManagerLocal manager;

	// Tools
	private String portCom;
	private JLabel labelAltitude;
	private JLabel labelPressure;
	private JLabel labelTemperature;

	}
