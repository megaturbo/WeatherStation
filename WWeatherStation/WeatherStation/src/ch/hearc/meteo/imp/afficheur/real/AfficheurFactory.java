
package ch.hearc.meteo.imp.afficheur.real;

import java.rmi.RemoteException;

import ch.hearc.meteo.imp.use.remote.pclocal.PCLocal;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurFactory_I;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurFactory implements AfficheurFactory_I
	{

	@Override
	@Deprecated
	public AfficheurService_I createOnCentralPC(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		return null;
		}

	@Override
	@Deprecated
	public AfficheurService_I createOnLocalPC(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		return null;
		}

	public AfficheurService_I createOnLocalPC(PCLocal local)
		{
		return new AfficheurServiceLocal(local);
		}

	public static AfficheurService_I getCentralInstance() throws RemoteException
		{
		if (CENTRAL_INSTANCE == null)
			{
			CENTRAL_INSTANCE = new AfficheurServiceCentral();
			}

		return CENTRAL_INSTANCE;
		}


	private static AfficheurService_I CENTRAL_INSTANCE = null;

	}
