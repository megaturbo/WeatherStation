
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerCentral;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameCentral;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurServiceCentral implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private AfficheurServiceCentral()
		{
		manager = new ManagerCentral();
		frameService = new JFrameCentral(manager);
		}

	public static AfficheurServiceCentral getInstance()
		{
		if (INSTANCE == null)
			{
			INSTANCE = new AfficheurServiceCentral();
			}

		return INSTANCE;
		}

	public void addMeteoServiceRemote(MeteoServiceWrapper_I meteoServiceRemote)
		{
		manager.addMeteoServiceRemote(meteoServiceRemote);
		System.out.println("AFFICHEURSERVICECENTRAL");
		frameService.refresh();
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		manager.printPression(event);
		frameService.refresh();
		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		manager.printAltitude(event);
		frameService.refresh();
		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		manager.printTemperature(event);
		frameService.refresh();
		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		frameService.refresh();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerCentral manager;
	private JFrameCentral frameService;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	private static AfficheurServiceCentral INSTANCE = null;
	}
