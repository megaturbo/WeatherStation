
package ch.hearc.meteo.affichage.view.mainpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.affichage.customs.QKTCheckBox;

public class JPanelStationList extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationList()
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
		ArrayList<QKTCheckBox> stations = new ArrayList<QKTCheckBox>();

		for(int i = 0; i < 20; i++)
			{
			stations.add(new QKTCheckBox("Berlin " + i));
			}

		JPanel panelList = new JPanel();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));
		for(JCheckBox station:stations)
			{
			station.setSelected(true);
			panelList.add(station);
			}

		JScrollPane scrollPane = new JScrollPane(panelList);
		scrollPane.setPreferredSize(new Dimension(200,400));
//		scrollPane.getVerticalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.VERTICAL));
//		scrollPane.getHorizontalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.HORIZONTAL));

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

	// Tools

	}
