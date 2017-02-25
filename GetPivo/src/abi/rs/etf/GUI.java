package abi.rs.etf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JCheckBox[] chk = new JCheckBox[Main.num];
	private JLabel selected = new JLabel("Trenutno se ne zna ko!");
	private JButton button = new JButton("Odredi");

	public GUI() {
		super("PRE-ALPHA PIVO GETTER");

		setVisible(true);
		setBounds(50, 50, 850, 250);
		setResizable(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Main.writeFile();
				dispose();

			}

		});
		fillWindow();
	}

	private void fillWindow() {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 6));
		for (int i = 0; i < Main.num; i++) {
			p1.add(chk[i] = new JCheckBox(Main.everybody[i].getName(), Main.everybody[i].getIcon()));
		}
		add(p1, "North");
		button.addActionListener(new Odredjivac());
		add(button, "East");
		add(selected, "Center");
	}

	private class Odredjivac implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			double min = Double.MAX_VALUE;
			int shouldGo = 0;
			for (int i = 0; i < Main.num; i++) {
				if (chk[i].isSelected()) {
					Main.everybody[i].setDrank();
					Main.everybody[i].setPercent();
					if (min > Main.everybody[i].getPercent()) {
						min = Main.everybody[i].getPercent();
						shouldGo = i;
					} else if (min == Main.everybody[i].getPercent()) {
						if (Main.everybody[shouldGo].getWent() > Main.everybody[i].getWent()) {
							shouldGo = i;
						}
					}
				}
			}
			selected.setText("Treba da ide: " + Main.everybody[shouldGo].getName() + "!!!");
			Main.everybody[shouldGo].setWent();
			Main.everybody[shouldGo].setPercent();
			button.setText("PA NEK ODE PICKA!");
			button.setEnabled(false);
		}

	}

}
