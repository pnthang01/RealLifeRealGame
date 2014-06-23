package com.rlrg.utillities.badgechecker;

public class ActionObserver implements ActionPerformedListener {

	private String moduleName;

	public ActionObserver(BaseSource source) {
		source.addPerformedListener(this);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void actionPerformed(ActionPerformedEvent event) {
		System.out.println("Testing event successfully.");
	}

}
