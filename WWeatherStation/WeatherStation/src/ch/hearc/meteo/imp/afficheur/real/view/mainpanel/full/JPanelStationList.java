
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.full;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerCentral;
import ch.hearc.meteo.imp.afficheur.real.moo.Station;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameCentral;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.options.JPanelSlider;
public class JPanelStationList extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationList(JFrameCentral parent, ManagerCentral manager)
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
			//affiche tt les station connecté NOQKTCHECKBOX
			/*QKTCheckBox cb = new QKTCheckBox(s.getName());
			cb.setSelected(s.isVisible());
			addCheckBoxListener(s, cb);
			*/
			//panel.add(new JButton("►"));

			FlowLayout layout = new FlowLayout();
			JPanel panel = new JPanel(layout);
			panel.add(new JLabel("Station "+s.getName()));

			JButton settings = new JButton("Settings");
			addButtonListener(s, settings);
			panel.add(settings);
			panelList.add(panel);
			}
		this.updateUI();
		}

	private void addButtonListener(Station s, JButton btn)
		{
		btn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					JDialog dialog = new JDialog(parent);
					dialog.add(new JPanelSlider(manager));
					dialog.setModal(true);
					dialog.setSize(new Dimension(500,200));
					dialog.setLocationRelativeTo(parent);
					dialog.setVisible(true);
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
	private JFrameCentral parent;
	private ManagerCentral manager;

	// Tools
	private JPanel panelList;

	}
