package org.pizzagame.view;
import org.pizzagame.controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ItemListener {	
	private static final long serialVersionUID = 1L;
	private GameManager gameManager;
	private PlayerComponent playerAComponent;
	private JLabel labelNumberOfPizzas;
	private PlayerComponent playerBComponent;

	public GamePanel(GameManager gameManager) {
	    setLayout(new GridBagLayout());
	    this.gameManager = gameManager;
		playerAComponent = new PlayerComponent(this.gameManager.getPlayerA(), this.gameManager);
		labelNumberOfPizzas = new JLabel("Pizzas: " + this.gameManager.getNumberOfPizzas());
		playerBComponent = new PlayerComponent(this.gameManager.getPlayerB(), this.gameManager);
		add(playerAComponent, new GridBagConstraints(0,0,3,1,1.0,1.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2,100,2,2), 0, 0));
		add(labelNumberOfPizzas, new GridBagConstraints(1,0,3,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2,2,2,2), 0, 0));
		add(playerBComponent, new GridBagConstraints(2,0,3,1,1.0,1.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2,2,2,100), 0, 0));
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		// TODO Auto-generated method stub
		super.setVisible(aFlag);
		labelNumberOfPizzas.setText("Pizzas: " + this.gameManager.getNumberOfPizzas());
		playerAComponent.update();
		playerBComponent.update();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}	
}

