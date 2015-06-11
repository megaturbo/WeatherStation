
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
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
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
	public void start(MeteoServiceOptions meteoServiceOptions) throws Exception
		{
		if (connected)
			{
			if (!running)
				{
				temperatureAsker = new Thread(askTemperatureQuestion(meteoServiceOptions.getTemperatureDT()));
				pressureAsker = new Thread(askPressureQuestion(meteoServiceOptions.getPressionDT()));
				altitudeAsker = new Thread(askAltitudeQuestion(meteoServiceOptions.getAltitudeDT()));

				temperatureAsker.start();
				pressureAsker.start();
				altitudeAsker.start();

				running = true;

				}
			}

		}

	@Override
	public void stop() throws Exception
		{
		// stop asking questions
		if (running)
			{
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
			if(running)
				{
				try
					{
					stop();
					}
				catch (Exception e)
					{
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			reader.close();
			writer.close();
			serialPort.close();
			connected = false;
			}
		}

	@Override
	public void askAltitudeAsync() throws Exception
		{
		if (connected && running)
			{
			byte[] tabByte = TrameEncoder.coder("010200");
			writer.write(tabByte);
			}
		}

	@Override
	public void askPressionAsync() throws Exception
		{
		if (connected && running)
			{
			byte[] tabByte = TrameEncoder.coder("010000");
			writer.write(tabByte);
			}
		}

	@Override
	public void askTemperatureAsync() throws Exception
		{
		running = true; //used for checking the connectivity to a meteostation from the port detection class
		if (connected && running)
			{
			byte[] tabByte = TrameEncoder.coder("010100");
			writer.write(tabByte);
			}
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

	private Runnable askAltitudeQuestion(long delay)
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
							Thread.sleep(delay);
							askAltitudeAsync();
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

	private Runnable askPressureQuestion(long delay)
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
							Thread.sleep(delay);
							askPressionAsync();
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

	private Runnable askTemperatureQuestion(long delay)
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
							Thread.sleep(delay);
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

	private Thread temperatureAsker;
	private Thread pressureAsker;
	private Thread altitudeAsker;

	}
