package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.jgoodies.looks.Options;

public class RocketF extends JFrame {

	public LaunchPanel2 mpanel = new LaunchPanel2();

	public WPanel wpanel = new WPanel();
	public MenuP menubar = new MenuP();
	public JPanel htmlpanel = new JPanel();
	HTMLFile html = new HTMLFile("st");
	public RocketBuilderPanel rBuilder = new RocketBuilderPanel();
	public static RocketMath mRocket;
	// public CPanel cpanel = new CPanel();
	public String user;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RocketF() {
		super();
		
		UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
		Options.setDefaultIconSize(new Dimension(18, 18));

		// try {
		//
		// UIManager.setLookAndFeel(new PlasticLookAndFeel());
		// } catch (Exception e) {
		// System.err.println("Can't set look & feel:" + e);
		// }
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Water Bottle Rocket Launch");
		setSize(2000, 1000);

		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// getContentPane().setBackground(Color.cyan);

		add(wpanel);

		setJMenuBar(menubar);
		
		htmlpanel.add(html);
		htmlpanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
		html.setPreferredSize(new Dimension(getWidth(), getHeight()));
	}

	public void mInit() {
		removeAll();
		// add(cpanel,BorderLayout.NORTH);
		add(mpanel);
		repaint();
	}

	public void removeAll() {
		remove(wpanel);
		remove(mpanel);
		remove(rBuilder);
		remove(htmlpanel);

	}

	public void switchTo(JComponent p) {
		removeAll();
		add(p);
		repaint();
		refFrame(this);

	}

	public void switchUser() {

		wpanel.reset();
		switchTo(wpanel);

		refFrame(this);
		// System.out.println(user);
	}

	public static void main(String[] args) {

		RocketF frame = new RocketF();
		// JFrame j = new JFrame();
		// JEditorPane editorPane;
		//
		// editorPane = new JEditorPane();
		// editorPane.setEditable(false);
		//
		// URL helpURL = null;
		// helpURL =
		// HTMLFile.class.getResource("/com/htmlPack/RocketExplanation.html");
		// //URL("RocketExplanation.html");
		// if (helpURL != null) {
		// try {
		// editorPane.setPage(helpURL);
		// } catch (IOException e) {
		// System.err.println("Attempted to read a bad URL: " + helpURL);
		// }
		// } else {
		// System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
		// }
		//
		// //Put the editor pane in a scroll pane.
		// JScrollPane s = new JScrollPane();
		// s.setVerticalScrollBarPolicy(
		// JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// s.setPreferredSize(new Dimension(250, 145));
		// s.setMinimumSize(new Dimension(10, 10));
		//

	}

	public class WPanel extends WelcomePanel {

		public WPanel() {
			super();
			setSize(getWidth(), getHeight());

		}

		public void reset() {
			pane.remove(launch);
			pane.remove(target);
			pane.remove(builder);
			pane.remove(exp);

			pane.add(tpane);
			pane.setLayout(new BorderLayout());
			tpane.add(j);
			j.requestFocus();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("launch")) {
				System.out.println(e.getActionCommand());

				mInit();

			} else if (e.getActionCommand().equals("user")) {
				user = j.getText();
				// mpanel.updateUser(user);
				pane.remove(tpane);
				pane.setLayout(new GridLayout());
				pane.add(launch, BorderLayout.NORTH);
				pane.add(target, BorderLayout.NORTH);
				pane.add(builder, BorderLayout.NORTH);
				pane.add(exp, BorderLayout.NORTH);
				repaint();
			} else if (e.getActionCommand().equals("builder")) {
				switchTo(rBuilder);
			} else if (e.getActionCommand().equals("exp")) {
				switchTo(htmlpanel);
			}
			pane.repaint();

			try {
				SwingUtilities.getRoot(this).setVisible(true);
			} catch (Exception eve) {
			}
		}

	}
public static void sleep(long a)
{
	try {
		Thread.sleep(a);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public static void refFrame(Component comp) {
		try {
			SwingUtilities.getRoot(comp).setVisible(true);
		} catch (Exception eve) {
		}

	}

	public class CPanel extends ControlPanel {
		public CPanel() {
			super();
			setSize(getWidth(), getHeight() / 5);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switchUser();
			// System.out.println("restart");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

		}

	}

	public static void setRocket(RocketMath r) {
		RocketF.mRocket = r;

	}

	public class HTMLFile extends JScrollPane {
		JEditorPane editorPane;
		URL helpURL = null;

		public HTMLFile(String str) {
			super();
			System.out.println("new html file" + str);
			setPreferredSize(new Dimension(htmlpanel.getWidth(), htmlpanel.getHeight()));
			if (str.equals("st")){
				str = new String("/htmlPack/RocketExplanation.html");
			}
			else
			{
				str = str.substring(5);
				System.out.println(str);
			}
			editorPane = new JEditorPane();
			editorPane.setEditable(false);
			setUrl(str);
			//setUrl("/htmlPack/Rocket.html");

			// Put the editor pane in a scroll pane.

			this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.setPreferredSize(new Dimension(250, 145));
			this.setMinimumSize(new Dimension(10, 10));
			this.setViewportView(editorPane);

			editorPane.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent hle) {
					if (HyperlinkEvent.EventType.ACTIVATED.equals(hle
							.getEventType())) {
						System.out.println(hle.getURL().toString());
						
						htmlpanel.remove(html);
						htmlpanel.repaint();
						//sleep(1000);
						html = new HTMLFile(hle.getURL().toString());
						
						htmlpanel.add(html);
						htmlpanel.repaint();
						htmlpanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
						html.setPreferredSize(new Dimension(getWidth(), getHeight()));
						switchTo(htmlpanel);
						html.setVisible(true);
					}
				}
			});
		}

		public void setUrl(String str) {
			helpURL = HTMLFile.class.getResource(str);
			// URL("RocketExplanation.html");
			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException e) {
					System.err.println("Attempted to read a bad URL: "
							+ helpURL);
				}
			} else {
				System.err
						.println("Couldn't find file: TextSamplerDemoHelp.html");
			}
			repaint();
		}
		
		public void setUrl(URL url)
		{
			helpURL = url;
			// URL("RocketExplanation.html");
			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException e) {
					System.err.println("Attempted to read a bad URL: "
							+ helpURL);
				}
			} else {
				System.err
						.println("Couldn't find file: TextSamplerDemoHelp.html");
			}
			this.setViewportView(editorPane);
			repaint();
		}
	}

	public class MenuP extends RMenu {
		public MenuP() {
			super();

		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("Change User")) {
				switchUser();
			} else if (e.getActionCommand().equals("Launch Mode")) {
				mInit();
			} else if (e.getActionCommand().equals("Save"))

			{
				//
				// RocketMath r = mpanel.sliderp.spanel.rocket2;
				// if (r != null)
				// {
				// SaveWindow s = new SaveWindow(r, user); // FIX
				// } // from
				// // test1
				// // to
				// // acctually
				// // collect
				// // input

			} else if (e.getActionCommand().equals("Load")) {
				LoadWindow s = new LoadWindow(user);
			}
		}
	}
}