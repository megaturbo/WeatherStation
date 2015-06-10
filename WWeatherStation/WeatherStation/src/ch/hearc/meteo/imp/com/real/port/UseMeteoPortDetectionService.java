
package ch.hearc.meteo.imp.com.real.port;

import java.util.List;



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
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

