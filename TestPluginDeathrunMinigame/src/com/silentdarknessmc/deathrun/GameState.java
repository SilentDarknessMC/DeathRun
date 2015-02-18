package com.silentdarknessmc.deathrun;

import com.silentdarknessmc.deathrun.GameState;

public enum GameState {
	IN_LOBBY(true), IN_INTRO(false), IN_GAME(false), POST_GAME(false), RESETTING(false);
	
	private boolean canJoin;
	private static GameState currentState;
	
	GameState(boolean canJoin) {
		this.canJoin = canJoin;
	}
	
	public boolean canJoin() {
		return canJoin;
	}
	
	public static void setState(GameState state) {
		GameState.currentState = state;
	}
	
	public static boolean isState(GameState state) {
		return GameState.currentState == state;
	}
	
	public static GameState getState() {
		return currentState;
	}
}