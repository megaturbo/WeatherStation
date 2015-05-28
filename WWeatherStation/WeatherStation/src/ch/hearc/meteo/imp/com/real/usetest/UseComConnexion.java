
package ch.hearc.meteo.imp.com.real.usetest;

import java.io.IOException;
import java.util.TooManyListenersException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import ch.hearc.meteo.imp.com.real.com.ComConnexion;
import ch.hearc.meteo.imp.com.real.com.ComOption;



public class UseComConnexion
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
			ComOption options = new ComOption();
			System.out.println("options created");
			ComConnexion connexion = new ComConnexion("COM5", options);
			System.out.println("connexion created");
			try
				{
				System.out.println("attempting connexion");
				connexion.connect();
				System.out.println("connexion established");
				}
			catch (NoSuchPortException e)
				{
				System.err.println("port error");
				}
			catch (UnsupportedCommOperationException e)
				{
				System.err.println("unsupported operation");
				}
			catch (PortInUseException e)
				{
				System.err.println("port is already in use");
				}
			catch (TooManyListenersException e)
				{
				System.err.println("too many listeners!");
				}
			catch (IOException e)
				{

				}
			try
				{
				connexion.start();
				}
			catch (Exception e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}

		/*------------------------------------------------------------------*\
		|*							Methodes Private						*|
		\*------------------------------------------------------------------*/

	}

