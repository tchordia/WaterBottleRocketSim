package src.GUIpack;

import java.awt.BorderLayout;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * 
 * @author Cameron Yang
 * 
**/

public class CreateRocketFallEX extends JPanel
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
	
	int i=1;
	
	double 	totalBodyLength;
	
	public double x;
	public double y;
	
	public double aftBodyLeftSide;
	public double aftBodyRightSide;
	public double aftBodyBottom;
	public double aftBodyTop;
	
	public double transitionTopLeft;
	public double transitionTopRight;
	public double transitionHeight;
	public double topTransitionHeight;
	
	public double finHeight;
	public double finTipTop;
	public double finTipBottom;
	public double leftFinTip;
	public double rightFinTip;
	
	double fallingAngle;
	
	boolean secondPointFalling=false;
	
	double contactAngle=0;
	
	double angleAtFall=0;
	
	boolean running=true;
	
	boolean stool;
		
	Group entireRocket = new Group();
	
	Circle point;
	
	AnimationTimer timer;
	
	Line parentBoundingLineLeft = new Line();
	Line parentBoundingLineRight = new Line();
	Line parentBoundingLineTop = new Line();
	Line parentBoundingLineBottom = new Line();
	
	Group whoops = new Group();
	
	CreateRocketFallEX(double noseLength,
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
	
	CreateRocketFallEX()
	{
		 Ln=10*5;
		 D=10*2;
		 Df=7*2;
		 Dr=5*2;
		 Lt=20*2;
		 Xp=50*2;
		 Cr=20*2;
		 Ct=10*2;
		 S=10*2;
		 Lf=10*2;
		 R=10*2;
		 Xr=20*2;
		 Xb=100*2;
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
					timer.start();
				}
			});
	}
	
	
	public Group getRocket()
	{
		return entireRocket;
	}
	
	public void update()
	{
		
	}
	
	private Scene createScene()
	{
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.WHITE);
		
		x=getWidth()/2;
		y=getHeight()/2;
		
		aftBodyLeftSide = x-Dr;
		aftBodyRightSide = x+Dr;
		aftBodyBottom = y+totalBodyLength/2;
		aftBodyTop = y-totalBodyLength/2+Xp+Lt;
		
		transitionTopLeft=x-Df;
		transitionTopRight=x+Df;
		transitionHeight=aftBodyTop-Lt;
		topTransitionHeight=transitionHeight-Xp+Ln;
		
		finHeight = aftBodyBottom-Cr;
		finTipTop = finHeight+Xr;
		finTipBottom = finTipTop+Ct;
		leftFinTip = x-(Dr+S);
		rightFinTip = x+(Dr+S);


		Line centerLine=new Line();

		
		centerLine.setStartX(x);
		centerLine.setStartY(y-totalBodyLength/2);
		centerLine.setEndX(x);
		centerLine.setEndY(y+totalBodyLength/2);
		centerLine.setFill(Color.BLUE);
		
		Line aftRightSide = new Line(aftBodyRightSide,aftBodyTop,aftBodyRightSide,aftBodyBottom);
		Line aftLeftSide = new Line(aftBodyLeftSide,aftBodyTop,aftBodyLeftSide,aftBodyBottom);
		Line aftBottom = new Line(aftBodyRightSide,aftBodyBottom,aftBodyLeftSide,aftBodyBottom);
		
		entireRocket.getChildren().addAll(aftRightSide, aftLeftSide, aftBottom);//0-2
		
		Line bottomTransitionLeft = new Line(aftBodyLeftSide, aftBodyTop, transitionTopLeft, transitionHeight);
		Line bottomTransitionRight = new Line(aftBodyRightSide, aftBodyTop, transitionTopRight, transitionHeight);
		Line topTransitionLeft = new Line(transitionTopLeft, transitionHeight, transitionTopLeft, topTransitionHeight);
		Line topTransitionRight = new Line(transitionTopRight, transitionHeight, transitionTopRight, topTransitionHeight);
		
		entireRocket.getChildren().addAll(bottomTransitionLeft, bottomTransitionRight, topTransitionLeft, topTransitionRight);//3-6
		
		Line coneLeft = new Line(transitionTopLeft, topTransitionHeight, x, topTransitionHeight-Ln);
		Line coneRight = new Line(transitionTopRight, topTransitionHeight, x, topTransitionHeight-Ln);	
		
		entireRocket.getChildren().addAll(coneLeft, coneRight);//7-8
		
		Line leftFinTop = new Line(aftBodyLeftSide, finHeight, leftFinTip, finTipTop);
		Line leftFinLeft = new Line(leftFinTip, finTipTop, leftFinTip, finTipBottom);
		Line leftFinBottom = new Line(leftFinTip, finTipBottom, aftBodyLeftSide,aftBodyBottom);
		
		entireRocket.getChildren().addAll(leftFinTop, leftFinLeft, leftFinBottom);//9-11 666
		
		Line rightFinTop = new Line(aftBodyRightSide, finHeight, rightFinTip, finTipTop);
		Line rightFinRight = new Line(rightFinTip, finTipTop, rightFinTip, finTipBottom);
		Line rightFinBottom = new Line(rightFinTip, finTipBottom, aftBodyRightSide,aftBodyBottom);
		
		entireRocket.getChildren().addAll(rightFinTop, rightFinRight, rightFinBottom);//12-14
						
		entireRocket.setLayoutX(300);
		entireRocket.setLayoutY(100);		
		
		System.out.println(entireRocket.getRotationAxis());		
		
		entireRocket.setRotate(180);
		
		final Line parentBoundingLine = new Line();
		parentBoundingLine.setStartY(entireRocket.getBoundsInParent().getMaxY());
		parentBoundingLine.setEndY(entireRocket.getBoundsInParent().getMaxY());
		parentBoundingLine.setStartX(10000000);
		parentBoundingLine.setEndX(-10000000);
		
		point=new Circle(x-Math.sqrt(Ln*Ln+Df*Df), y-totalBodyLength/2, 3);
		
		Button btn = new Button();
		btn.setLayoutX(200);
		btn.setLayoutX(200);
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				running=!running;
			}
		}
				);
		
		whoops.getChildren().addAll(parentBoundingLineLeft, parentBoundingLineRight, parentBoundingLineBottom, parentBoundingLineTop);
		
		root.getChildren().addAll(entireRocket, parentBoundingLine, point, btn, whoops);
		
		double phi= Math.atan((Df)/Ln)*180/Math.PI;
		fallingAngle = 90-(180-entireRocket.getRotate());
		fallingAngle -= phi;
		
		boolean Tanmay=true;
		int z=0;
		
		while(Tanmay)
		{
			try
			{
			
			System.out.println("Value at " + z + ": " + entireRocket.getChildren().get(z));
			}
			
			catch(IndexOutOfBoundsException e)
			{
				Tanmay = false;
			}
			z++;
		}
		
		
		System.out.println("Falling Angle: " + fallingAngle);
				
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) 
			{
				if(running == true)
				{
				i++;
				if(!secondPointFalling)
				{
					entireRocket.getTransforms().clear();
					entireRocket.getTransforms().add(new Rotate(-i, x, y-totalBodyLength/2));
				}
				else
				{
					if(angleAtFall==0)
					{
					angleAtFall = i;
					System.out.println(angleAtFall);
					}
					else
					{
					entireRocket.setTranslateY(50);
					entireRocket.setTranslateX(-40);
					entireRocket.getTransforms().clear();
					entireRocket.getTransforms().add(new Rotate(-i, transitionTopRight, topTransitionHeight));
					stool = parentBoundingLine.intersects(entireRocket.getChildren().get(13).localToScene(entireRocket.getChildren().get(13).getBoundsInLocal()));
					System.out.println("contact: " + stool);
					if(stool)
					{
						this.stop();
					}
					}
				}
				if(i==(int)fallingAngle)
				{
					System.out.println(i);
					entireRocket.getTransforms().clear();
					entireRocket.getTransforms().add(new Rotate(-fallingAngle, x, y-totalBodyLength/2));
					secondPointFalling = true;
				}
			}
			}
		};
		
		return scene;
	}
	
	public void updateBounds(Bounds local)
	{
		
		double minX;
		double minY;
		double maxX;
		double maxY;
		
		minX=local.getMinX();
		minY=local.getMinY();
		maxX=local.getMaxX();
		maxY=local.getMaxY();
		
		parentBoundingLineLeft.setStartX(minX);
		parentBoundingLineLeft.setStartY(minY);
		parentBoundingLineLeft.setEndX(minX);
		parentBoundingLineLeft.setEndY(maxY);
		parentBoundingLineLeft.setStroke(Color.RED);

		parentBoundingLineRight.setStartX(maxX);
		parentBoundingLineRight.setStartY(maxY);
		parentBoundingLineRight.setEndX(maxX);
		parentBoundingLineRight.setEndY(minY);
		parentBoundingLineRight.setStroke(Color.RED);

		parentBoundingLineTop.setStartX(maxX);
		parentBoundingLineTop.setStartY(minY);
		parentBoundingLineTop.setEndX(minX);
		parentBoundingLineTop.setEndY(minY);
		parentBoundingLineTop.setStroke(Color.RED);

		parentBoundingLineBottom.setStartX(maxX);
		parentBoundingLineBottom.setStartY(maxY);
		parentBoundingLineBottom.setEndX(minX);
		parentBoundingLineBottom.setEndY(maxY);
		parentBoundingLineBottom.setStroke(Color.RED);
	}
	
	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		CreateRocketFallEX rocket = new CreateRocketFallEX();
		frame.add(rocket);
		frame.setSize(600,600);
		frame.setTitle("Rocket Creation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
