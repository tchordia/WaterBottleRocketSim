package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * 
 * @author Sahil
 *
 */
public class AngularLaunch extends RocketMath {

	double angle;
	double accx;
	double accy;
	static double thrust1;
	double drag1;
	double vx;
	double oldvx=0;
	double vy;
	double oldvy=0;
	double x1;
	double oldx = 0;
	double y1;
	double oldy = 0;

	public AngularLaunch(double m0, double mW, double vB, double p0, double cD,
			double rBot, double rNoz, double angle) {
		 super(m0, mW, vB, p0, cD, rBot, rNoz);
		drag1 = this.drag();
		this.angle = angle*Math.PI/180;
		step*=3;
	}
	
	public AngularLaunch()
	{
		super();
		this.angle = Math.PI/2;
	}

	// methods to be used
	// drag, thrust1
	// since each step is 1 second i didn't bother putting that in
	public void calculateXAcc() {

		thrust1 = this.thrust;

//		if (vx>0)
			drag1 = this.drag();
//		else
//			drag1 = 0;
		accx = (thrust1  + drag1)
				* Math.cos(angle )
				/ this.m;
	}

	public void calculateYAcc() {
		thrust1 = this.thrust;
		drag1 = this.drag();
		accy = (thrust1 * Math.sin(angle ) + drag1
				* Math.sin(angle ) - this.m * 9.81)
				/ this.m;
	}

	public void calculateVx() {
		vx = oldvx + accx*step;
		oldvx = vx;
	}

	public void calculateVy() {
		vy = oldvy + accy*step;
		oldvy = vy;
	}

	public void newFlightAngle() {
		angle = Math.atan(vy / vx) ;
	}

	public void xpos() {
		x1 = oldx+vx*step;
		oldx = x1;
	}

	public void ypos() {
		y1 = oldy+vy*step;
		oldy = y1;
	}
	public double drag()
	{
		double drag = -.5 * cD * pA * Math.PI * rBot * rBot * (oldvx*oldvx+Math.abs(oldvy)*oldvy);
//		System.out.println("    " + -.5 * cD * pA * Math.PI * rBot * rBot );
//		System.out.println("Drag" + drag);
	return drag;
	}

	public void doStepThrust()
	{
		super.doStep();
		this.calculateXAcc();
		this.calculateYAcc();
		this.calculateVx();
		this.calculateVy();
		newFlightAngle();
		this.xpos();
		this.ypos();
		//System.out.println(t+" Cartesian Coordinates: ("+x1+", "+y1+") " + "Angle: "+ angle*180/Math.PI);
	}
	// when thrust1 = 0
	// make 2d motion based on velocity
	// cartesian coordinates just for kicks
//	public static void main(String args[]) {
//		AngularLaunch rocket = new AngularLaunch(0.76, 0.66, 2, 253312.5, 1, .05, .01,30);
//		
//		// (double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz)
//		JFrame frm = new JFrame();
//		BouncingBall ball = new BouncingBall();
//		ball.setSize(1000, 500);
//		frm.add(ball, BorderLayout.CENTER);
//		frm.setSize(new Dimension(1000, 500));
//
//		frm.setVisible(true);
//		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		rocket.doStepThrust();
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////			rocket.doStepThrust();
//			ball.update(rocket);
//
//			//RocketMath.printStats(rocket);
//
//		System.out.println("y1:"+rocket.y1);
//	//System.exit(0);
//	}
}