package swen225.battleships;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GraphicalInterface extends JFrame implements WindowListener {
	/**
	 * The following fields cache various icons so we don't need to load them
	 * everytime.
	 */
	private static ImageIcon boardPartition = makeImageIcon("boardPartition.png");
	private static ImageIcon emptySquare = makeImageIcon("water.png");
	private static ImageIcon hitSquare = makeImageIcon("explosion.png");
	private static ImageIcon missSquare = makeImageIcon("missedWater.png");
	private static ImageIcon hShipLeftSquare = makeImageIcon("hShipLeft.png");	
	private static ImageIcon hShipRightSquare = makeImageIcon("hShipRight.png");
	private static ImageIcon hShipMiddleSquare = makeImageIcon("hShipMiddle.png");
	private static ImageIcon vShipBottomSquare = makeImageIcon("vShipBottom.png");	
	private static ImageIcon vShipTopSquare = makeImageIcon("vShipTop.png");
	private static ImageIcon vShipMiddleSquare = makeImageIcon("vShipMiddle.png");
	
	private BattleShipsGame game;
	private JPanel outerMostPanel;	
	private JLabel[][] leftBattleGrid;
	private JLabel[][] rightBattleGrid;
	
	public GraphicalInterface() {
		super("The Game of Battleships!");		
		
		game = new BattleShipsGame(15,15);
		game.createRandomBoard(new Random());



		// Construct the outermost panel. This will include the grid display, as
		// well as any buttons as needed.
		makeOutermostPanel(15,15);
		getContentPane().add(outerMostPanel);
		drawBoard();
		
		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
				
		// Pack the window. This causes the window to compute its size,
		// including the layout of its components.
		pack();
		
		// Finally, make the window visible
		setVisible(true);
	}

	/**
	 * This method is called when the user clicks on the "X" button in the
	 * right-hand corner.
	 * 
	 * @param e
	 */
	public void windowClosing(WindowEvent e) {
		// Ask the user to confirm they wanted to do this
		int r = JOptionPane.showConfirmDialog(this, 
				new JLabel("Exit BattleShips?"), "Confirm Exit",
									JOptionPane.YES_NO_OPTION, 
									JOptionPane.QUESTION_MESSAGE);
		
		if (r == JOptionPane.YES_OPTION) 
			System.exit(0);
		
	}
	
	/**
	 * This method is called after the X button has been clicked.
	 * @param e
	 */
    public void windowClosed(WindowEvent e) {}

    // The following methods are part of the WindowListener interface,
    // but are not needed here.
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}

    
    /**
	 * This method determines the appropriate icon for a given square.
	 * 
	 * @param gs
	 * @param visible
	 * @return
	 */
    private ImageIcon getSquareIcon(GridSquare gs, boolean visible) {
    	if(gs.getType() ==  GridSquare.Type.EMPTY) {
    		return emptySquare;
    	} else if(gs.getType() ==  GridSquare.Type.MISS) {
    		return missSquare;
    	} else if(gs.getType() ==  GridSquare.Type.HIT) {
    		return hitSquare;
    	} else if(gs.getType() == GridSquare.Type.SHIP){
    		// TO DO!
			if(visible){
				if(gs.getShipPart() == GridSquare.ShipPart.HORIZONTAL_LEFT_END) {
					return hShipLeftSquare;
				} else if(gs.getShipPart() == GridSquare.ShipPart.HORIZONTAL_RIGHT_END) {
					return hShipRightSquare;
				} else if(gs.getShipPart() == GridSquare.ShipPart.HORIZONTAL_MIDDLE) {
					return hShipMiddleSquare;
				} else if(gs.getShipPart() == GridSquare.ShipPart.VERTICAL_TOP_END) {
					return vShipTopSquare;
				} else if(gs.getShipPart() == GridSquare.ShipPart.VERTICAL_BOTTOM_END) {
					return vShipBottomSquare;
				} else if(gs.getShipPart() == GridSquare.ShipPart.VERTICAL_MIDDLE) {
					return vShipMiddleSquare;
				}
			}else{
				return emptySquare;
			}


    	}else{
			return null;
		}
    	return null;
    }
    
    /**
     * This method is used to draw the game board
     */
    private void drawBoard() {
    	// You'll want to use getSquareIcon here ...
		for(int x = 0; x < 15 * 2 + 1; x++){
			for(int y = 0; y < 15; y++){
				if(x < 15){

				}
			}
		}
    }
    
    
    
    private Map<Integer,Map<Integer,JLabel>> leftGrid = new HashMap<>();
    private Map<Integer,Map<Integer,JLabel>> rGrid = new HashMap<>();
    
    /**
     * This method is used to create the outermost panel.
     * @return
     */
    private void makeOutermostPanel(int width, int height) {    	    
    	leftBattleGrid = new JLabel[width][height];
    	rightBattleGrid = new JLabel[width][height];


		JPanel buttonHolder = new JPanel();
		buttonHolder.setLayout(new FlowLayout(FlowLayout.LEADING));
		JButton button = new JButton("New Game");
		buttonHolder.add(button);

		JPanel grids = new JPanel();
		grids.setLayout(new GridLayout(height, width * 2 + 1, 1, 1));


		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				leftBattleGrid[x][y] = new JLabel();
				rightBattleGrid[x][y] = new JLabel();

				leftBattleGrid[x][y].setIcon(makeImageIcon("water.png"));
				//leftBattleGrid[x][y].addMouseListener();

				rightBattleGrid[x][y].setIcon(makeImageIcon("water.png"));
				//rightBattleGrid[x][y].addMouseListener();
			}
		}
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width * 2 + 1; x++){

				if(x < width){
					grids.add(leftBattleGrid[x][y]);
				}
				else if(x == width){
						JLabel mid = new JLabel();
						mid.setIcon(makeImageIcon("boardPartition.png"));
						grids.add(mid);

				}
				else if(x >= width+1){
					grids.add(rightBattleGrid[x-(width+1)][y]);
				}
			}
		}



    	// This method needs to:
    	//
    	// 1. Construct a panel for representing the left and right battle
		// grids. This should use a GridLayout which is large enough to contain
		// both the left and right grids, as well as a single partition square
		// in the middle. Each square in the left and right battle grids should
		// be initialised with a JLabel containing the emptySquare icon. An
		// empty border of sufficient size should be placed around this panel.
    	//
    	// 2. Construct another panel for holding the "new game" button, and add
		// that button to the panel. This should use the FlowLayout to ensure
		// the button is located in the center.
    	//
    	// 3. Finally, construct the outermost panel, whilst adding the battle
		// grid panel and button panel (put the buttons above the battle grid).

    	outerMostPanel = new JPanel();
		outerMostPanel.add(buttonHolder);
		outerMostPanel.add(grids);

		outerMostPanel.setLayout(new FlowLayout());

    }
    
	/**
	 * Helper method for loading image icons.
	 * @param filename
	 * @return
	 */
	private static ImageIcon makeImageIcon(String filename) {		
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		java.net.URL imageURL = BattleShip.class.getResource(filename);

		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		return icon;
	}
	
	/**
	 * Main method for the Graphical User Interface
	 */
	public static void main(String[] args) {
		new GraphicalInterface();
	}
}
