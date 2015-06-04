
package ch.hearc.meteo.imp.reseau;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.real.AfficheurServiceLocalFull;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

import com.bilat.tools.reseau.rmi.IdTools;
import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

/**
 * <pre>
 * One instance only (Singleton) on PC-Central
 * RMI-Shared
 * </pre>
 */
public class RemoteAfficheurCreator implements RemoteAfficheurCreator_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private RemoteAfficheurCreator() throws RemoteException
		{
		server();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Remote use
	 */
	@Override
	public RmiURL createRemoteAfficheurService(AffichageOptions affichageOptions, RmiURL meteoServiceRmiURL) throws RemoteException
		{
		MeteoServiceWrapper_I meteoServiceRemote = null;
			// client
			{
			try
				{
				meteoServiceRemote = (MeteoServiceWrapper_I)RmiTools.connectionRemoteObject(meteoServiceRmiURL); //
				}
			catch (NotBoundException e)
				{
				e.printStackTrace();
				}
			}

			// server
			{
			AfficheurService_I afficheurService = createAfficheurService(affichageOptions, meteoServiceRemote);
			AfficheurServiceWrapper_I afficheurServiceWrapper = new AfficheurServiceWrapper(afficheurService); //

			RmiURL afficheurServicermiURL = rmiUrl();
			RmiTools.shareObject(afficheurServiceWrapper, afficheurServicermiURL); //
			return afficheurServicermiURL; // Retourner le RMI-ID pour une
											// connection distante sur le
											// serveur d'affichage
			}
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized RemoteAfficheurCreator_I getInstance() throws RemoteException
		{
		if (INSTANCE == null)
			{
			INSTANCE = new RemoteAfficheurCreator();
			}

		return INSTANCE;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private AfficheurService_I createAfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		return new AfficheurServiceLocalFull(meteoServiceRemote); //
		}

	private void server() throws RemoteException
		{
		// TODO share this
		String id = IdTools.createID(RMI_ID_CREATOR);//
		RmiTools.shareObject(this, new RmiURL(id)); //
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/**
	 * Thread Safe
	 */
	private static RmiURL rmiUrl()
		{
		String id = IdTools.createID(PREFIXE);
		return new RmiURL(id);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	// Tools

	private static RemoteAfficheurCreator_I INSTANCE = null;

	// Tools final
	private static final String PREFIXE = "AFFICHEUR_SERVICE";

	public static final String RMI_ID = PREFIXE;
	public static final String RMI_ID_CREATOR = "CREATOR_ID";

	}
