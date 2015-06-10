
package ch.hearc.meteo.imp.afficheur.real.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMainLocal;
import ch.hearc.meteo.imp.com.real.port.MeteoPortDetectionService;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JFrameLocal extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameLocal(ManagerLocal manager)
		{
		this.manager = manager;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelMain.updateMeteoServiceOptions(meteoServiceOptions);
		}

	public void refresh()
		{
		panelMain.refresh();
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
		createMenuBar();

		panelMain = new JPanelMainLocal(manager);

		setLayout(new BorderLayout());

		add(panelMain);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Local Client");
		setSize(900, 600);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	private void createMenuBar()
		{
		JMenuBar menuBar = new JMenuBar();

		JMenu menuConnection = new JMenu("Connection");
		menuConnection.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuConnection);

		JMenuItem itemSelectCom = new JMenuItem("Add a Weather Station");
		itemSelectCom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

		itemSelectCom.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					String newPort = showPortComChooser();
					//TODO Give FR to NAZI, NAZI will give FR to Japan
					if (newPort != null)
						{
						manager.addMeteoService(newPort);
						}
					}
			});

		menuConnection.add(itemSelectCom);

		setJMenuBar(menuBar);
		}

	/**
	 * Show an input dialog to add a Weather Station
	 *
	 * @return Port com, but if cancel or nothing found => null
	 */
	private String showPortComChooser()
		{
		MeteoPortDetectionService portDetector = new MeteoPortDetectionService();
		List<String> ports = portDetector.findListPortMeteo();
		if (ports.isEmpty())
			{
			JOptionPane.showMessageDialog(new JFrame(), "No Weather Station found.");
			return null;
			}
		else
			{

			Object o = JOptionPane.showInputDialog(new JFrame(), "Add a COM port", "COM port chooser", JOptionPane.PLAIN_MESSAGE, null, ports.toArray(), "ham");
			return (String)o;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerLocal manager;

	// Tools
	private JPanelMainLocal panelMain;
	}
