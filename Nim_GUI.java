import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Nim_GUI extends JFrame implements ActionListener 
{
	Random random = new Random();
	int rowOfPebbles [] = new int[10];			// The array is used to store a random numbers for generating pebble buttons and for brain uses.
	int numRow = 0;								// The int is used to determine how many rows are in this game session.
	int totalPebAmount = 0;						// Tracks how many pebbles are in the game to determine whether or not to end the game.
	int turnFlag = 1;							// 1: User's turn, 0: Brain's turn
	JPanel [] pnl_rows = new JPanel[10];		// Creates a panel for the row numbers.
	JPanel [] pnl_pebRow = new JPanel[10];		// Creates a panel for the pebble buttons.
	JPanel pnl_top = new JPanel(new FlowLayout(FlowLayout.CENTER));		// Creates a layout to be used in northern region.
	JPanel pnl_body = new JPanel(new BorderLayout());					// Creates a layout to be used in the center region.
	JPanel pnl_line = new JPanel(new FlowLayout(FlowLayout.CENTER));	// Creates a layout to be used within pnl_body.
	JPanel pnl_display = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JLabel lbl_welcome = new JLabel("Welcome to the Nim Game!");
	JLabel lbl_guide = new JLabel("You can pick any number of pebbles, BUT you can only pick one row you see below (The pebbles to the right will be picked if it exists):");
	JLabel lbl_display = new JLabel("");		// Used to display a message of who the winner is.
	JButton btn_pebble = new JButton("O");		// Used for the pebble button.
	JButton btn_quit = new JButton("Quit");		// Used for the user to quit any time.
	JButton pebbleRow_1 [] = new JButton[10];	// These arrays will hold a certain amount of pebble button objects.
	JButton pebbleRow_2 [] = new JButton[10];	
	JButton pebbleRow_3 [] = new JButton[10];
	JButton pebbleRow_4 [] = new JButton[10];
	JButton pebbleRow_5 [] = new JButton[10];
	JButton pebbleRow_6 [] = new JButton[10];
	
	final int fr_WIDTH = 800;
	final int fr_HEIGHT = 550;
	
	public class PebbleRow
	{
		public void gameSetup()
		{
			numRow = random.nextInt(4) + 3;
			rowOfPebbles[0] = random.nextInt(6) + 3;
			rowOfPebbles[1] = random.nextInt(6) + 3;
			rowOfPebbles[2] = random.nextInt(6) + 3;
			rowOfPebbles[3] = random.nextInt(6) + 3;
			rowOfPebbles[4] = random.nextInt(6) + 3;
			rowOfPebbles[5] = random.nextInt(6) + 3;
			for(int i = 0; i < numRow; i++)
			{
				totalPebAmount += rowOfPebbles[i];
			}
		}
	}
	
	public Nim_GUI()
	{
		super("Nim Game");
		
		PebbleRow pebAmount = new PebbleRow();
		pebAmount.gameSetup();
		//pebAmount.generateRows();
		JPanel pnl_board = new JPanel(new GridLayout(numRow, 1, 5, 5));	// Creates a new layout to display the row numbers in grid form.
		
		setSize(fr_WIDTH, fr_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl_top.add(lbl_welcome);						// Includes a greeting in pnl_top.
		add(pnl_body, BorderLayout.CENTER);				// Creates pnl_body for the game in the center region.
		pnl_line.add(lbl_guide);						// Includes a line which tells a short instruction in pnl_line.
		pnl_body.add(pnl_board, BorderLayout.CENTER);	// Include pnl_board for row display and puts it in the center region of pnl_body layout.
		pnl_body.add(pnl_line, BorderLayout.NORTH);		// Puts pnl_line in the northern region of pnl_body layout.
		add(pnl_top, BorderLayout.NORTH);				// Creates pnl_top in the Northern Region (above pnl_body) for the welcome message.
		add(btn_quit, BorderLayout.SOUTH);				// Add a quit button to the south of pnl_top
		btn_quit.addActionListener(this);				// Adds an event for the quit button
		pnl_display.add(lbl_display);
		pnl_body.add(pnl_display, BorderLayout.SOUTH);
		
		// Tried to get this to work. Will want to get this to work at another time.
		if(totalPebAmount == 0)
		{
			if(turnFlag == 0)
			{
				lbl_display.setText("GAME OVER! Looks like I won this time!");
			}
			else
			{
				lbl_display.setText("GAME OVER! Congratulations! You Won!");
			}
		}
		
		for(int i = 0; i < numRow; i++)
		{
			pnl_rows[i] = new JPanel(new BorderLayout());		// Creates a layout for the number row display.
			JLabel rowDisplay = new JLabel((i+1) + ")    ");		// Used to display number rows.
			pnl_rows[i].add(rowDisplay, BorderLayout.CENTER);	// Includes rowDisplay in pnl_rows
			pnl_board.add(pnl_rows[i]);							// pnl_board will grid rowDisplay within one column and whatever numRow is for the rows.
			pnl_rows[i].setLayout(new BoxLayout(pnl_rows[i], BoxLayout.X_AXIS));	// Creates a layout for the pebble buttons.
			for(int j = 0; j < rowOfPebbles[i]; j++)
			{
				if(i == 0)
				{
					pebbleRow_1[j] = new JButton("O");			// Will add a certain amount of this pebble buttons determined by the rowOfPebbles array to the JButton array. 
					pebbleRow_1[j].addActionListener(this);		// Adds an action listener to each of the pebble buttons.
					pnl_rows[i].add(pebbleRow_1[j]);			// Will add pebble buttons to pnl_rows.
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));	// Creates space between the pebble buttons.
				}
				else if(i == 1)
				{
					pebbleRow_2[j] = new JButton("O");
					pebbleRow_2[j].addActionListener(this);
					pnl_rows[i].add(pebbleRow_2[j]);
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));
				}
				else if(i == 2)
				{
					pebbleRow_3[j] = new JButton("O");
					pebbleRow_3[j].addActionListener(this);
					pnl_rows[i].add(pebbleRow_3[j]);
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));
				}
				else if(i == 3)
				{
					pebbleRow_4[j] = new JButton("O");
					pebbleRow_4[j].addActionListener(this);
					pnl_rows[i].add(pebbleRow_4[j]);
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));
				}
				else if(i == 4)
				{
					pebbleRow_5[j] = new JButton("O");
					pebbleRow_5[j].addActionListener(this);
					pnl_rows[i].add(pebbleRow_5[j]);
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));
				}
				else if(i == 5)
				{
					pebbleRow_6[j] = new JButton("O");
					pebbleRow_6[j].addActionListener(this);
					pnl_rows[i].add(pebbleRow_6[j]);
					pnl_rows[i].add(Box.createRigidArea(new Dimension(5,0)));
				}
			}
			pnl_board.add(pnl_rows[i]);	// The pebble buttons will be added to pnl_board in grid fashion while still retaining the box layout.
		}
	}
	
	public class NimBrainLVL_0
	{
		int cIndex;
		int cPebble;
		
		public int pickRow()
		{
			int index = random.nextInt(numRow);
			while(rowOfPebbles[index] == 0)
			{
				index = random.nextInt(numRow);
			}
			System.out.println(index);
			return index;
		}
		
		public int pickPebbles()
		{
			int pebAmount = rowOfPebbles[cIndex];
			int pebble = random.nextInt(pebAmount);
			System.out.println(pebble);
			return pebble;
		}
		
		public void Nim_Move()
		{
			cIndex = pickRow();
			cPebble = pickPebbles();
			
			if(cIndex == 0)	// pebbleRow_1[]
			{
				int amountRight = rowOfPebbles[0];
				
				for(int j = cPebble; j < amountRight; j++)
				{
					pebbleRow_1[j].setEnabled(false);
					rowOfPebbles[0] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount left: " + rowOfPebbles[0]);
				System.out.println("Total Pebbles: " + (totalPebAmount));
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
			else if(cIndex == 1) // pebbleRow_2[]
			{
				int amountRight = rowOfPebbles[1];
				
				for(int j = cPebble; j < amountRight; j++)
				{
					pebbleRow_2[j].setEnabled(false);
					rowOfPebbles[1] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount left: " + rowOfPebbles[1]);
				System.out.println("Total Pebbles: " + (totalPebAmount));
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
			else if(cIndex == 2) // pebbleRow_3[]
			{
				int amountRight = rowOfPebbles[2];
				
				for(int j = cPebble; j < amountRight; j++)
				{	
					pebbleRow_3[j].setEnabled(false);
					rowOfPebbles[2] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount left:" + rowOfPebbles[2]);
				System.out.println("Total Pebbles: " + totalPebAmount);
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
			else if(cIndex == 3) // pebbleRow_4[]
			{
				int amountRight = rowOfPebbles[3];

				for(int j = cPebble; j < amountRight; j++)
				{
					pebbleRow_4[j].setEnabled(false);
					rowOfPebbles[3] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount left:" + rowOfPebbles[3]);
				System.out.println("Total Pebbles: " + (totalPebAmount));
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
			else if(cIndex == 4) // pebbleRow_5[]
			{
				int amountRight = rowOfPebbles[4];
				
				for(int j = cPebble; j < amountRight; j++)
				{
					pebbleRow_5[j].setEnabled(false);
					rowOfPebbles[4] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount Left:" + rowOfPebbles[4]);
				System.out.println("Total Pebbles: " + (totalPebAmount));
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
			else if(cIndex == 5) // pebbleRow_6[]
			{
				int amountRight = rowOfPebbles[5];
				
				for(int j = cPebble; j < amountRight; j++)
				{
					pebbleRow_6[j].setEnabled(false);
					rowOfPebbles[5] --;
					totalPebAmount --;
				}
				
				if(totalPebAmount == 0)
				{
					//lbl_display.setText("GAME OVER! Looks like I won this time!");
					try
					{
						Thread.sleep(4000);
					}
					catch(InterruptedException p)
					{
					
					}
					System.exit(0);
				}
				
				System.out.println("Comp Row: " + (cIndex + 1));
				System.out.println("Comp peb: " + (cPebble + 1));
				System.out.println("Amount Left:" + rowOfPebbles[5]);
				System.out.println("Total Pebbles: " + (totalPebAmount));
				System.out.println("---------------User's Turn---------------");
				turnFlag = 1;
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		NimBrainLVL_0 Brain = new NimBrainLVL_0();
		System.out.println("Number of Rows:" + numRow);
		Object source = e.getSource();
		
		if(source == btn_quit)
		{
			System.exit(0);
		}
		else
		{
			for(int i = 0; i < 8; i++)
			{
				if(source == pebbleRow_1[i])
				{
					int amountRight = rowOfPebbles[0];
					for(int j = i; j < amountRight; ++j)
					{
						pebbleRow_1[j].setEnabled(false);
						rowOfPebbles[0] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount left: " + rowOfPebbles[0]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
					
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
						
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
				else if(source == pebbleRow_2[i])
				{
					int amountRight = rowOfPebbles[1];
					for(int j = i; j < amountRight; ++j)
					{
						pebbleRow_2[j].setEnabled(false);
						rowOfPebbles[1] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount left:" + rowOfPebbles[1]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
				
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
					
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
				else if(source == pebbleRow_3[i])
				{
					int amountRight = rowOfPebbles[2];
					for(int j = i; j < amountRight; ++j)
					{	
						pebbleRow_3[j].setEnabled(false);
						rowOfPebbles[2] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount left:" + rowOfPebbles[2]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
					
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
					
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
				else if(source == pebbleRow_4[i])
				{
					int amountRight = rowOfPebbles[3];
					for(int j = i; j < amountRight; ++j)
					{
						pebbleRow_4[j].setEnabled(false);
						rowOfPebbles[3] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount left:" + rowOfPebbles[3]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
					
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
						
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
				else if(source == pebbleRow_5[i])
				{
					int amountRight = rowOfPebbles[4];
					for(int j = i; j < amountRight; ++j)
					{
						pebbleRow_5[j].setEnabled(false);
						rowOfPebbles[4] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount Left:" + rowOfPebbles[4]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
					
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
					
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
				else if(source == pebbleRow_6[i])
				{
					int amountRight = rowOfPebbles[5];
					for(int j = i; j < amountRight; ++j)
					{
						pebbleRow_6[j].setEnabled(false);
						rowOfPebbles[5] --;
						totalPebAmount --;
					}
					
					if(totalPebAmount == 0)
					{
						try
						{
							//lbl_display.setText("GAME OVER! Congratulations! You Won!");
							Thread.sleep(4000);
						}
						catch(InterruptedException p)
						{
						
						}
						System.exit(0);
					}
					
					System.out.println("Amount Left:" + rowOfPebbles[5]);
					System.out.println("Total Pebbles: " + (totalPebAmount));
					System.out.println("---------------Brain's Turn---------------");
				
					try
					{
						Thread.sleep(2000);
					}
					catch(InterruptedException p)
					{
				
					}
					turnFlag = 0;
					Brain.Nim_Move();
				}
			}
		}
	}
	public static void main(String args[])
	{
		Nim_GUI Nim = new Nim_GUI();
		Nim.setVisible(true);
	}
}