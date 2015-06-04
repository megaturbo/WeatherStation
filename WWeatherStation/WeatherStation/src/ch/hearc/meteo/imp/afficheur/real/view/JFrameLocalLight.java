
package ch.hearc.meteo.imp.afficheur.real.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ch.hearc.meteo.imp.afficheur.real.AfficheurFactory;
import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMainLocalLight;

public class JFrameLocalLight extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameLocalLight(String portCom, Manager manager)
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

		panelMain = new JPanelMainLocalLight(manager);

		setLayout(new BorderLayout());
		}

	private void control()
		{
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Local Client [Light]");
		setSize(600, 400);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}
	private void createMenuBar()
		{
		JMenuBar menuBar = new JMenuBar();

		JMenu menuConnection = new JMenu("Connection");
		menuConnection.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuConnection);

		JMenuItem itemSelectCom = new JMenuItem("Select COM");
		itemSelectCom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

		itemSelectCom.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					(new AfficheurFactory()).createOnLocalPCLight();
					}
			});

		menuConnection.add(itemSelectCom);

		setJMenuBar(menuBar);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Manager manager;

	// Tools
	private JPanelMainLocalLight panelMain;

	}
