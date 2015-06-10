package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.list;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.JPanelListCom;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class JPanelComControl extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelComControl(JPanelListCom parent, MeteoServiceWrapper_I meteoRemote)
		{
		this.parent = parent;
		this.meteoRemote = meteoRemote;
		this.portName = "ERR";
		try
			{
			this.portName = meteoRemote.getPort();
			}
		catch (RemoteException e)
			{
			e.printStackTrace();
			}

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

		labelPort = new JLabel(portName);
		buttonStart = new JButton(">");
		buttonStop = new JButton("||");
		buttonDisconnect = new JButton("X");

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;

		add(labelPort, c);
		c.gridx = 1;
		add(buttonStart, c);
		c.gridx = 2;
		add(buttonStop, c);
		c.gridx = 3;
		add(buttonDisconnect, c);
		}

	private void control()
		{
		this.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e)
					{
					parent.updatePortCom(portName);
					}

				@Override
				public void mouseClicked(MouseEvent arg0)
					{
					}
			});

		this.buttonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					MeteoServiceOptions meteoOptions = new MeteoServiceOptions(100, 200, 300);
					try
						{
						parent.getManager().setMeteoServiceOptions(portName, meteoOptions);
						meteoRemote.start(parent.getManager().getMeteoServiceOptions(portName));
						}
					catch (RemoteException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
			});

		this.buttonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					try
						{
						meteoRemote.stop();
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}
					}
			});

		this.buttonDisconnect.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					try
						{
						meteoRemote.disconnect();
						}
					catch (RemoteException | MeteoServiceException e1)
						{
						e1.printStackTrace();
						}
					}
			});
		}

	private void appearance()
		{
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JPanelListCom parent;
	private String portName;
	private MeteoServiceWrapper_I meteoRemote;

	// Tools
	private JLabel labelPort;
	private JButton buttonStart;
	private JButton buttonStop;
	private JButton buttonDisconnect;

	}
