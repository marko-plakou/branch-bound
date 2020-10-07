package aiesfinalproject;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class aiesfinalproject {
	
	
	

	public static void main(String[] args) {
		System.out.println("Using Branch&Bound Algorithm in order to escape the maze with the quickest possible path.\n");
		System.out.println("First Approach\n");
		//Initialization
		final Integer[][] maze= new Integer [6][5]; //The Maze Itself
		maze[0][0]=0;
		maze[1][0]=0;
		maze[2][0]=0;
		maze[3][0]=0;
		maze[4][0]=0;
		maze[5][0]=0;
		maze[0][1]=1;
		maze[1][1]=1;
		maze[2][1]=1;
		maze[3][1]=1;
		maze[4][1]=1;
		maze[5][1]=1;
		maze[0][2]=0;
		maze[1][2]=1;
		maze[2][2]=0;
		maze[3][2]=0;
		maze[4][2]=1;
		maze[5][2]=0;
		maze[0][3]=0;
		maze[1][3]=1;
		maze[2][3]=1;
		maze[3][3]=1;
		maze[4][3]=1;
		maze[5][3]=0;
		maze[0][4]=0;
		maze[1][4]=0;
		maze[2][4]=0;
		maze[3][4]=0;
		maze[4][4]=0;
		maze[5][4]=0;
		
	final Point sp =new Point(); //starting Point
	sp.setLocation(0, 1);
	Stack<Point>ma=new Stack<Point>();// Search Line
	ma.push(sp);
	int limit=100; //Limit
	int counter=0;
	ArrayList<String>ks=new ArrayList<String>(); //Pre-examined Total
	ks.clear();
	final	Point fp=new Point(); //finish Point
			Point microscope=new Point();  // Microscope
			Point child1=new Point();//Down Child
			Point child2=new Point();//Right Child
			Point child3=new Point();//Left Child
			Point child4=new Point();//Up  (Parent)
		
		
		while(!ma.isEmpty()&&!ks.contains(fp.toString())||counter>limit){
			
			fp.setLocation(5,1);
			counter++;
			System.out.println("\n The counter is:"+counter);
			System.out.print("Pre-examined total(Ê.Ó):");
			printks(ks);
			microscope=ma.peek();
			System.out.print(" The microscope is:"+"("+microscope.x+","+microscope.y+") ");
			System.out.print(" The score of this  is:"+score(microscope));
			System.out.print(" Search line(Ì.Á):");
			printma(ma);
			ma.pop();
			if(ks.contains(microscope.toString())) {System.out.print(" This step has already been examined .");continue;}
			
				else {					
				ks.add(microscope.toString());
				System.out.print(" Child situations(Ê.Ð):");
					if(microscope.x!=0) {
						if(maze[microscope.x-1][microscope.y]==1) {
							child4.setLocation(microscope.x-1,microscope.y);
							System.out.print("("+child4.x+","+child4.y+")");
							ma.push(child4);
							
							}
										}
					if(microscope.y!=4) {
						if(maze[microscope.x][microscope.y+1]==1) {
							child3.setLocation(microscope.x,microscope.y+1);
							System.out.print("("+child3.x+","+child3.y+")");
							ma.push(child3);
							}
										}
					if(microscope.y!=0) {
						if(maze[microscope.x][microscope.y-1]==1) {
							child2.setLocation(microscope.x,microscope.y-1);
							System.out.print("("+child2.x+","+child2.y+")");
							ma.push(child2);
							}
										}
					if(microscope.x!=5) {
						if(maze[microscope.x+1][microscope.y]==1) {
							child1.setLocation(microscope.x+1,microscope.y);
							System.out.print("("+child1.x+","+child1.y+")");
							ma.push(child1);
							}
										}
				
					}
			}
		System.out.println("\n"+" You got out with "+counter+" steps.");
		ks.remove(counter-1);//Removing the Finish Point.
		prioritizeright(maze,ks,ma,counter);
	}
	
	
	static public void printma(Stack<Point> ma) {//Printing Search Line(M.A)
	for (int i=0;i<ma.size();i++) {
		Point p=new Point();
		p=ma.get(i);
		System.out.print(" ("+p.x+","+p.y+")");
	}
}
	static public void printks(ArrayList<String> ks) {//Printing pre-examined total(Ê.Ó)
	for (int i=0;i<ks.size();i++) {
		String coord="";
		coord=ks.get(i);
		String coord1=coord.replaceAll("java.awt.Point", " ");
		System.out.print(coord1);
	}
	
}
	
	static public int score(Point point) {//Finding the Manhattan distance between starting point and point given.
		int score=Math.abs(point.x-0)+Math.abs(point.y-1)+1;
		return score;
	}
	
	
	
	static public int prioritizeright(Integer[][] maze1,ArrayList<String> ks1,Stack<Point> ma1,int limit1) {
		System.out.println("\n Continuing the procedure of Branch&Bound ...");
		Integer[][] maze= new Integer [6][6];
		 maze=maze1;
		 Stack<Point>ma=new Stack<Point>();// Search Line
		 	ma=ma1;
			int limit=limit1;
			int counter=0;
			ArrayList<String>ks=new ArrayList<String>(); //Pre-examined Total
			ks=ks1;
			Point fp=new Point(); //finish Point
			fp.setLocation(5,1);
			Point sp=new Point(); //finish Point
			sp.setLocation(1,1);
			Point microscope=new Point();  // Microscope
			Point child1=new Point();//Right Child
			Point child2=new Point();//Up Child
			Point child3=new Point();//Left Child
			Point child4=new Point();//Down Child
			ma.add(0, sp);
			while(!ma.isEmpty()&&!ks.contains(fp.toString())) {
				counter++;
				System.out.println("\n The counter is: "+counter);
				System.out.print(" Pre-examined total(Ê.Ó):");
				printks(ks);
				microscope=ma.peek();
				System.out.print(" The microscope is:"+"("+microscope.x+","+microscope.y+") ");
				System.out.print(" Search line(Ì.Á):");
				printma(ma);
				ma.pop();
				if(ks.contains(microscope.toString())&&!(microscope.x<=1&&microscope.y<=1)) {System.out.print(" This step has already been examined and it will not lead to better qetaway because its score is:"+" ("+score(microscope)+")");continue;}
				if(score(microscope)>=limit) {System.out.print(" This step exceeds or balances the current score as its score is "+" ("+score(microscope)+") "+" so it has branched out.");continue;}
				if(!ks.contains(microscope.toString())) {ks.add(microscope.toString());};
				System.out.print(" Child situations(Ê.Ð):");
				if(microscope.x!=5) {//Down
					if(maze[microscope.x+1][microscope.y]==1) {
						child4.setLocation(microscope.x+1,microscope.y);
						System.out.print("("+child4.x+","+child4.y+")");
						ma.push(child4);
						}
									}
				if(microscope.y!=0) {//left
					if(maze[microscope.x][microscope.y-1]==1) {
						child3.setLocation(microscope.x,microscope.y-1);
						System.out.print("("+child3.x+","+child3.y+")");
						ma.push(child3);}
									}
				if(microscope.x!=0) {//Up
					if(maze[microscope.x-1][microscope.y]==1) {
						child2.setLocation(microscope.x-1,microscope.y);
						System.out.print("("+child2.x+","+child2.y+")");
						ma.push(child2);}
									}
				if(microscope.y!=5) {//Right
					if(maze[microscope.x][microscope.y+1]==1) {
						child1.setLocation(microscope.x,microscope.y+1);
						System.out.print("("+child1.x+","+child1.y+")");
						ma.push(child1);}
									}
				}
			if(counter<limit1) {System.out.println("\n You got out faster with "+counter+" steps.");
				return counter;
			}
			else {System.out.println("\n NO BETTER PATH WAS FOUND.");return 100;}
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}