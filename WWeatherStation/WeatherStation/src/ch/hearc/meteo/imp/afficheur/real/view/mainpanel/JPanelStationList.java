
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.affichage.customs.QKTCheckBox;
import ch.hearc.meteo.affichage.customs.QKTScrollBarUI;
import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.moo.Station;

public class JPanelStationList extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationList(Manager manager)
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
		Collection<Station> stations = manager.getStationList();

		// Clear
		panelList.removeAll();

		for(Station s: stations)
			{
			QKTCheckBox cb = new QKTCheckBox(s.getName());
			cb.setSelected(s.isVisible());
			panelList.add(cb);
			}
		this.updateUI();
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

		panelList = new JPanel();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane(panelList);
		scrollPane.setPreferredSize(new Dimension(200, 400));
				scrollPane.getVerticalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.VERTICAL));
				scrollPane.getHorizontalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.HORIZONTAL));

		FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
		setLayout(flowlayout);

		add(scrollPane);
		}

	private void control()
		{
		// rien
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
	JPanel panelList;

	}
