
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.full;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.imp.afficheur.real.customs.QKTCheckBox;
import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.moo.Station;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameFull_I;

public class JPanelStationList extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationList(JFrameFull_I parent, Manager manager)
		{
		this.parent = parent;
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

		panelList.removeAll();

		for(Station s: stations)
			{
			QKTCheckBox cb = new QKTCheckBox(s.getName());
			cb.setSelected(s.isVisible());
			addCheckBoxListener(s, cb);
			panelList.add(cb);
			}
		this.updateUI();
		}

	private void addCheckBoxListener(Station s, QKTCheckBox cb)
		{
		cb.addItemListener(new ItemListener()
			{

				@Override
				public void itemStateChanged(ItemEvent event)
					{
					parent.setSeriesVisible(0, !s.isVisible());
					s.setVisible(!s.isVisible());
					}
			});
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
//		scrollPane.setPreferredSize(new Dimension(200, 600));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(new JLabel("Connected Stations"), c);

		c.gridy = 1;
		c.weighty = 9;
		add(scrollPane, c);
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
	private JFrameFull_I parent;
	private Manager manager;

	// Tools
	private JPanel panelList;

	}
