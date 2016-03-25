package com.team1389.base.util.control;

import org.strongback.components.Switch;
import org.strongback.components.ui.ContinuousRange;

public class AxisAsButton implements Switch{
	ContinuousRange axis;

	public AxisAsButton(ContinuousRange axis) {
		this.axis = axis;
	}
	@Override
	public boolean isTriggered() {
		if (axis.read() > 0.5){
			return true;
		} else {
			return false;
		}
	}

}
