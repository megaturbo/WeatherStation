
package ch.hearc.meteo.imp.afficheur.real.moo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.com.meteo.listener.event.Sources;

public class Manager
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Manager()
		{
		collectionAltitude = new TimeSeriesCollection();
		collectionPression = new TimeSeriesCollection();
		collectionTemperature = new TimeSeriesCollection();

		mapSourcesStation = new HashMap<Sources, Station>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void printAltitude(MeteoEvent event)
		{
		manage(collectionAltitude, Sensor.ALTITUDE, event);
		}

	public void printPression(MeteoEvent event)
		{
		manage(collectionPression, Sensor.PRESSURE, event);
		}

	public void printTemperature(MeteoEvent event)
		{
		manage(collectionTemperature, Sensor.TEMPERATURE, event);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Collection<Station> getStationList()
		{
		return this.mapSourcesStation.values();
		}

	public TimeSeriesCollection getCollectionAltitude()
		{
		return this.collectionAltitude;
		}

	public TimeSeriesCollection getCollectionPression()
		{
		return this.collectionPression;
		}

	public TimeSeriesCollection getCollectionTemperature()
		{
		return this.collectionTemperature;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void manage(TimeSeriesCollection collection, Sensor sensor, MeteoEvent event)
		{
		Sources source = event.getSource();

		if(!mapSourcesStation.containsKey(source)) {
			Station station = new Station(source);
			mapSourcesStation.put(source, station);
			collection.addSeries(station.getSeries(sensor));
		}

		RegularTimePeriod time = new Millisecond(new Date(event.getTime()));
		TimeSeries series = mapSourcesStation.get(source).getSeries(sensor);
		series.add(time, event.getValue());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private TimeSeriesCollection collectionAltitude;
	private TimeSeriesCollection collectionPression;
	private TimeSeriesCollection collectionTemperature;

	private Map<Sources, Station> mapSourcesStation;

	}
