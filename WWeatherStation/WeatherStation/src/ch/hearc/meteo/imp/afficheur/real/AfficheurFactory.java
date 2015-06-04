
package ch.hearc.meteo.imp.afficheur.real;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurFactory_I;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;



public class AfficheurFactory implements AfficheurFactory_I
	{

	@Override
	public AfficheurService_I createOnCentralPC(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		return new AfficheurServiceCentral(affichageOptions, meteoServiceRemote);
		}

	@Override
	public AfficheurService_I createOnLocalPC(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		String portCom = showComChooser();
		return new AfficheurServiceLocalFull(portCom, affichageOptions, meteoServiceRemote);
		}

	public AfficheurService_I createOnLocalPCLight()
	{
		String portCom = showComChooser();
		return new AfficheurServiceLocalLight(portCom);
	}


	private String showComChooser() {
		// TODO use Port to set connection
		String[] possibilities = { "COM1", "COM2", "COM3" };
		return (String) JOptionPane.showInputDialog(new JFrame(), "Choose a COM port", "COM port chooser", JOptionPane.PLAIN_MESSAGE, null, possibilities, "COM1");
	}

	}

