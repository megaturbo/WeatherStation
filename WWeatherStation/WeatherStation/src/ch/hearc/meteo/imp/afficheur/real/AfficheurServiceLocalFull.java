
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameMain;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurServiceLocalFull implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceLocalFull(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		manager = new Manager();
		frameService = new JFrameMain(manager);
		}

	@Override public void printPression(MeteoEvent event)
		{
		manager.printPression(event);
		frameService.refresh();
		}

	@Override public void printAltitude(MeteoEvent event)
		{
		manager.printAltitude(event);
		frameService.refresh();
		}

	@Override public void printTemperature(MeteoEvent event)
		{
		manager.printTemperature(event);
		frameService.refresh();
		}

	@Override public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Manager manager;
	private JFrameMain frameService;

	}
