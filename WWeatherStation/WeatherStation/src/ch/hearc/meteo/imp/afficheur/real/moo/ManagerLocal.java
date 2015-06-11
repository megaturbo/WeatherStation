
package ch.hearc.meteo.imp.afficheur.real.moo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.use.remote.pclocal.PCLocal;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.com.meteo.listener.event.Sources;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class ManagerLocal
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ManagerLocal(PCLocal pcLocal)
		{
		this.pcLocal = pcLocal;
		this.meteoRemotes = new ArrayList<MeteoServiceWrapper_I>();

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


	public AfficheurServiceWrapper_I getCentralRemote()
		{
		return this.centralRemote;
		}

	public void setCentralRemote(AfficheurServiceWrapper_I afficheurCentralRemote)
		{
		this.centralRemote = afficheurCentralRemote;
		}

	public void addMeteoService(String portCom)
		{
		meteoRemotes.add(pcLocal.createMeteoService(portCom));
		}

	public List<MeteoServiceWrapper_I> getRemotes()
		{
		return meteoRemotes;
		}

	public void removeRemote(MeteoServiceWrapper_I meteoRemote)
		{
		meteoRemotes.remove(meteoRemote);
		}

	public void setMeteoServiceOptions(String portCom, MeteoServiceOptions meteoServiceOptions)
		{
		for(MeteoServiceWrapper_I remote:meteoRemotes)
			{
			try
				{
				if (remote.getPort().equals(portCom))
					{
					centralRemote.updateMeteoServiceOptions(meteoServiceOptions);
					remote.setMeteoServiceOptions(meteoServiceOptions);
					}
				}
			catch (RemoteException e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}

	public MeteoServiceOptions getMeteoServiceOptions(String portCom)
		{
		for(MeteoServiceWrapper_I remote:meteoRemotes)
			{
			try
				{
				if (remote.getPort().equals(portCom)) { return remote.getMeteoServiceOptions(); }
				}
			catch (RemoteException e)
				{
				e.printStackTrace();
				}
			}
		return new MeteoServiceOptions(1000, 1000, 1000);
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

	public TimeSeries getSeriesFromPort(String portCom, Sensor sensor)
		{

		TimeSeries series = null;
		for(Station s:stationFromSources.values())
			{
			if (s.getName().equals(portCom))
				{
				series = s.getSeries(sensor);
				}
			}

		return series;
		}

	public List<GeoPosition> getGeopositions()
		{
		List<GeoPosition> geopositions = new ArrayList<GeoPosition>();
		for(Entry<Sources, Station> entry:stationFromSources.entrySet())
			{
			Station station = entry.getValue();
			geopositions.add(station.getGeoposition());
			}
		return geopositions;
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

		System.out.println(source.getPort() + " has received: " + event.getValue());

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
	private PCLocal pcLocal;
	private AfficheurServiceWrapper_I centralRemote;
	private List<MeteoServiceWrapper_I> meteoRemotes;

	// Tools
	private TimeSeriesCollection collectionAltitude;
	private TimeSeriesCollection collectionPression;
	private TimeSeriesCollection collectionTemperature;
	private Map<Sources, Station> stationFromSources;

	}
