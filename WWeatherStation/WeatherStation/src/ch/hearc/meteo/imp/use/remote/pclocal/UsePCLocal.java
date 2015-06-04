
package ch.hearc.meteo.imp.use.remote.pclocal;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

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
		RmiURL rmiURL = new RmiURL(RemoteAfficheurCreator.RMI_ID); //TODO not localhost
		new PCLocal(new MeteoServiceOptions(800, 1000, 1200), "COM5", new AffichageOptions(3, RmiTools.getLocalHost() + ""), rmiURL).run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
