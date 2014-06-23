package com.rlrg.utillities.badgechecker;

import java.util.ArrayList;
import java.util.List;

import com.rlrg.utillities.exception.BadgeCheckerModuleException;

public abstract class BaseSource {
	
	protected List<ActionPerformedListener> listeners = new ArrayList<ActionPerformedListener>();
	
	/**
	 * Default constructor, please make sure classes extends this BaseSource for BadgeChecker must have #ModuleName annotation.
	 * @throws BadgeCheckerModuleException
	 */
	public BaseSource(){
		ActionObserver actionObserver = new ActionObserver(this);
	}
	
	/**
	 * If an action of extend classes have to be checked by BadgeChecker, please add this method when the action is done.
	 */
	protected void notifyListeners() {
		for (ActionPerformedListener name : listeners) {
	    	name.actionPerformed(new ActionPerformedEvent());
	    }
	}
	
	/**
	 * For any Observers tend to implement this #ActionPerformedListener
	 * @param newListener
	 */
	protected void addPerformedListener(ActionPerformedListener newListener) {
		listeners.add(newListener);
	}
}
