
package ch.hearc.meteo.imp.com.real.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import ch.hearc.meteo.imp.com.logique.MeteoServiceCallback_I;
import ch.hearc.meteo.imp.com.real.com.trame.TrameDecoder;
import ch.hearc.meteo.imp.com.real.com.trame.TrameEncoder;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEventType_E;

// TODO student
//  Implémenter cette classe
//  Updater l'implémentation de MeteoServiceFactory.create()

/**
 * <pre>
 * Aucune connaissance des autres aspects du projet ici
 *
 * Ouvrer les flux vers le port com
 * ecouter les trames arrivantes (pas boucle, mais listener)
 * decoder trame
 * avertir meteoServiceCallback
 *
 *</pre>
 */
public class ComConnexion implements ComConnexions_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ComConnexion(MeteoServiceCallback_I meteoServiceCallback, String portName, ComOption comOption)
		{
		this.comOption = comOption;
		this.portName = portName;
		this.meteoServiceCallback = meteoServiceCallback;
		}

	/**
	 * <pre>
	 * Problem :
	 * 		MeteoService est un MeteoServiceCallback_I
	 * 		ComConnexions_I utilise MeteoServiceCallback_I
	 * 		MeteoService utilise ComConnexions_I
	 *
	 * On est dans la situation
	 * 		A(B)
	 * 		B(A)
	 *
	 * Solution
	 * 		 B
	 * 		 A(B)
	 *  	 B.setA(A)
	 *
	 *  Autrement dit:
	 *
	 *		ComConnexions_I comConnexion=new ComConnexion( portName,  comOption);
	 *      MeteoService_I meteoService=new MeteoService(comConnexion);
	 *      comConnexion.setMeteoServiceCallback(meteoService);
	 *
	 *      Ce travail doit se faire dans la factory
	 *
	 *  Warning : call next
	 *  	setMeteoServiceCallback(MeteoServiceCallback_I meteoServiceCallback)
	 *
	 *  </pre>
	 */
	public ComConnexion(String portName, ComOption comOption)
		{
		this(null, portName, comOption);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void start() throws Exception
		{
		if (connected)
			{
			if (!running)
				{
				questionner = new Thread(askQuestions());
				running = true;
				questionner.start();
				}
			}

		}

	@Override
	public void stop() throws Exception
		{
		// stop asking questions
		if (running)
			{
			Thread.sleep(100);
			questionner.join();
			running = false;
			}

		}

	@Override
	public void connect() throws NoSuchPortException, UnsupportedCommOperationException, PortInUseException, TooManyListenersException, IOException
		{
		// open stream
		if (!connected)
			{
			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier(portName);

			serialPort = (SerialPort)portID.open(portName, 10000);
			serialPort.setSerialPortParams(comOption.getSpeed(), comOption.getDataBit(), comOption.getStopBit(), comOption.getParity());
			serialPort.notifyOnDataAvailable(true);
			serialPort.addEventListener(new SerialPortEventListener()
				{

					@Override
					public void serialEvent(SerialPortEvent arg0)
						{
						switch(arg0.getEventType())
							{
							case SerialPortEvent.DATA_AVAILABLE:
								try
									{
									treatValues();
									}
								catch (IOException e)
									{
									System.err.println("IO error");
									}
								catch (MeteoServiceException e)
									{
									System.err.println("meteo error");
									}
								break;
							}
						}
				});
			reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			writer = serialPort.getOutputStream();
			connected = true;
			}

		}

	private void treatValues() throws IOException, MeteoServiceException
		{
		String line = reader.readLine();
		MeteoEventType_E dataType = TrameDecoder.dataType(line);
		float value = TrameDecoder.valeur(line);
		switch(dataType)
			{
			case ALTITUDE:
				meteoServiceCallback.altitudePerformed(value);
				break;
			case TEMPERATURE:
				meteoServiceCallback.temperaturePerformed(value);
				break;
			case PRESSION:
				meteoServiceCallback.pressionPerformed(value);
				break;
			}

		}

	@Override
	public void disconnect() throws IOException
		{
		// close stream
		if (connected)
			{
			reader.close();
			writer.close();
			serialPort.close();
			connected = false;
			}
		}

	@Override
	public void askAltitudeAsync() throws Exception
		{
		byte[] tabByte = TrameEncoder.coder("010200");
		System.out.println("asking for altitude");
		writer.write(tabByte);
		System.out.println("altitude asked");
		}

	@Override
	public void askPressionAsync() throws Exception
		{
		byte[] tabByte = TrameEncoder.coder("010000");
		writer.write(tabByte);
		}

	@Override
	public void askTemperatureAsync() throws Exception
		{
		byte[] tabByte = TrameEncoder.coder("010100");
		writer.write(tabByte);

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	@Override
	public String getNamePort()
		{
		return portName;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/**
	 * For post building
	 */
	public void setMeteoServiceCallback(MeteoServiceCallback_I meteoServiceCallback)
		{
		this.meteoServiceCallback = meteoServiceCallback;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private Runnable askQuestions() throws Exception
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					try
						{
						while(running)
							{
							askAltitudeAsync();
							askPressionAsync();
							askTemperatureAsync();
							}
						}
					catch (Exception e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
			};

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private ComOption comOption;
	private String portName;
	private MeteoServiceCallback_I meteoServiceCallback;

	// Tools
	private SerialPort serialPort;
	private OutputStream writer;
	private BufferedReader reader;
	private boolean running;
	private boolean connected;
	private Thread questionner;
	}
