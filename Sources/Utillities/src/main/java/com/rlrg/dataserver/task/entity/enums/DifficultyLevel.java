package com.rlrg.dataserver.task.entity.enums;

public enum DifficultyLevel {
	EASY(1), NORMAL(2), HARD(3);
	
	private int level;
	
	private DifficultyLevel(int l){
		level = l;
	}
	
	public int getDifficultyLevel(){
		return level;
	}
}
