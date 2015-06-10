
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameLocal;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;



public class AfficheurServiceLocal implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceLocal(MeteoServiceWrapper_I meteoServiceRemote) {
		manager = new Manager(meteoServiceRemote);
		frameService = new JFrameLocal(meteoServiceRemote, manager);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printPression(MeteoEvent event) {
		manager.printPression(event);
	}

	@Override
	public void printAltitude(MeteoEvent event) {
		manager.printAltitude(event);
	}

	@Override
	public void printTemperature(MeteoEvent event) {
		manager.printTemperature(event);
	}

	@Override
	public void updateMeteoServiceOptions(
			MeteoServiceOptions meteoServiceOptions) {
		frameService.updateMeteoServiceOptions(meteoServiceOptions);
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Manager manager;
	private JFrameLocal frameService;

	}
