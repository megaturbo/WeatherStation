
package ch.hearc.meteo.imp.afficheur.real.customs;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class QKTCheckBox extends JCheckBox
	{

	public QKTCheckBox(String text)
		{
		super(text);

		this.setForeground(Color.DARK_GRAY);
		this.setIcon(new ImageIcon("assets/unchecked_checkbox.png"));
		this.setSelectedIcon(new ImageIcon("assets/checked_checkbox.png"));
		}

	}