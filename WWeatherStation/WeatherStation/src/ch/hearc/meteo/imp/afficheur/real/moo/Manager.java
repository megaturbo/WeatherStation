
package ch.hearc.meteo.imp.afficheur.real.moo;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.Waypoint;

import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.com.meteo.listener.event.Sources;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class Manager
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Manager(MeteoServiceWrapper_I meteoServiceRemote)
		{

		this.meteoServiceRemote = meteoServiceRemote;

		collectionAltitude = new TimeSeriesCollection();
		collectionPression = new TimeSeriesCollection();
		collectionTemperature = new TimeSeriesCollection();
		stationFromSources = new HashMap<Sources, Station>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void printAltitude(MeteoEvent event)
		{
		manage(Sensor.ALTITUDE, event);
		}

	public void printPression(MeteoEvent event)
		{
		manage(Sensor.PRESSURE, event);
		}

	public void printTemperature(MeteoEvent event)
		{
		manage(Sensor.TEMPERATURE, event);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				remote			*|
	\*------------------------------*/

	public void setMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions) throws RemoteException
		{
		meteoServiceRemote.setMeteoServiceOptions(meteoServiceOptions);
		}

	public MeteoServiceOptions getMeteoServiceOptions() throws RemoteException
		{
		return this.meteoServiceRemote.getMeteoServiceOptions();
		}

	public Collection<Station> getStationList()
		{
		return this.stationFromSources.values();
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

	public TimeSeries getSeriesFromPort(String portCom, Sensor sensor) {

		TimeSeries series = null;
		for(Station s:stationFromSources.values()) {
			if(s.getName().equals(portCom)) {
				series = s.getSeries(sensor);
			}
		}

		return series;
	}

	public Set<? extends Waypoint> getWaypoints()
		{
		Set<DefaultWaypoint> waypoints = new HashSet<DefaultWaypoint>();
		for(Entry<Sources, Station> entry:stationFromSources.entrySet())
			{
			Station station = entry.getValue();
			if (station.isVisible())
				{
				waypoints.add(station.getWaypoint());
				}
			}
		return waypoints;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void manage(Sensor sensor, MeteoEvent event)
		{
		Sources source = event.getSource();

		if (!stationFromSources.containsKey(source))
			{
			createNewKey(source);
			}

		RegularTimePeriod time = new Millisecond(new Date(event.getTime()));
		Station station = stationFromSources.get(source);
		TimeSeries series = station.getSeries(sensor);

		series.add(time, event.getValue());
		}

	private void createNewKey(Sources source)
		{
		Station station = new Station(source);
		stationFromSources.put(source, station);

		collectionAltitude.addSeries(station.getSeriesAltitude());
		collectionPression.addSeries(station.getSeriesPression());
		collectionTemperature.addSeries(station.getSeriesTemperature());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private MeteoServiceWrapper_I meteoServiceRemote;
	// Tools
	private TimeSeriesCollection collectionAltitude;
	private TimeSeriesCollection collectionPression;
	private TimeSeriesCollection collectionTemperature;
	private Map<Sources, Station> stationFromSources;

	}
