
package ch.hearc.meteo.affichage.view.mainpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class JPanelMain extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMain()
		{
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
		panelStationGraphs = new JPanelStationGraphs();
		panelMap = new JPanelMap();

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.BOTTOM);
		tabbedPane.addTab("Map", panelMap);
		tabbedPane.addTab("Graph", panelStationGraphs);

		setLayout(new BorderLayout());


		// JComponent : add
		add(tabbedPane, BorderLayout.CENTER);
		}

	private void control()
		{


		// START SIMULATION
		thread = new Thread(new Runnable()
			{
				@Override
				public void run()
					{
					while(true)
						{

						double wait = Math.random() * 2;
						System.out.println("wait: " + wait);

						sleep((long)wait*100);
						t += wait;

						panelStationGraphs.addValue(t, (Math.random() * 60) - 30);
						}
					}
			});
		// END SIMULATION
		}

	private void sleep(long millis)
		{
		try
			{
			Thread.sleep(millis);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelStationGraphs panelStationGraphs;
	private JPanelMap panelMap;

	// Sim
	private double t;
	private Thread thread;
	public void startSim()
		{
		t = 0;
		panelStationGraphs.addValue(0, 0);
		thread.start();
		}

	}
