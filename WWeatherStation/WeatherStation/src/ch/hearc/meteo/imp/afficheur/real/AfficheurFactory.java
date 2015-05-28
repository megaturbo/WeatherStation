
package ch.hearc.meteo.imp.afficheur.real;

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
		return new AfficheurServiceLocalFull(affichageOptions, meteoServiceRemote);
		}

	}

