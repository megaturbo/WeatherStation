
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameLocal;
import ch.hearc.meteo.imp.use.remote.pclocal.PCLocal;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;

public class AfficheurServiceLocal implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceLocal(PCLocal pcLocal)
		{
		manager = new ManagerLocal(pcLocal);
		frameService = new JFrameLocal(manager);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setCentralRemote(AfficheurServiceWrapper_I afficheurCentralRemote) {
		manager.setCentralRemote(afficheurCentralRemote);
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
	private ManagerLocal manager;
	private JFrameLocal frameService;

	}
