package org.pizzagame.model;
import java.lang.String;

public class Player {
	private Integer playerId;
	private Boolean currentPlayer;
	private States state;
	private String name;
	private Integer lastNumberOfEatenPizzas;
	
	public enum States {
		Dead,
		Alive
	}
		
	public Player(Integer playerId, String name) {
		this.playerId = playerId;
		this.name = name;		
		currentPlayer = false;
		state = States.Alive;
		lastNumberOfEatenPizzas = 0;
	}
	
	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Boolean getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Boolean currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public States getState() {
		return state;
	}
	
	public void setState(States state) {
		this.state = state;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLastNumberOfEatenPizzas() {
		return lastNumberOfEatenPizzas;
	}

	public void setLastNumberOfEatenPizzas(Integer lastNumberOfEatenPizzas) {
		this.lastNumberOfEatenPizzas = lastNumberOfEatenPizzas;
	}
}
