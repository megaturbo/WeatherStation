
package ch.coursjava.meteo.graph;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.coursjava.meteo.graph.customs.QKTCheckBox;
import ch.coursjava.meteo.graph.customs.QKTScrollBarUI;

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
		stations.add(new QKTCheckBox("Berlin"));
		stations.add(new QKTCheckBox("Paris"));
		stations.add(new QKTCheckBox("Berlin"));
		stations.add(new QKTCheckBox("Paris"));
		stations.add(new QKTCheckBox("Berne"));
		stations.add(new QKTCheckBox("Berne"));
		stations.add(new QKTCheckBox("Shampooing"));

		JPanel panelList = new JPanel();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));
		for(JCheckBox station: stations) {
			station.setSelected(true);
			panelList.add(station);
		}

		JScrollPane scrollPane = new JScrollPane(panelList);
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

	// Tools

	}
