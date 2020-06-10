package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CORefuel extends Command {
	private GameWorld target;
	
	CORefuel(){
		super("Refuel");
	}
	CORefuel(GameWorld gw){
		super("Refuel");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.refuelselected();
			return;
		}
	}

}
