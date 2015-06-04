
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.full.JPanelMap;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.full.JPanelStationGraphs;

public class JPanelMainLocalFull extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMainLocalFull(Manager manager)
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
	public void setSeriesVisible(int serie, boolean visible)
		{
		panelStationGraphs.setSeriesVisible(serie, visible);
		panelMap.refresh();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		panelStationGraphs = new JPanelStationGraphs(manager);
		panelMap = new JPanelMap(manager);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.BOTTOM);
		tabbedPane.addTab("Map", panelMap);
		tabbedPane.addTab("Graph", panelStationGraphs);

		setLayout(new BorderLayout());

		// JComponent : add
		add(tabbedPane, BorderLayout.CENTER);
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
	private JPanelStationGraphs panelStationGraphs;
	private JPanelMap panelMap;

	}
