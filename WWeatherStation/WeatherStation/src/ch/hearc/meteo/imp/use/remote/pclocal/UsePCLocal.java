
package ch.hearc.meteo.imp.use.remote.pclocal;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

import com.bilat.tools.reseau.rmi.IdTools;
import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class UsePCLocal
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		String portCom = "COM5";
		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
		AffichageOptions affichageOptions = new AffichageOptions(3, RmiTools.getLocalHost() + "");
		RmiURL rmiURL = new RmiURL(IdTools.createID(RemoteAfficheurCreator.RMI_ID)); //TODO not localhost
		new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiURL).run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
