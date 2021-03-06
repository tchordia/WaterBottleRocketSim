package src.GUIpack;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;

import javax.swing.Painter;

/**
 * 
 * @author Cameron Yang, Tanmay Chordia
 * 
 * This class creates the Panel that contains the sliders to be used for the simulation.
 *
 */

public class RocketSliders extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static MinimalSlider Ln;  
	public static MinimalSlider D;
	public static MinimalSlider Df;
	public static MinimalSlider Dr;
	public static MinimalSlider Lt;
	public static MinimalSlider Xp;
	public static MinimalSlider Cr;
	public static MinimalSlider Ct;
	public static MinimalSlider S;  
	public static MinimalSlider Lf;
	public static MinimalSlider R;
	public static MinimalSlider Xr;
	public static MinimalSlider Xb;
	public static MinimalSlider Nz;
	
    UIDefaults sliderDefaults = new UIDefaults();
    
    private BufferedImage sliderThumb;

	public RocketSliders() {

        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
		JPanel sliders = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		Ln = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 100, 50);

		Ln.setMajorTickSpacing(20);

		Ln.setMinorTickSpacing(1);

		Ln.setPaintLabels(true);
		
		Ln.addChangeListener(this);
		
		JPanel noseLengthPanel = new JPanel();
		noseLengthPanel.setBackground(Color.white);
		noseLengthPanel.add(Ln);
		noseLengthPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Length of Nose"));
		

		D = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 50, 20);

		D.setMajorTickSpacing(10);

		D.setMinorTickSpacing(1);

		D.setPaintLabels(true);

		D.addChangeListener(this);

		JPanel noseBaseDiameterPanel = new JPanel();
		noseBaseDiameterPanel.setBackground(Color.white);
		noseBaseDiameterPanel.add(D);
		noseBaseDiameterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter of Nose Base"));


		Df = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 50, 14); // divide value by 1000

		Df.setMajorTickSpacing(10);

		Df.setPaintLabels(true);

		Df.addChangeListener(this);

		JPanel DfPanel = new JPanel();
		DfPanel.setBackground(Color.white);
		DfPanel.add(Df);
		DfPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter at Front Transition"));


		Dr = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 50, 10);

		Dr.setMajorTickSpacing(10);

		Dr.setMinorTickSpacing(1);

		Dr.setPaintLabels(true);

		Dr.addChangeListener(this);

		JPanel DrPanel = new JPanel();
		DrPanel.setBackground(Color.white);
		DrPanel.add(Dr);
		DrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter at Rear Transition"));
		
		Lt = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 100, 40);

		Lt.setMajorTickSpacing(20);

		Lt.setMinorTickSpacing(1);

		Lt.setPaintLabels(true);

		Lt.addChangeListener(this);

		JPanel LtPanel = new JPanel();
		LtPanel.setBackground(Color.white);
		LtPanel.add(Lt);
		LtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Length of Transition"));


		Xp = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 200, 100);

		Xp.setMajorTickSpacing(40);

		Xp.setMinorTickSpacing(1);

		Xp.setPaintLabels(true);

		Xp.addChangeListener(this);

		JPanel XpoefficientPanel = new JPanel();

		XpoefficientPanel.setBackground(Color.white);
		XpoefficientPanel.add(Xp);
		XpoefficientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Distance from Nose to Transition"));


		Cr = new MinimalSlider(MinimalSlider.HORIZONTAL, 0, 50, 40);// divide by 100
		
		Cr.setMajorTickSpacing(10);

		Cr.setMinorTickSpacing(1);

		Cr.setPaintLabels(true);
		
		Cr.addChangeListener(this);
		
		JPanel CrPanel = new JPanel();
		CrPanel.setBackground(Color.white);
		CrPanel.add(Cr);
		CrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin root chord"));
		
		
		Ct = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 50, 20); // divide by
																			  // 100
		Ct.setMajorTickSpacing(10);

		Ct.setMinorTickSpacing(1);

		Ct.setPaintLabels(true);

		Ct.addChangeListener(this);
		
		JPanel CtPanel = new JPanel();
		CtPanel.setBackground(Color.white);
		CtPanel.add(Ct);
		CtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin Tip Chord"));
		
		
		S = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 50, 20);
		
		S.setMajorTickSpacing(10);

		S.setMinorTickSpacing(1);

		S.setPaintLabels(true);

		S.addChangeListener(this);
		
		JPanel SPanel = new JPanel();
		SPanel.setBackground(Color.white);
		SPanel.add(S);
		SPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin Semi Span"));
		
		
		Lf = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 50, 20);
		
		Lf.setMajorTickSpacing(10);

		Lf.setMinorTickSpacing(1);

		Lf.setPaintLabels(true);

		Lf.addChangeListener(this);
		
		JPanel LfPanel = new JPanel();
		LfPanel.setBackground(Color.white);
		LfPanel.add(Lf);
		LfPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Length of Fin Mid-Chord"));
		
		R = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 50, 20);
		
		R.setMajorTickSpacing(10);

		R.setMinorTickSpacing(1);

		R.setPaintLabels(true);

		R.addChangeListener(this);
		
		JPanel RPanel = new JPanel();
		RPanel.setBackground(Color.white);
		RPanel.add(R);
		RPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Radius of Body at Aft End"));
		
		Xr = new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 50, 40);
		
		Xr.setMajorTickSpacing(10);

		Xr.setMinorTickSpacing(1);

		Xr.setPaintLabels(true);

		Xr.addChangeListener(this);
		
		JPanel XrPanel = new JPanel();
		XrPanel.setBackground(Color.white);
		XrPanel.add(Xr);
		XrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Edge and Fin Tip Leading Edge"));
		
		Xb= new MinimalSlider (MinimalSlider.HORIZONTAL, 0, 300, 200);
		
		Xb.setMajorTickSpacing(60);

		Xb.setMinorTickSpacing(1);

		Xb.setPaintLabels(true);

		Xb.addChangeListener(this);
		
		JPanel XbPanel = new JPanel();
		XbPanel.setBackground(Color.white);
		XbPanel.add(Xb);
		XbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Distance from Nose Tip to Fin Root"));
		
		Nz = new MinimalSlider (MinimalSlider.HORIZONTAL, 1, 10, 1);
		
		Nz.setMajorTickSpacing(1);

		Nz.setMinorTickSpacing(1);

		Nz.setPaintLabels(true);

		Nz.addChangeListener(this);
		
		JPanel NPanel = new JPanel();
		NPanel.setBackground(Color.white);
		NPanel.add(Nz);
		NPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "nozzleRadius"));
		
		GroupLayout sliderLayout = new GroupLayout(sliders);
		
		sliders.setLayout(sliderLayout);
		
		sliderLayout.setAutoCreateContainerGaps(true);
		sliderLayout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup horizontalLayout = sliderLayout.createSequentialGroup();
		GroupLayout.SequentialGroup verticalLayout = sliderLayout.createSequentialGroup();
		
		horizontalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(noseLengthPanel)
				.addComponent(DrPanel)
				.addComponent(CrPanel)
				.addComponent(CtPanel)
				.addComponent(LfPanel)
				.addComponent(XrPanel)
				.addComponent(NPanel)
				);

		horizontalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(noseBaseDiameterPanel)
				.addComponent(XpoefficientPanel)
				.addComponent(DfPanel)
				.addComponent(SPanel)
				.addComponent(RPanel)
				.addComponent(XbPanel)
				.addComponent(LtPanel)
				);

		sliderLayout.setHorizontalGroup(horizontalLayout);
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(noseLengthPanel).addComponent(noseBaseDiameterPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(DrPanel).addComponent(XpoefficientPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(DfPanel).addComponent(CrPanel));

		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(SPanel).addComponent(CtPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(LfPanel).addComponent(RPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(XrPanel).addComponent(XbPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(NPanel).addComponent(LtPanel));
		
		sliderLayout.setVerticalGroup(verticalLayout);
		sliders.setBackground(Color.white);
		add(sliders);
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Button Panel Example");
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new RocketSliders(), BorderLayout.PAGE_START);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Ln: "
				+ (Ln.getValue() / (100 + 0.0)));
		System.out.println("D: " + D.getValue() / (100 + 0.0));
		System.out.println("Df: " + Df.getValue()
				/ (1000 + 0.0));
		System.out.println("Dr: " + Dr.getValue()*10000);
		System.out.println("Xp: " + Xp.getValue());
		System.out.println("Cr: " + Cr.getValue()
				/ (100 + 0.0));
		System.out.println("Ct: " + Ct.getValue()
				/ (100 + 0.0));

	}

}