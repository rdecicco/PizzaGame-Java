package org.pizzagame.view;
import org.pizzagame.controller.*;
import org.pizzagame.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerComponent extends JPanel implements ItemListener {
	private static final long serialVersionUID = 1L;
	private Player player;
	private GameManager gameManager;
	private GridLayout layout;
	private JLabel labelName;	
	private JComboBox<Integer> comboEatenPizzas = new JComboBox<Integer>();
	private JButton buttonConfirm;
	private JButton buttonPass;
	
	public PlayerComponent(Player player, GameManager gameManager) {
		layout = new GridLayout(4,1,5,5);		
		setLayout(layout);
		this.player = player;
		this.gameManager = gameManager;
		setBorder(BorderFactory.createRaisedBevelBorder());
		labelName = new JLabel(player.getName());
		labelName.setHorizontalAlignment(JLabel.CENTER);
		add(labelName);
		setComboEatenPizzas(0);
		add(comboEatenPizzas);
		buttonConfirm = new JButton("Confirm");
		buttonConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.eatPizzas(comboEatenPizzas.getItemAt(comboEatenPizzas.getSelectedIndex()), player, player.getPlayerId() == gameManager.getPlayerA().getPlayerId() ? gameManager.getPlayerB() : gameManager.getPlayerA());
			}
		});
		add(buttonConfirm);
		
		buttonPass = new JButton("Pass");
		buttonPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.eatPizzas(0, player, player.getPlayerId() == gameManager.getPlayerA().getPlayerId() ? gameManager.getPlayerB() : gameManager.getPlayerA());
			}
		});
		add(buttonPass);
	}
		
	public void setComboEatenPizzas(Integer disabledValue) {
		comboEatenPizzas.removeAllItems();		
		for (int i = 1; i < 4; i++) {
			if (i != disabledValue) {
				comboEatenPizzas.addItem(i);
			}
		}
	}
	
	public void update() {
		if (player.getCurrentPlayer()) {
			setComboEatenPizzas(gameManager.getOtherPlayer(player.getPlayerId()).getLastNumberOfEatenPizzas());
			comboEatenPizzas.setEnabled(true);
			buttonConfirm.setEnabled(true);
			buttonPass.setEnabled(gameManager.canPass());
		}
		else
		{
			comboEatenPizzas.setEnabled(false);
			buttonConfirm.setEnabled(false);
			buttonPass.setEnabled(false);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
