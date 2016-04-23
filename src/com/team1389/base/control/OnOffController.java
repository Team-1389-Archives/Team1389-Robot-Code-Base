package com.team1389.base.control;

public interface OnOffController {
	void set(boolean onOrOff);
	
	public static OnOffController combine(OnOffController[] controllers){
		return new OnOffController(){

			@Override
			public void set(boolean onOrOff) {
				for (OnOffController controller : controllers){
					controller.set(onOrOff);
				}
			}
		};
	}
}
