package src.GUIpack;

/**
 * 
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author Tanmay
 * @date Mar 2, 2014
 * @version 1
 */
public class GraphPanel extends JPanel {

	public RocketMath rocket;
	public RocketMath orocket;
	int sHeight = 50; // apparent height of applet
	int sWidth = 5; // apparent width of applet
	int numMarks = 10; // Number of marks, currently does noth
	int ratio = 2 * sHeight / (numMarks);

	boolean changedCall = true;

	/**
	 * 
	 */
	public GraphPanel(RocketMath rocketa) {

		orocket = rocketa;
		rocket = rocketa;
		
	}

	public void updaterr(RocketMath rocketa) {
		orocket = rocketa;
		rocket = rocketa;
		changedCall = true;
		repaint();
	}

	public void drawAxis(Graphics gr, int width, int height) {
		/*
		 * draw axis
		 */
		gr.setColor(Color.black);

		gr.drawLine(width / 2, 0, width / 2, height);
		gr.drawLine(0, height / 2, width, height / 2);

	
		for (int i = 1; i < 8; i++) {
			gr.drawLine(i * width / 8, height / 2 - 5, i * width / 8,
					height / 2 + 5);
			gr.drawString(Double.toString((sWidth + 0.0) * i / 8), i * width
					/ 8, height / 2 - 5);
		}

		for (int i = 1; i <= 8; i++) {
			gr.drawLine(width / 2 - 5, i * height / 8, width / 2 + 5, i
					* height / 8);
			gr.drawString(Double.toString((sHeight + 0.0) * (4 - i) / 4),
					width / 2 - 5, i * height / 8);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 * 
	 * graphs the path of the rocket
	 */
	@Override
	public void paint(Graphics gr) {

		if (!changedCall) {
		rocket = new RocketMath();
		}
			int width = getWidth();
			int height = getHeight();
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, width, height);
			
			gr.setColor(Color.red);
			
			gr.drawString("---Velocity", (width *8) / 9 - 20 , (height * 1) / 9);
			gr.setColor(Color.blue);
			gr.drawString("---Position", (width *8) / 9  - 20, (height * 1) / 9 + 20);
			gr.setColor(Color.black);
			drawAxis(gr, width, height);
			// rocket = orocket.copy();
			// rocket = new RocketMath();
			double inc = rocket.step;
			double y2 = 10;
			double nexty2 = 10;
			gr.setColor(Color.blue);

			/*
			 * Draws the position graph
			 */
			for (double x = 0.0; x <= sWidth; x = x + inc) {

				/*
				 * sets the y value to the height, and the new y value to the
				 * next height, then connects the dots
				 */

				double y = rocket.h;
				y2 = rocket.v;
				double nextx = x + inc;
				rocket.doStep();
				double nexty = rocket.h;
				nexty2 = rocket.v;

				// y = 10;
				// nexty = 10;
				// if (x == 15)
				// {
				// y = 20;
				//
				// }
				// for testgin purposes
				gr.setColor(Color.blue);
				int startX = (int) (x * width / (sWidth));
				int startY = (int) (-y * height / (2 * sHeight) + height / 2.0);
				int endX = (int) (nextx * width / (sWidth));
				int endY = (int) (-nexty * height / (2 * sHeight) + height / 2.0);
				// connect the dots

				gr.drawLine(startX, startY, endX, endY);

				gr.setColor(Color.red);
				
				startX = (int) (x * width / (sWidth));
				startY = (int) (-y2 * height / (2 * sHeight) + height / 2.0);
				endX = (int) (nextx * width / (sWidth));
				endY = (int) (-nexty2 * height / (2 * sHeight) + height / 2.0);

				gr.drawLine(startX, startY, endX, endY);
				// draw the velocity graph
				changedCall = false;
			
		}

	}

	// public void paint(Graphics gr)
	// {
	// Circle circ = new Circle(50,60,(int)Math.round(rocket.rNoz * 100));
	// circ.draw(gr);
	//
	// }
}
