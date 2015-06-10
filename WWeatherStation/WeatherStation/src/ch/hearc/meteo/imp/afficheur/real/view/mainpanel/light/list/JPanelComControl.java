package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.list;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelComControl extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelComControl(String portName)
		{
		this.portName = portName;

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
					System.out.println(portName + " pressed");

					}

				@Override
				public void mouseClicked(MouseEvent arg0)
					{
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
	private String portName;

	// Tools
	private JLabel labelPort;
	private JButton buttonStart;
	private JButton buttonStop;
	private JButton buttonDisconnect;

	}
