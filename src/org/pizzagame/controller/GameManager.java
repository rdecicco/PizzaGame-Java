package org.pizzagame.controller;

import org.pizzagame.model.Player;

public class GameManager {
	private static boolean fullGame = true;
	private CallBack updateGuiCallback;
	private States state;
    private Integer numberOfPizzas;
	private Player playerA;
	private Player playerB;

    public enum States {
        GameOver,
        Game
    };
	
    public interface CallBack {
    	public void updateGui();
    }
    
    public void registerCallBack(CallBack callback) {
    	updateGuiCallback = callback;
    }
    
    public GameManager() {
    	state = States.GameOver;
    	numberOfPizzas = 0;
    	playerA = new Player(1, "Player A");
    	playerB = new Player(2, "Player B");
    }
    
    public void newGame() {    	
    	numberOfPizzas = 10 + ((int) (Math.random() * 100)) % 11;
    	playerA.setLastNumberOfEatenPizzas(0);
    	playerA.setState(Player.States.Alive);
    	playerA.setCurrentPlayer(true);
    	playerB.setLastNumberOfEatenPizzas(0);
    	playerB.setState(Player.States.Alive);
    	playerB.setCurrentPlayer(false);
    	state = States.Game;
    }
    
    public boolean eatPizzas(int numberOfEatenPizzas, Player currentPlayer, Player otherPlayer) {
        if (state != States.Game)
            return false;

        if (!currentPlayer.getCurrentPlayer() || otherPlayer.getCurrentPlayer())
        	return false;
        
        if (numberOfEatenPizzas > numberOfPizzas)
            return false;

        currentPlayer.setLastNumberOfEatenPizzas(numberOfEatenPizzas);

        if (numberOfPizzas == 0) {
            swapPlayers(currentPlayer, otherPlayer);
            updateGuiCallback.updateGui();
            return true;
        }

        numberOfPizzas -= numberOfEatenPizzas;

        if (fullGame) {
        	if (numberOfPizzas == 0) {
        		currentPlayer.setState(Player.States.Dead);
        		state = States.GameOver;
        		updateGuiCallback.updateGui();
        		return true;
        	}
        }
        else
        {
        	if (numberOfPizzas == 0 || numberOfPizzas == 1) {
        		currentPlayer.setState(Player.States.Dead);
        		state = States.GameOver;
        		updateGuiCallback.updateGui();
        		return true;
        	}
        	else if (numberOfPizzas == 2) {
        		otherPlayer.setState(Player.States.Dead);
        		state = States.GameOver;
        		updateGuiCallback.updateGui();
        		return true;
        	}
        }

        swapPlayers(currentPlayer, otherPlayer);
        updateGuiCallback.updateGui();
        return true;
    }

    private void swapPlayers(Player currentPlayer, Player otherPlayer) {
        currentPlayer.setCurrentPlayer(false);
        otherPlayer.setCurrentPlayer(true);
    }
    
    public String getStateName() {
    	switch (state) {
    	case GameOver:
    		return "Game Over";
    	case Game:
    		return "Game";
    	default:
    		return "Unknown";
    	}	
    }

    public States getState() {
		return state;
	}
    
	public void setState(States state) {
		this.state = state;
	}
	
	public Integer getNumberOfPizzas() {
		return numberOfPizzas;
	}
	
	public void setNumberOfPizzas(Integer numberOfPizzas) {
		this.numberOfPizzas = numberOfPizzas;
	}
	
	public Player getPlayerA() {
		return playerA;
	}
	
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}
	
	public Player getPlayerB() {
		return playerB;
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	
	public Player getOtherPlayer(Integer playerId) {
		if (playerA.getPlayerId() == playerId)
			return playerB;
		else if (playerB.getPlayerId() == playerId)
			return playerA;
		return null;
	}
	
	public boolean canPass() {
		Player otherPlayer;
		if (playerA.getCurrentPlayer()) {
			otherPlayer = playerB;
		}
		else
		{
			otherPlayer = playerA;
		}

		if (otherPlayer.getLastNumberOfEatenPizzas() == 0)
			return false;

		for (int i = 1; i < 4; i++) {
			if (i != otherPlayer.getLastNumberOfEatenPizzas())
			{
				if (i < numberOfPizzas && numberOfPizzas > 2)
					return false;
			}
		}

		return true;
	}
}
