
package ch.hearc.meteo.imp.com.real.port;

import java.util.List;

import ch.hearc.meteo.imp.com.real.MeteoFactory;
import ch.hearc.meteo.imp.com.real.MeteoService;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoListener_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;



public class UseMeteoPortDetectionService
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		MeteoPortDetectionService meteoPortDetectionService= (MeteoPortDetectionService)new MeteoPortDetectionServiceFactory().create();
		List<String> list = meteoPortDetectionService.findListPortMeteo();
		System.out.println(list.toString());
		MeteoService station = (MeteoService)new MeteoFactory().create(list.get(0));
		try
			{
			station.connect();
			}
		catch (MeteoServiceException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(100,100,1000);
		station.start(meteoServiceOptions);
		station.addMeteoListener(new MeteoListener_I()
			{

				@Override
				public void temperaturePerformed(MeteoEvent event)
					{
					// TODO Auto-generated method stub
					System.out.println(event+"\n");
					}

				@Override
				public void pressionPerformed(MeteoEvent event)
					{
					// TODO Auto-generated method stub
					System.out.println(event+"\n");
					}

				@Override
				public void altitudePerformed(MeteoEvent event)
					{
					// TODO Auto-generated method stub
					System.out.println(event+"\n");
					}
			});
		int x = 0;
		for(int i = 1; i <= 10000; i++)
			{
			x += (i * i)* 2;
			}
		station.stop();
		try
			{
			station.disconnect();
			}
		catch (MeteoServiceException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

