package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javafx.scene.input.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;

public class RocketFAnimated extends JFrame  {
	
	public SPanel spanel = new SPanel();
	public ProjectileBall apanel;
	AngularLaunch rocket2 =new AngularLaunch(
			  spanel.massRocket.getValue()/(10 + 0.0),
			  spanel.massWater.getValue()/(10 + 0.0),
			  spanel.volumeBottle.getValue()/(10000 + 0.0),
			  spanel.airPressure.getValue()*10000,
			  spanel.dragC.getValue(),
			  spanel.bottleRadius.getValue()/(100 + 0.0),
			  spanel.nozzleRadius.getValue()/(100 + 0.0),
//			  Math.asin(ProjectileBall.sineTheta)
			  Math.PI/4
			  );
	private static final long serialVersionUID = 1L;
	
	public RocketFAnimated ()
    {

		super(); 

        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } 
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        
        setupAnimation();

        spanel.setBackground(Color.white);
        add(spanel);
        add(apanel);
        apanel.setVisible(true);

        setTitle("Button Panel Example");
        setSize(new Dimension(600, 600));
        setLayout(new GridLayout(0,2));
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000,1000));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	
	public void setupAnimation()
	{
        
        apanel = new ProjectileBall(300, 400)
        {
        	public void resetBall()
        	{
        		super.resetBall();
    			rocket2 = new AngularLaunch(
    					spanel.massRocket.getValue()/(10 + 0.0),
    					spanel.massWater.getValue()/(10 + 0.0),
    					spanel.volumeBottle.getValue()/(10000 + 0.0),
    					spanel.airPressure.getValue()*10000,
    					spanel.dragC.getValue(),
    					spanel.bottleRadius.getValue()/(100 + 0.0),
    					spanel.nozzleRadius.getValue()/(100 + 0.0),
    					Math.PI/4
    					);
        	}
        	public void angleAdjust(MouseEvent e)
        	{
        		super.angleAdjust(e);
    			rocket2 = new AngularLaunch(
    					spanel.massRocket.getValue()/(10 + 0.0),
    					spanel.massWater.getValue()/(10 + 0.0),
    					spanel.volumeBottle.getValue()/(10000 + 0.0),
    					spanel.airPressure.getValue()*10000,
    					spanel.dragC.getValue(),
    					spanel.bottleRadius.getValue()/(100 + 0.0),
    					spanel.nozzleRadius.getValue()/(100 + 0.0),
//    					Math.asin(ProjectileBall.sineTheta)
    					Math.PI/4
    							);
    			System.out.println(rocket2.angle);
        	}

        	public void mathClass(double t)
        	{
        		rocket2.t=t;
        		rocket2.doStepThrust();
//        		System.out.println()
        		ProjectileBall.y=rocket2.y1;
        		ProjectileBall.x=rocket2.x1;
        		rocket2.t=t+1;
        		System.out.print("Next step: ");
        		rocket2.doStepThrust();
        		if(Double.toString(rocket2.angle) == "NaN")
        		{
        			System.out.println("aw shit");
        		}
        		rocket2.t=t-1;
        	}
        };
	}

   public static void main(String[] args) 
   {
	   RocketFAnimated frame = new RocketFAnimated();
   }

   public class SPanel extends SliderPanel
   {
	   public SPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
	   }
	   @Override
	   public void stateChanged(ChangeEvent arg0)
	   {
		   	System.out.println("massRocket: " + (massRocket.getValue()/(10+0.0))) ;
			System.out.println("massWater: " + massWater.getValue()/(10+0.0)) ;
			System.out.println("volumeBottle: " + volumeBottle.getValue()/(10000+0.0)) ;
			System.out.println("airPressure: " + airPressure.getValue()*10000) ;
			System.out.println("dragC: " + dragC.getValue()) ;
			System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
			System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
			System.out.println("angle: " + rocket2.angle);
			rocket2 = new AngularLaunch(
					massRocket.getValue()/(10 + 0.0),
					massWater.getValue()/(10 + 0.0),
					volumeBottle.getValue()/(10000 + 0.0),
					airPressure.getValue()*10000,dragC.getValue(),
					bottleRadius.getValue()/(100 + 0.0),
					nozzleRadius.getValue()/(100 + 0.0),
//					Math.asin(ProjectileBall.sineTheta)
					Math.PI/4
					);
	   }
   }
}