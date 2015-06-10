
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.JPanelListCom;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.JPanelViewCom;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;


public class JPanelMainLocal extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMainLocal(ManagerLocal manager)
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
		panelViewCom.refresh();
		}

	public void updatePortCom(String portCom) {
		panelViewCom.updatePortCom(portCom);
	}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelViewCom.updateMeteoServiceOptions(meteoServiceOptions);
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
		// JComponent : Instanciation
		panelListCom = new JPanelListCom(this, manager);
		panelViewCom = new JPanelViewCom(manager);

		setLayout(new BorderLayout());

		add(panelListCom, BorderLayout.WEST);
		add(panelViewCom, BorderLayout.CENTER);
		}

	private void control()
		{

		}

	private void appearance()
		{
		panelListCom.setPreferredSize(new Dimension(250, 300));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerLocal manager;

	// Tools
	private JPanelListCom panelListCom;
	private JPanelViewCom panelViewCom;

	}
