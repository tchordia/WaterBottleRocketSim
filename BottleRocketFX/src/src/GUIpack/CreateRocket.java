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

import mathPack.Barrowman;



/**
 * 
 * @author Cameron J. Yang
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
	
	public double Ln;
	public double D;
	public double Df;
	public double Dr;
	public double Lt;
	public double Xp;
	public double Cr;
	public double Ct;
	public double S;
	public double Lf;
	public double R;
	public double Xr;
	public double Xb;
	public double nozzleRadius;
	
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
	Barrowman barrow; 
	Group entireRocket = new Group();
	
	Circle point;
	
	AnimationTimer timer;
	
	double youngJeans;
	
	
	double translatingX=1000;
	
	Line parentBoundingLineLeft = new Line();
	Line parentBoundingLineRight = new Line();
	Line parentBoundingLineTop = new Line();
	Line parentBoundingLineBottom = new Line();
	
	Group whoops = new Group();
	
	Line aftRightSide;
	Line aftLeftSide;
	Line aftBottom;
		
	Line bottomTransitionLeft;
	Line bottomTransitionRight;
	Line topTransitionLeft;
	Line topTransitionRight;
		
	Line coneLeft;
	Line coneRight;
		
	Line leftFinTop;
	Line leftFinLeft;
	Line leftFinBottom;
		
	Line rightFinTop;
	Line rightFinRight;
	Line rightFinBottom;
	
	Line nozleLeft;
	Line nozleRight;
	Line nozleBottom;
	
	
	
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
		 
		 
	}
	
	CreateRocket(double factor)
	{
		 Ln=10*5 * factor;
		 D=10*2 * factor;
		 Df=7*2 * factor;
		 Dr=5*2 * factor;
		 Lt=20*2 * factor;
		 Xp=50*2 * factor;
		 Cr=20*2 * factor;
		 Ct=10*2 * factor;
		 S=10*2 * factor;
		 Lf=10*2 * factor;
		 R=10*2 * factor;
		 Xr=20*2 * factor;
		 Xb=100*2 * factor;
		 N=2 * factor;
		 
			totalBodyLength = Xb + Cr;
			
			x=0;
			y=0;

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
			
			aftRightSide = new Line(aftBodyRightSide,aftBodyTop,aftBodyRightSide,aftBodyBottom);
			aftLeftSide = new Line(aftBodyLeftSide,aftBodyTop,aftBodyLeftSide,aftBodyBottom);
			aftBottom = new Line(aftBodyRightSide,aftBodyBottom,aftBodyLeftSide,aftBodyBottom);

			bottomTransitionLeft = new Line(aftBodyLeftSide, aftBodyTop, transitionTopLeft, transitionHeight);
			bottomTransitionRight = new Line(aftBodyRightSide, aftBodyTop, transitionTopRight, transitionHeight);
			topTransitionLeft = new Line(transitionTopLeft, transitionHeight, transitionTopLeft, topTransitionHeight);
			topTransitionRight = new Line(transitionTopRight, transitionHeight, transitionTopRight, topTransitionHeight);

			coneLeft = new Line(transitionTopLeft, topTransitionHeight, x, topTransitionHeight-Ln);
			coneRight = new Line(transitionTopRight, topTransitionHeight, x, topTransitionHeight-Ln);	

			leftFinTop = new Line(aftBodyLeftSide, finHeight, leftFinTip, finTipTop);
			leftFinLeft = new Line(leftFinTip, finTipTop, leftFinTip, finTipBottom);
			leftFinBottom = new Line(leftFinTip, finTipBottom, aftBodyLeftSide,aftBodyBottom);

			rightFinTop = new Line(aftBodyRightSide, finHeight, rightFinTip, finTipTop);
			rightFinRight = new Line(rightFinTip, finTipTop, rightFinTip, finTipBottom);
			rightFinBottom = new Line(rightFinTip, finTipBottom, aftBodyRightSide,aftBodyBottom);

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
			
			barrow = new Barrowman(this);
			barrow.volume();
	}
	CreateRocket()
	{
		this(1);
	}
	
	private Scene createScene()
	{
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.WHITE);
		
		x=0;
		y=0;

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


		//		Line centerLine=new Line();
		//
		//		centerLine.setStartX(x);
		//		centerLine.setStartY(y-totalBodyLength/2);
		//		centerLine.setEndX(x);
		//		centerLine.setEndY(y+totalBodyLength/2);
		//		centerLine.setStroke(Color.BLUE);

		aftRightSide = new Line(aftBodyRightSide,aftBodyTop,aftBodyRightSide,aftBodyBottom);
		aftLeftSide = new Line(aftBodyLeftSide,aftBodyTop,aftBodyLeftSide,aftBodyBottom);
		aftBottom = new Line(aftBodyRightSide,aftBodyBottom,aftBodyLeftSide,aftBodyBottom);

		root.getChildren().addAll(aftRightSide, aftLeftSide, aftBottom);//0-2

		bottomTransitionLeft = new Line(aftBodyLeftSide, aftBodyTop, transitionTopLeft, transitionHeight);
		bottomTransitionRight = new Line(aftBodyRightSide, aftBodyTop, transitionTopRight, transitionHeight);
		topTransitionLeft = new Line(transitionTopLeft, transitionHeight, transitionTopLeft, topTransitionHeight);
		topTransitionRight = new Line(transitionTopRight, transitionHeight, transitionTopRight, topTransitionHeight);

		root.getChildren().addAll(bottomTransitionLeft, bottomTransitionRight, topTransitionLeft, topTransitionRight);//3-6

		coneLeft = new Line(transitionTopLeft, topTransitionHeight, x, topTransitionHeight-Ln);
		coneRight = new Line(transitionTopRight, topTransitionHeight, x, topTransitionHeight-Ln);	

		root.getChildren().addAll(coneLeft, coneRight);//7-8

		leftFinTop = new Line(aftBodyLeftSide, finHeight, leftFinTip, finTipTop);
		leftFinLeft = new Line(leftFinTip, finTipTop, leftFinTip, finTipBottom);
		leftFinBottom = new Line(leftFinTip, finTipBottom, aftBodyLeftSide,aftBodyBottom);

		root.getChildren().addAll(leftFinTop, leftFinLeft, leftFinBottom);//9-11 666

		rightFinTop = new Line(aftBodyRightSide, finHeight, rightFinTip, finTipTop);
		rightFinRight = new Line(rightFinTip, finTipTop, rightFinTip, finTipBottom);
		rightFinBottom = new Line(rightFinTip, finTipBottom, aftBodyRightSide,aftBodyBottom);
		
		root.getChildren().addAll(rightFinTop, rightFinRight, rightFinBottom);//12-14
		
		nozleLeft = new Line(x+nozzleRadius);
		nozleRight = new Line();
		nozleBottom = new Line();
		
		root.getChildren().addAll(rightFinTop, rightFinRight, rightFinBottom);//12-14
		
		
		aftRightSide.setLayoutX(200);
		aftRightSide.setLayoutY(200);
		
		aftLeftSide.setLayoutX(200);
		aftLeftSide.setLayoutY(200);

		aftBottom.setLayoutX(200);
		aftBottom.setLayoutY(200);
			
		bottomTransitionLeft.setLayoutX(200);
		bottomTransitionLeft.setLayoutY(200);

		bottomTransitionRight.setLayoutX(200);
		bottomTransitionRight.setLayoutY(200);

		topTransitionLeft.setLayoutX(200);
		topTransitionLeft.setLayoutY(200);

		topTransitionRight.setLayoutX(200);
		topTransitionRight.setLayoutY(200);

		coneLeft.setLayoutX(200);
		coneLeft.setLayoutY(200);

		coneRight.setLayoutX(200);
		coneRight.setLayoutY(200);
			
		leftFinTop.setLayoutX(200);
		leftFinTop.setLayoutY(200);

		leftFinLeft.setLayoutX(200);
		leftFinLeft.setLayoutY(200);

		leftFinBottom.setLayoutX(200);
		leftFinBottom.setLayoutY(200);
		
		rightFinTop.setLayoutX(200);
		rightFinTop.setLayoutY(200);

		rightFinRight.setLayoutX(200);
		rightFinRight.setLayoutY(200);

		rightFinBottom.setLayoutX(200);
		rightFinBottom.setLayoutY(200);
		
		//!!!!!!!!!!!
		
//		entireRocket.setRotate(132);
		
//		translatingX=-5;
		
		youngJeans=entireRocket.getRotate();
		
		//!!!!!!!!!!!
		
		
		final Line parentBoundingLine = new Line();
		parentBoundingLine.setStartY(entireRocket.getBoundsInParent().getMaxY());
		parentBoundingLine.setEndY(entireRocket.getBoundsInParent().getMaxY());
		parentBoundingLine.setStartX(10000000);
		parentBoundingLine.setEndX(-10000000);

		point=new Circle();

		
		Button btn = new Button();
		btn.setLayoutX(200);
		btn.setLayoutX(200);
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				running=!running;
			}
		});
		
		whoops.getChildren().addAll(parentBoundingLine);
		
		root.getChildren().addAll(point, btn, whoops);
		
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
		
		/*
		 * 
		 * 225 degrees: -76, 45, 119.36
		 *
		 * 180 degrees: -40, 50, 74 degrees
		 * 
		 * 175 degrees: -38, 49 69 degrees
		 * 
		 * 160 degrees: -30, 48 64 degrees
		 * 
		 * 155 degrees: -28, 45
		 * 
		 * 150 degrees: -25, 43 degrees 75
		 * 
		 * 145 degrees: -14, 34
		 * 
		 * 140 degrees: -12, 30
		 *
		 * 135 degrees: -6, 25, 29.358 degrees
		 * 
		 * 130 degrees: -4, 24
		 * 
		 * 125 degrees: -2, 20
		 * 
		 * 120 degrees: 0, 15
		 * 
		 * 170 degrees: 
		 * 
		 */
				
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) 
			{
				
				updateRocket(
						 20*5+i,
						 20*2,
						 14*2,
						 5*2,
						 20*2,
						 50*2,
						 20*2,
						 10*2,
						 10*2,
						 10*2,
						 10*2,
						 20*2,
						 100*2,
						 2
						);
				
				i++;
				
				running = false;
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
					entireRocket.setTranslateY(-.0065*youngJeans*youngJeans+2.5642*youngJeans-199.66);
					System.out.println("Low Shirt Sujeeth says, 'y value at: " + entireRocket.getTranslateY()+"'");
					entireRocket.setTranslateX((.0013*youngJeans*youngJeans+.4077*youngJeans-70.184)*-1);
					
					if(translatingX!=1000)
					entireRocket.setTranslateX(translatingX);

					System.out.println("Big boobed Sujeeth says, 'x value at: " + entireRocket.getTranslateX()+"'");
					
					entireRocket.getTransforms().clear();
					Rotate rotate= new Rotate(-i, transitionTopRight, topTransitionHeight);
					point.setLayoutX(rotate.getAxis().getX());
					point.setLayoutY(rotate.getAxis().getY());
					entireRocket.getTransforms().add(rotate);
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

	public Group getRocket()
	{
		
		System.out.println("getting rocket");
		
		if(entireRocket.getChildren().isEmpty())
		{
		
		entireRocket.getChildren().addAll(aftRightSide, aftLeftSide, aftBottom);//0-2
		
		entireRocket.getChildren().addAll(bottomTransitionLeft, bottomTransitionRight, topTransitionLeft, topTransitionRight);//3-6
		
		entireRocket.getChildren().addAll(coneLeft, coneRight);//7-8
		
		entireRocket.getChildren().addAll(leftFinTop, leftFinLeft, leftFinBottom);//9-11 666
		
		entireRocket.getChildren().addAll(rightFinTop, rightFinRight, rightFinBottom);//12-14
		}
		
		return entireRocket;
	}
	
	public void updateRocket(
		 double noseLength,
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
		 double numberOfFins)
	{

	entireRocket = new Group();
	
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
	
	totalBodyLength = Xb + Cr;
	
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
	
	
	Platform.runLater(new Runnable(){
		@Override
		public void run()
		{
			aftRightSide.setStartX(aftBodyRightSide);
			aftRightSide.setStartY(aftBodyTop);
			aftRightSide.setEndX(aftBodyRightSide);
			aftRightSide.setEndY(aftBodyBottom);
			
			aftLeftSide.setStartX(aftBodyLeftSide);
			aftLeftSide.setStartY(aftBodyTop);
			aftLeftSide.setEndX(aftBodyLeftSide);
			aftLeftSide.setEndY(aftBodyBottom);
						
			aftBottom.setStartX(aftBodyRightSide);
			aftBottom.setStartY(aftBodyBottom);
			aftBottom.setEndX(aftBodyLeftSide);
			aftBottom.setEndY(aftBodyBottom);
							
			bottomTransitionLeft.setStartX(aftBodyLeftSide);
			bottomTransitionLeft.setStartY(aftBodyTop);
			bottomTransitionLeft.setEndX(transitionTopLeft);
			bottomTransitionLeft.setEndY(transitionHeight);
						
			bottomTransitionRight.setStartX(aftBodyRightSide);
			bottomTransitionRight.setStartY(aftBodyTop);
			bottomTransitionRight.setEndX(transitionTopRight);
			bottomTransitionRight.setEndY(transitionHeight);
						
			topTransitionLeft.setStartX(transitionTopLeft);
			topTransitionLeft.setStartY(transitionHeight);
			topTransitionLeft.setEndX(transitionTopLeft);
			topTransitionLeft.setEndY(topTransitionHeight);
						
			topTransitionRight.setStartX(transitionTopRight);
			topTransitionRight.setStartY(transitionHeight);
			topTransitionRight.setEndX(transitionTopRight);
			topTransitionRight.setEndY(topTransitionHeight);
				
			
			coneLeft.setStartX(transitionTopLeft);
			coneLeft.setStartY(topTransitionHeight);
			coneLeft.setEndX(x);
			coneLeft.setEndY(topTransitionHeight-Ln);
			
			
			coneRight.setStartX(transitionTopRight);
			coneRight.setStartY(topTransitionHeight);
			coneRight.setEndX(x);
			coneRight.setEndY(topTransitionHeight-Ln);
						
			leftFinTop.setStartX(aftBodyLeftSide);
			leftFinTop.setStartY(finHeight);
			leftFinTop.setEndX(leftFinTip);
			leftFinTop.setEndY(finTipTop);
						
			leftFinLeft.setStartX(leftFinTip);
			leftFinLeft.setStartY(finTipTop);
			leftFinLeft.setEndX(leftFinTip);
			leftFinLeft.setEndY(finTipBottom);
						
			leftFinBottom.setStartX(leftFinTip);
			leftFinBottom.setStartY(finTipBottom);
			leftFinBottom.setEndX(aftBodyLeftSide);
			leftFinBottom.setEndY(aftBodyBottom);
			
			rightFinTop.setStartX(aftBodyRightSide);
			rightFinTop.setStartY(finHeight);
			rightFinTop.setEndX(rightFinTip);
			rightFinTop.setEndY(finTipTop);
						
			rightFinRight.setStartX(rightFinTip);
			rightFinRight.setStartY(finTipTop);
			rightFinRight.setEndX(rightFinTip);
			rightFinRight.setEndY(finTipBottom);
						
			rightFinBottom.setStartX(rightFinTip);
			rightFinBottom.setStartY(finTipBottom);
			rightFinBottom.setEndX(aftBodyRightSide);
			rightFinBottom.setEndY(aftBodyBottom);

		}
	}
	);				
//			aftRightSide.setStartX(aftBodyRightSide);
//			aftRightSide.setStartY(aftBodyTop);
//			aftRightSide.setEndX(aftBodyRightSide);
//			aftRightSide.setEndY(aftBodyBottom);

			
//			aftLeftSide;
//			aftLeftSide;
//			aftLeftSide;
//			aftLeftSide;
//
//
//			aftBottom;
//			aftBottom;
//			aftBottom;
//			aftBottom;
//
//				
//			bottomTransitionLeft;
//			bottomTransitionLeft;
//			bottomTransitionLeft;
//			bottomTransitionLeft;
//
//
//			bottomTransitionRight;
//			bottomTransitionRight;
//			bottomTransitionRight;
//			bottomTransitionRight;
//
//			topTransitionLeft;
//			topTransitionLeft;
//			topTransitionLeft;
//			topTransitionLeft;
//
//			topTransitionRight;
//			topTransitionRight;
//			topTransitionRight;
//			topTransitionRight;
//
//			coneLeft;
//			coneLeft;
//			coneLeft;
//			coneLeft;
//
//			coneRight;
//			coneRight;
//			coneRight;
//			coneRight;
//				
//			leftFinTop;
//			leftFinTop;
//			leftFinTop;
//			leftFinTop;
//
//			leftFinLeft;
//			leftFinLeft;
//			leftFinLeft;
//			leftFinLeft;
//
//			leftFinBottom;
//			leftFinBottom;
//			leftFinBottom;
//			leftFinBottom;
//			
//			rightFinTop;
//			rightFinTop;
//			rightFinTop;
//			rightFinTop;
//
//			rightFinRight;
//			rightFinRight;
//			rightFinRight;
//			rightFinRight;
//
//			rightFinBottom;
//			rightFinBottom;
//			rightFinBottom;
//			rightFinBottom;
		}
	
	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		CreateRocket rocket = new CreateRocket();
		rocket.updateRocket(
				 10*5,
				 10*2,
				 7*2,
				 5*2,
				 20*2,
				 50*2,
				 20*2,
				 10*2,
				 10*2,
				 10*2,
				 10*2,
				 20*2,
				 100*2,
				 2
				);
		frame.add(rocket);
		frame.setSize(600,600);
		frame.setTitle("Rocket Creation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
