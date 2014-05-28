package src.GUIpack;

//IntroExample.java
//
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class RMenu extends JMenuBar implements ActionListener {

	String[] fileItems = new String[] { "Change User", "Launch Mode",
			"Target Mode", "Save", "Load", "Toggle Music" };
	String[] editItems = new String[] { "Undo", "Cut", "Copy", "Paste" };
	char[] fileShortcuts = { 'N', 'O', 'S', 'X' , 'L','M'};
	char[] editShortcuts = { 'Z', 'X', 'C', 'V' };

	public RMenu() {

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu otherMenu = new JMenu("Other");
		JMenu subMenu = new JMenu("SubMenu");
		JMenu subMenu2 = new JMenu("SubMenu2");

		setBackground(Color.pink);
		fileMenu.setBackground(Color.black);
		Font font = new Font("Verdana", Font.BOLD, 15);

		setForeground(Color.red);
		setFont(font);

		
		fileMenu.setFont(font);

		for (int i = 0; i < fileItems.length; i++) {
			JMenuItem item = new JMenuItem(fileItems[i], fileShortcuts[i]);
			item.addActionListener(this);
			fileMenu.add(item);
		}

		// Assemble the File menus with keyboard accelerators.
		for (int i = 0; i < editItems.length; i++) {
			JMenuItem item = new JMenuItem(editItems[i]);
			item.setAccelerator(KeyStroke
					.getKeyStroke(editShortcuts[i], Toolkit.getDefaultToolkit()
							.getMenuShortcutKeyMask(), false));
			item.addActionListener(this);
			editMenu.add(item);
		}
		

		// Insert a separator in the Edit menu in Position 1 after "Undo".
		editMenu.insertSeparator(1);

		// Assemble the submenus of the Other menu.
		JMenuItem item;
		subMenu2.add(item = new JMenuItem("Extra 2"));
		item.addActionListener(this);
		subMenu.add(item = new JMenuItem("Extra 1"));
		item.addActionListener(this);
		subMenu.add(subMenu2);

		// Assemble the Other menu itself.
		otherMenu.add(subMenu);
		otherMenu.add(item = new JCheckBoxMenuItem("Check Me"));
		item.addActionListener(this);
		otherMenu.addSeparator();
		ButtonGroup buttonGroup = new ButtonGroup();
		otherMenu.add(item = new JRadioButtonMenuItem("Radio 1"));
		item.addActionListener(this);
		buttonGroup.add(item);
		otherMenu.add(item = new JRadioButtonMenuItem("Radio 2"));
		item.addActionListener(this);
		buttonGroup.add(item);
		otherMenu.addSeparator();
		otherMenu.add(item = new JMenuItem("Potted Plant", new ImageIcon(
				"image.gif")));
		item.addActionListener(this);

		// Finally, add all the menus to the menu bar.
		add(fileMenu);
		// add(editMenu);
		// add(otherMenu);
	}

	public static void main(String s[]) {
		JFrame frame = new JFrame("Simple Menu Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new RMenu());
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
