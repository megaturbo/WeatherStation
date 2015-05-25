
package ch.hearc.meteo.imp.afficheur.real.moo;

public enum Sensor
	{
	ALTITUDE(0),
	TEMPERATURE(1),
	PRESSURE(2);

	private final int id;

	Sensor(int id)
		{
		this.id = id;
		}
	}
