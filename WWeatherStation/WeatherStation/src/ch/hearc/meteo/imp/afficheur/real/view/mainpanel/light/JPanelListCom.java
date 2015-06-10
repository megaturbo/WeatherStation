
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.moo.Station;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMainLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.list.JPanelComControl;

public class JPanelListCom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelListCom(JPanelMainLocal parent, ManagerLocal manager)
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

	public void updatePortCom(String portCom) {
		parent.updatePortCom(portCom);
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
		panelList = new JPanel();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
		refreshButton = new JButton("Refresh");

		JScrollPane scrollPane = new JScrollPane(panelList);

		this.setBorder(BorderFactory.createTitledBorder("Connected stations"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// JComponent : add
		add(scrollPane);
		add(refreshButton);
		}

	private void control()
		{
		refreshButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					refreshList();
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	private void refreshList()
		{
		panelList.removeAll();
		Collection<Station> stations = manager.getStationList();

		for(Station s:stations)
			{
			panelList.add(new JPanelComControl(this, s.getName()));
			}

		this.updateUI();

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JPanelMainLocal parent;
	private ManagerLocal manager;

	// Tools
	private JPanel panelList;
	private JButton refreshButton;

	}
