
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.real.AfficheurFactory;
import ch.hearc.meteo.imp.com.real.MeteoFactory;
import ch.hearc.meteo.imp.com.real.MeteoService;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class PCLocal implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCLocal(MeteoServiceOptions meteoServiceOptions, String portCom, AffichageOptions affichageOptions, RmiURL rmiURLafficheurManager)
		{
		this.meteoServiceOptions = meteoServiceOptions;
		this.portCom = portCom;
		this.affichageOptions = affichageOptions;
		this.rmiURLafficheurManager = rmiURLafficheurManager;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			server(); // avant
			}
		catch (Exception e)
			{
			System.err.println("[PCLocal :  run : server : failed");
			e.printStackTrace();
			}

		try
			{
			client();
			}
		catch (RemoteException | UnknownHostException | NotBoundException e)
			{
			System.err.println("[PCLocal :  run : client : failed");
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				server			*|
	\*------------------------------*/

	private void server() throws MeteoServiceException, RemoteException
		{
		MeteoService meteoService = (MeteoService)new MeteoFactory().create(portCom);
//		meteoService.connect();
//		meteoService.start(meteoServiceOptions);

		MeteoServiceWrapper_I meteoServiceWrapper = new MeteoServiceWrapper(meteoService);

		AfficheurService_I afficheurService = new AfficheurFactory().createOnLocalPCLight(meteoServiceWrapper);

		System.out.println("PC Local: sharing meteoServiceWrapper");
		RmiTools.shareObject(meteoServiceWrapper, rmiURLafficheurManager);
		System.out.println("PC Local: sharing success\n");
		}

	/*------------------------------*\
	|*				client			*|
	\*------------------------------*/

	private void client() throws RemoteException, UnknownHostException, NotBoundException
		{
		//		String id_creator = IdTools.createID(RemoteAfficheurCreator.RMI_ID_CREATOR);
		RmiURL rmiURL = new RmiURL(RemoteAfficheurCreator.RMI_ID_CREATOR); //TODO not localhost
		RemoteAfficheurCreator_I remoteAfficheurCreator = (RemoteAfficheurCreator_I)RmiTools.connectionRemoteObject(rmiURL);

		//		RemoteAfficheurCreator_I remoteAfficheurCreator = RemoteAfficheurCreator.getInstance();

		RmiURL afficheurServicermiURL = remoteAfficheurCreator.createRemoteAfficheurService(affichageOptions, rmiURLafficheurManager);

		System.out.println("PC Local: connecting to afficheurService");
		afficheurServiceRemote = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObject(afficheurServicermiURL); //
		System.out.println("PC Local: connection success\n");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private MeteoServiceOptions meteoServiceOptions;
	private String portCom;
	private AffichageOptions affichageOptions;
	private RmiURL rmiURLafficheurManager;

	// Tools
	private AfficheurServiceWrapper_I afficheurServiceRemote;
	}
