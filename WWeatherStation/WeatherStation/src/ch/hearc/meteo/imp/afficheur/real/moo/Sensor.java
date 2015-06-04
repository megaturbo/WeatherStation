
package ch.hearc.meteo.imp.afficheur.real.moo;

public enum Sensor
	{
	ALTITUDE(0, "Altitude"),
	TEMPERATURE(1, "Temperature"),
	PRESSURE(2, "Pressure");

	private final int id;
	private final String name;

	Sensor(int id, String name)
		{
		this.id = id;
		this.name = name;
		}

	String getName()
	{
		return name;
	}

	int getId()
	{
		return id;
	}

	}
