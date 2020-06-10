package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COAccelerate extends Command {
	GameWorld target;
	
	COAccelerate() {
		super("Accelerate Player Ship");
	}
	COAccelerate(GameWorld gw) {
		super("Accelerate Player Ship");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.speedup();
			return;
	    }
	}
}
