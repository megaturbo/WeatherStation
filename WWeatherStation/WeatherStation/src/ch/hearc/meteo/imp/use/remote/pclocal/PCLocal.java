
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.real.AfficheurServiceLocal;
import ch.hearc.meteo.imp.com.real.MeteoFactory;
import ch.hearc.meteo.imp.com.real.MeteoService;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoAdapter;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

import com.bilat.tools.reseau.rmi.IdTools;
import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class PCLocal implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCLocal(MeteoServiceOptions meteoServiceOptions, String portCom, AffichageOptions affichageOptions, RmiURL rmiURLafficheurManager)
		{
		//		this.meteoServiceOptions = meteoServiceOptions;
		//		this.portCom = portCom;
		//		this.affichageOptions = affichageOptions;
		//		this.rmiURLafficheurManager = rmiURLafficheurManager;
		}

	public PCLocal()
		{

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		afficheurService = new AfficheurServiceLocal(this);
		//		try
		//			{
		//			server(); // avant
		//			}
		//		catch (Exception e)
		//			{
		//			System.err.println("[PCLocal :  run : server : failed");
		//			e.printStackTrace();
		//			}
		//
		//		try
		//			{
		//			client();
		//			}
		//		catch (RemoteException | UnknownHostException | NotBoundException e)
		//			{
		//			System.err.println("[PCLocal :  run : client : failed");
		//			e.printStackTrace();
		//			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				server			*|
	\*------------------------------*/

	public MeteoServiceWrapper_I createMeteoService(String portCom)
		{

		MeteoService meteoService = (MeteoService)new MeteoFactory().create(portCom);

		try
			{
			meteoService.connect();
			}
		catch (MeteoServiceException e1)
			{
			e1.printStackTrace();
			}

		meteoService.addMeteoListener(new MeteoAdapter()
			{

				@Override
				public void temperaturePerformed(MeteoEvent event)
					{
					afficheurService.printTemperature(event);
					}

				@Override
				public void altitudePerformed(MeteoEvent event)
					{
					afficheurService.printAltitude(event);
					}

				@Override
				public void pressionPerformed(MeteoEvent event)
					{
					afficheurService.printPression(event);
					}

			});

		MeteoServiceWrapper_I meteoServiceWrapper = new MeteoServiceWrapper(meteoService);
		RmiURL rmiURLmeteoService = new RmiURL(IdTools.createID(RemoteAfficheurCreator.RMI_ID)); //TODO not localhost
		try
			{
			RmiTools.shareObject(meteoServiceWrapper, rmiURLmeteoService);
			}
		catch (RemoteException e)
			{
			e.printStackTrace();
			}

		RmiURL rmiURLRemoteAfficheurCreator = new RmiURL(RemoteAfficheurCreator.RMI_ID_CREATOR); //TODO not localhost
		System.out.println(RemoteAfficheurCreator.RMI_ID_CREATOR);
		try
			{
			RemoteAfficheurCreator_I remoteAfficheurCreator = (RemoteAfficheurCreator_I)RmiTools.connectionRemoteObject(rmiURLRemoteAfficheurCreator);
			RmiURL afficheurServicermiURL = remoteAfficheurCreator.createRemoteAfficheurService(null, rmiURLmeteoService);
			//			afficheurServiceRemote = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObject(afficheurServicermiURL);
			}
		catch (RemoteException | NotBoundException e)
			{
			System.out.println("\nLe pc central est introuvable. Veuillez vérifier que vous avez une connexion à cet ordinateur.");
			}
		return meteoServiceWrapper;
		}

	@Deprecated
	private void server() throws MeteoServiceException, RemoteException
		{
		//		MeteoService meteoService = (MeteoService)new MeteoFactory().create(portCom);

		//ROULIN HANDLES THIS
		//		meteoService.connect();
		//		meteoService.start(meteoServiceOptions);
		//
		//		MeteoServiceWrapper_I meteoServiceWrapper = new MeteoServiceWrapper(meteoService);

		//		AfficheurService_I afficheurService = new AfficheurFactory().createOnLocalPC(null, meteoServiceWrapper);

		// afficheurService.add

		//		meteoService.addMeteoListener(new MeteoAdapter()
		//			{
		//
		//				@Override
		//				public void temperaturePerformed(MeteoEvent event)
		//					{
		//					afficheurService.printTemperature(event);
		//					}
		//
		//				@Override
		//				public void altitudePerformed(MeteoEvent event)
		//					{
		//					afficheurService.printAltitude(event);
		//					}
		//
		//				@Override
		//				public void pressionPerformed(MeteoEvent event)
		//					{
		//					afficheurService.printPression(event);
		//					}
		//
		//			});

		//		System.out.println("PC Local: sharing meteoServiceWrapper");
		//		RmiTools.shareObject(meteoServiceWrapper, rmiURLafficheurManager);
		//		System.out.println("PC Local: sharing success\n");
		}

	/*------------------------------*\
	|*				client			*|
	\*------------------------------*/

	@Deprecated
	private void client() throws RemoteException, UnknownHostException, NotBoundException
		{
		//		String id_creator = IdTools.createID(RemoteAfficheurCreator.RMI_ID_CREATOR);
		//		RmiURL rmiURL = new RmiURL(RemoteAfficheurCreator.RMI_ID_CREATOR); //TODO not localhost
		//		RemoteAfficheurCreator_I remoteAfficheurCreator = (RemoteAfficheurCreator_I)RmiTools.connectionRemoteObject(rmiURL);
		//
		//		//		RemoteAfficheurCreator_I remoteAfficheurCreator = RemoteAfficheurCreator.getInstance();
		//
		//		RmiURL afficheurServicermiURL = remoteAfficheurCreator.createRemoteAfficheurService(affichageOptions, rmiURLafficheurManager);
		//
		//		System.out.println("PC Local: connecting to afficheurService");
		//		afficheurServiceRemote = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObject(afficheurServicermiURL); //
		//		System.out.println("PC Local: connection success\n");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	//	private MeteoServiceOptions meteoServiceOptions;
	//	private String portCom;
	//	private AffichageOptions affichageOptions;
	//	private RmiURL rmiURLafficheurManager;
	// Tools
	//	private AfficheurServiceWrapper_I afficheurServiceRemote;
	private AfficheurService_I afficheurService;
	}
