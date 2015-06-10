
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerCentral;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameCentral;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;



public class AfficheurServiceCentral implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceCentral()
		{
		manager = new ManagerCentral();
		frameService = new JFrameCentral(manager);
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
	private ManagerCentral manager;
	private JFrameCentral frameService;
	}

