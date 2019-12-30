import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame {

	private JButton reg;
	private JButton monster;

	public GUI() {
		super("Crawler");
		setLayout(new FlowLayout());

		reg = new JButton("Reg Button");
		add(reg);

		Icon Dio = new ImageIcon(getClass().getResource("Dio.png"));
		Icon Dio2 = new ImageIcon(getClass().getResource("Dio2.png"));

		monster = new JButton("Monster", Dio);
		monster.setRolloverIcon(Dio2);
		add(monster);

		HandelerClass handler = new HandelerClass();
		reg.addActionListener(handler);
		monster.addActionListener(handler);
	}

	private class HandelerClass implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
		}
	}
}
