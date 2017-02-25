package abi.rs.etf;

import javax.swing.ImageIcon;

public class Person {
	private String name;
	private int went;
	private int drank;
	private double percent;
	private ImageIcon icon;

	public Person(String s, int w, int d) {
		name = s;
		went = w;
		drank = d;
		String iconPath = s + ".jpg";
		icon = new ImageIcon(iconPath);
		try {
			percent = ((double) w) / ((double) d);
		} catch (Exception e) {
			System.out.println("Can't divide by 0!");
			System.exit(1);
		}
	}

	public String getName() {
		return name;
	}

	public int getWent() {
		return went;
	}

	public int getDrank() {
		return drank;
	}

	public double getPercent() {
		return percent;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setWent() {
		went++;
	}

	public void setDrank() {
		drank++;
		setPercent();
	}

	public void setPercent() {
		percent = ((double) went) / ((double) drank);
	}

	public String toString() {
		return name + " " + went + " " + drank + " " + percent;
	}
}
