package mathPack;

import java.io.Serializable;


/**
 * @author Sahil Sancheti
 * 
 * Allows for two dimensional movement with rocket math, allowing a launch at an angle
 *
 */
public class AngularLaunch extends RocketMath implements Serializable {

	public double angle;
	double accx;
	double accy;
	static double thrust1;
	double drag1;
	double vx;
	double oldvx=0;
	double vy;
	double oldvy=0;
	public double x1;
	double oldx = 0;
	public double y1;
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
		return drag;
	}

	/**
	 * The equivalent of doStep from RocketMath, does a step ove movement
	 */
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
	}
}