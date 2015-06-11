
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMainLocal;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.list.JPanelComControl;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

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

	public ManagerLocal getManager() {
		return manager;
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

	public void refreshList()
		{
		panelList.removeAll();
		List<MeteoServiceWrapper_I> remotes = manager.getRemotes();

		for(MeteoServiceWrapper_I remote:remotes)
			{
			panelList.add(new JPanelComControl(this, remote));
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
