
package ch.hearc.meteo.imp.com.real.port;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import gnu.io.CommPortIdentifier;

import ch.hearc.meteo.imp.com.real.MeteoFactory;
import ch.hearc.meteo.imp.com.real.MeteoService;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoAdapter;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.com.port.MeteoPortDetectionService_I;

public class MeteoPortDetectionService implements MeteoPortDetectionService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MeteoPortDetectionService()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public List<String> findListPortSerie()
		{
		Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		List<String> portList = new ArrayList<String>();
		while(portEnum.hasMoreElements())
			{
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL)
				{
				portList.add(portIdentifier.getName());
				}
			}
		return portList;
		}

	@Override
	public boolean isStationMeteoAvailable(String portName, long timeoutMS)
		{
		MeteoService station = (MeteoService)new MeteoFactory().create(portName);
		station.addMeteoListener(new MeteoAdapter()
			{
				@Override
				public void temperaturePerformed(MeteoEvent event)
					{
					isAStation = true;
					}
			});
		isAStation = false;
		try
			{
			station.connect();
			station.askTemperatureAsync();
			Thread.sleep(timeoutMS);
			station.disconnect();
			return isAStation;
			}
		catch (MeteoServiceException e)
			{
			//rien
			}
		catch (InterruptedException e)
			{
			System.out.println("Thread died while checking for ports.");
			}
		return isAStation;
		}

	@Override
	public List<String> findListPortMeteo(List<String> listPortExcluded)
		{
		List<String> meteoPortList = new ArrayList<String>();
		for(String port: listPortExcluded)
			{
			if(isStationMeteoAvailable(port, 1000))
				{
				meteoPortList.add(port);
				}
			}
		return meteoPortList;
		}

	@Override
	public List<String> findListPortMeteo()
		{
		return findListPortMeteo(findListPortSerie());
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
	//tools
	private boolean isAStation;
	}
