package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.datas.JPanelDatas;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.options.JPanelSlider;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelViewCom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelViewCom(ManagerLocal manager)
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
		panelDatas.refresh();
		}

	public void updatePortCom(String portCom)
		{
		this.setVisible(true);
		panelSlider.updatePortCom(portCom);
		panelDatas.updatePortCom(portCom);
		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelSlider.updateMeteoServiceOptions(meteoServiceOptions);
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
		panelSlider = new JPanelSlider(manager);
		panelDatas = new JPanelDatas(manager);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// JComponent : add
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(panelSlider, c);

		c.gridx = 1;
		add(panelDatas, c);

		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//this.setVisible(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerLocal manager;

	// Tools
	private JPanelSlider panelSlider;
	private JPanelDatas panelDatas;
	}