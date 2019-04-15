package org.pizzagame.view;
import org.pizzagame.controller.*;
import org.pizzagame.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = 1L;
	private GameManager gameManager;
	private JLabel label;

	public GameOverPanel(GameManager gameManager) {
		this.gameManager = gameManager;
		setLayout(new GridBagLayout());
		label = new JLabel();
		add(label, new GridBagConstraints(1, 1, 1, 1, 120, 100, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2,2,2,2), 0, 0));
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		Player playerA = gameManager.getPlayerA();
		Player playerB = gameManager.getPlayerB();
		if (playerA.getState() == Player.States.Alive && playerB.getState() == Player.States.Alive)
			label.setText("Game Over");
		else if (playerA.getState() == Player.States.Alive && playerB.getState() == Player.States.Dead)
			label.setText("Winner is " + playerA.getName());
		else if (playerA.getState() == Player.States.Dead && playerB.getState() == Player.States.Alive)
			label.setText("Winner is " + playerB.getName());
		else
			label.setText("Players are both dead");
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}		
}
