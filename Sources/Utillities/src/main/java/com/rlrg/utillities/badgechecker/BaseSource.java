package com.rlrg.utillities.badgechecker;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSource {
	
	protected List<ActionPerformedListener> listeners = new ArrayList<ActionPerformedListener>();
	
	protected abstract void initListener();
	
	/**
	 * Default constructor, please make sure extends classes have the method initListener do initialize ActionObserver.
	 * Create a class extends #ActionPerformedListener with moduleName property. Like this
	 * ActionPerformedListener actionObserver = new ActionObserver(this);
	 */
	public BaseSource(){
		initListener();
	}
	
	/**
	 * If an action of extend classes have to be checked by BadgeChecker, please add this method when the action is done.
	 */
	protected void notifyListeners(String action, Long userId, Object... props) {
		for (ActionPerformedListener name : listeners) {
	    	name.actionPerformed(new ActionPerformedEvent(action, userId, props));
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
