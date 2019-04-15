package org.pizzagame.view;
import org.pizzagame.controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainForm extends JPanel implements ItemListener {
	private static GameManager gameManager = new GameManager();
	private static UpdateGuiCallBack updateGuiCallback = new UpdateGuiCallBack();
	private static JComponent contentPane = new MainForm();
	private static GameOverPanel gameOverPanel = new GameOverPanel(gameManager);
	private static GamePanel gamePanel = new GamePanel(gameManager);
	
    public MainForm() {
        super(new BorderLayout());
	    gameManager.registerCallBack(updateGuiCallback);
    }
 
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Pizza Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItemFile = new JMenuItem("New Game");
        menuItemFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.newGame();
				contentPane.add(gamePanel, BorderLayout.CENTER);
				gamePanel.setVisible(true);
				gameOverPanel.setVisible(false);
			}
        });
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        menu.add(menuItemFile);
        menu.add(menuItemExit);
        menuBar.add(menu);                		
        contentPane.add(menuBar, BorderLayout.NORTH);        
        contentPane.add(gamePanel, BorderLayout.CENTER);
        contentPane.add(gameOverPanel, BorderLayout.CENTER);
        contentPane.setOpaque(true);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        gamePanel.setVisible(false);
        gameOverPanel.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	private static class UpdateGuiCallBack implements GameManager.CallBack {
		@Override
		public void updateGui() {
			if (gameManager.getState() == GameManager.States.GameOver) {
				gamePanel.setVisible(false);
				gameOverPanel.setVisible(true);				
			}
			else if (gameManager.getState() == GameManager.States.Game)
			{
				gameOverPanel.setVisible(false);
				gamePanel.setVisible(true);
			}
		}
	}
    
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	private static final long serialVersionUID = 1L;
}
