
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameLocalLight;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;



public class AfficheurServiceLocalLight implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceLocalLight(MeteoServiceWrapper_I meteoServiceRemote) {
		manager = new Manager(meteoServiceRemote);
		frameService = new JFrameLocalLight(meteoServiceRemote, manager);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printPression(MeteoEvent event) {

	}

	@Override
	public void printAltitude(MeteoEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printTemperature(MeteoEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMeteoServiceOptions(
			MeteoServiceOptions meteoServiceOptions) {
		// TODO Auto-generated method stub
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
	private JFrameLocalLight frameService;

	}

