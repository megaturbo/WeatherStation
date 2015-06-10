
package ch.hearc.meteo.imp.use.remote.pclocal;


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
		//		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
		//		AffichageOptions affichageOptions = new AffichageOptions(3, RmiTools.getLocalHost() + "");
		//		RmiURL rmiURL = new RmiURL(IdTools.createID(RemoteAfficheurCreator.RMI_ID)); //TODO not localhost
		//		String portCom = "COM5";
		//
		//		new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiURL).run();
		new PCLocal().run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
