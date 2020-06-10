package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CODeccelerate extends Command {
	GameWorld target;
	
	CODeccelerate() {
		super("Deccelerate Player Ship");
	}
	CODeccelerate(GameWorld gw) {
		super("Deccelerate Player Ship");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.slowdown();
			return;
		}
	}
}
