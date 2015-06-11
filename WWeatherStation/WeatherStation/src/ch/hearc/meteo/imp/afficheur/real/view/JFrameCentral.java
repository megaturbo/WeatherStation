package ch.hearc.meteo.imp.afficheur.real.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerCentral;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMainCentral;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.full.JPanelStationList;

public class JFrameCentral extends JFrame{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameCentral(ManagerCentral manager) {
		this.manager = manager;

		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/


	public void setSeriesVisible(int serie, boolean visible) {
		panelMain.setSeriesVisible(serie, visible);
	}

	public void refresh() {
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

	private void geometry() {
		//createMenuBar();

		panelMain = new JPanelMainCentral(manager);
		panelList = new JPanelStationList(this, manager);

		setLayout(new BorderLayout());

		add(panelList, BorderLayout.WEST);
		add(panelMain, BorderLayout.CENTER);
	}

	private void control() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void appearance() {
		setTitle("Station Météo");
		setSize(1200, 800);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuConnection = new JMenu("Connection");
		menuConnection.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuConnection);

		JMenuItem itemSelectCom = new JMenuItem("Select COM");
		itemSelectCom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));

		itemSelectCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] possibilities = { "COM1", "COM2", "etc..." };
				Object o = JOptionPane.showInputDialog(JFrameCentral.this,
						"Choose a COM port", "COM port chooser",
						JOptionPane.PLAIN_MESSAGE, null, possibilities, "ham");
				// TODO use Port to set connection
			}
		});

		menuConnection.add(itemSelectCom);

		setJMenuBar(menuBar);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerCentral manager;

	// Tools
	private JPanelStationList panelList;
	private JPanelMainCentral panelMain;

}
