package src.GUIpack;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;



/**
 * 
 * @author Cameron Yang
 * 
**/

public class CreateRocket extends JPanel
{
	
	/** @param Ln	=	length of nose
	  * @param D	=	diameter at base of nose
	  * @param Df	=	diameter at front of transition
	  * @param Dr	=	diameter at rear of transition
	  * @param Lt	=	length of transition
	  * @param Xp	=	distance from tip of nose to front of transition
	  * @param Cr	=	fin root chord
	  * @param Ct	=	fin tip chord
	  * @param S	=	fin semispan
	  * @param Lf	=	length of fin mid-chord line
	  * @param R	=	radius of body at aft end
	  * @param Xr	=	distance between fin root leading edge and fin tip leading edge parallel to body
	  * @param Xb	=	distance from nose tip to fin root chord leading edge
	  * @param N	=	number of fins
	*/
	
	double Ln;
	double D;
	double Df;
	double Dr;
	double Lt;
	double Xp;
	double Cr;
	double Ct;
	double S;
	double Lf;
	double R;
	double Xr;
	double Xb;
	double N;
	
	double 	totalBodyLength;
		
	Group entireRocket = new Group();
	
	CreateRocket(double noseLength,
				 double noseBaseDiameter,
				 double diameterAtFrontTransition,
				 double diameterAtRearTransition,
				 double lengthOfTransition,
				 double distanceFromNoseToTransition,
				 double finRootChord,
				 double finTipChord,
				 double finSemispan,
				 double lengthOfFinMidChordLine,
				 double radiusOfBodyAtAftEnd,
				 double edgeAndFinTipLeadingEdgeParallelToBody,
				 double distanceFromNoseTipToFinRoot,
				 double numberOfFins
				 )
	{
		 Ln=noseLength;
		 D=noseBaseDiameter;
		 Df=diameterAtFrontTransition;
		 Dr=diameterAtRearTransition;
		 Lt=lengthOfTransition;
		 Xp=distanceFromNoseToTransition;
		 Cr=finRootChord;
		 Ct=finTipChord;
		 S=finSemispan;
		 Lf=lengthOfFinMidChordLine;
		 R=radiusOfBodyAtAftEnd;
		 Xr=edgeAndFinTipLeadingEdgeParallelToBody;
		 Xb=distanceFromNoseTipToFinRoot;
		 N=numberOfFins;
	}
	
	CreateRocket( int scale)
	{
		 Ln=scale * 10/2;
		 D=scale * 10/2;
		 Df=scale * 7/2;
		 Dr=scale * 5/2;
		 Lt=scale * 20/2;
		 Xp=scale * 50/2;
		 Cr=scale * 20/2;
		 Ct=scale * 10/2;
		 S=scale * 10/2;
		 Lf=scale * 10/2;
		 R=scale * 10/2;
		 Xr=scale * 20/2;
		 Xb=scale * 100/2;
		 N=2;
		 
			totalBodyLength = Xb + Cr;

			final JFXPanel fxPanel = new JFXPanel();

			fxPanel.setSize(600, 600);

			setLayout(new BorderLayout());

			add(fxPanel, BorderLayout.CENTER);

			Platform.runLater(new Runnable() {
				public void run() {
					Scene scene = createScene();
					fxPanel.setScene(scene);
				}
			});
	}
	CreateRocket()
	{
		this(1);
	}
	
	
	public Group getRocket()
	{
		return entireRocket;
	}
	
	private Scene createScene()
	{
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.WHITE);
		
		double x=getWidth()/2;
		double y=getHeight()/2;
		
		double aftBodyLeftSide = x-Dr;
		double aftBodyRightSide = x+Dr;
		double aftBodyBottom = y+totalBodyLength/2;
		double aftBodyTop = y-totalBodyLength/2+Xp+Lt;
		
		double transitionTopLeft=x-Df;
		double transitionTopRight=x+Df;
		double transitionHeight=aftBodyTop-Lt;
		double topTransitionHeight=transitionHeight-Xp+Ln;
		
		double finHeight = aftBodyBottom-Cr;
		double finTipTop = finHeight+Xr;
		double finTipBottom = finTipTop+Ct;
		double leftFinTip = x-(Dr+S);
		double rightFinTip = x+(Dr+S);
		
		
		Line centerLine=new Line();
		
		centerLine.setStartX(x);
		centerLine.setStartY(y-totalBodyLength/2);
		centerLine.setEndX(x);
		centerLine.setEndY(y+totalBodyLength/2);
		centerLine.setFill(Color.BLUE);
		
		Group aftEnd = new Group();
		Group coneToBodyTransition = new Group();
		Group cone = new Group();
		Group leftFin = new Group();
		Group rightFin = new Group();
		
		Line aftRightSide = new Line(aftBodyRightSide,aftBodyTop,aftBodyRightSide,aftBodyBottom);
		Line aftLeftSide = new Line(aftBodyLeftSide,aftBodyTop,aftBodyLeftSide,aftBodyBottom);
		Line aftBottom = new Line(aftBodyRightSide,aftBodyBottom,aftBodyLeftSide,aftBodyBottom);
		
		aftEnd.getChildren().addAll(aftRightSide, aftLeftSide, aftBottom);
		
		Line bottomTransitionLeft = new Line(aftBodyLeftSide, aftBodyTop, transitionTopLeft, transitionHeight);
		Line bottomTransitionRight = new Line(aftBodyRightSide, aftBodyTop, transitionTopRight, transitionHeight);
		Line topTransitionLeft = new Line(transitionTopLeft, transitionHeight, transitionTopLeft, topTransitionHeight);
		Line topTransitionRight = new Line(transitionTopRight, transitionHeight, transitionTopRight, topTransitionHeight);
		
		coneToBodyTransition.getChildren().addAll(bottomTransitionLeft, bottomTransitionRight, topTransitionLeft, topTransitionRight);
		
		Line coneLeft = new Line(transitionTopLeft, topTransitionHeight, x, y-totalBodyLength/2);
		Line coneRight = new Line(transitionTopRight, topTransitionHeight, x, y-totalBodyLength/2);	
		
		cone.getChildren().addAll(coneLeft, coneRight);
		
		Line leftFinTop = new Line(aftBodyLeftSide, finHeight, leftFinTip, finTipTop);
		Line leftFinLeft = new Line(leftFinTip, finTipTop, leftFinTip, finTipBottom);
		Line leftFinBottom = new Line(leftFinTip, finTipBottom, aftBodyLeftSide,aftBodyBottom);
		
		leftFin.getChildren().addAll(leftFinTop, leftFinLeft, leftFinBottom);
		
		Line rightFinTop = new Line(aftBodyRightSide, finHeight, rightFinTip, finTipTop);
		Line rightFinRight = new Line(rightFinTip, finTipTop, rightFinTip, finTipBottom);
		Line rightFinBottom = new Line(rightFinTip, finTipBottom, aftBodyRightSide,aftBodyBottom);
		
		rightFin.getChildren().addAll(rightFinTop, rightFinRight, rightFinBottom);
		
		entireRocket.getChildren().addAll(aftEnd, cone, coneToBodyTransition, leftFin, rightFin);
		
//		entireRocket.setTranslateX(200);
//		entireRocket.setTranslateY(200);
		
		root.getChildren().add(entireRocket);
		
		return scene;
	}
	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		CreateRocket rocket = new CreateRocket(6);
		frame.setSize(600,600);
		rocket.entireRocket.setTranslateX(frame.getWidth()/2);
		rocket.entireRocket.setTranslateY(frame.getHeight()/2);
		frame.add(rocket);
		frame.setSize(600,600);
		frame.setTitle("Rocket Creation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
