
package ch.hearc.meteo.imp.afficheur.real.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMain;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelStationList;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain(Manager manager)
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
		panelList.refresh();
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
		panelMain = new JPanelMain(manager);
		panelList = new JPanelStationList(manager);

		setLayout(new BorderLayout());

		add(panelList, BorderLayout.WEST);
		add(panelMain, BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Station M�t�o");
		setSize(800, 600);
		setLocationRelativeTo(null); 	// frame centrer
		setVisible(true); 				// last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Manager manager;

	// Tools
	private JPanelStationList panelList;
	private JPanelMain panelMain;

	}
