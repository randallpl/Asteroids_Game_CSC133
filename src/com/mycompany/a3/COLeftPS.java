package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COLeftPS extends Command {
	GameWorld target;
	
	COLeftPS() {
		super("Turn to Port");
	}
	COLeftPS(GameWorld gw) {
		super("Turn to Port");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.port();
			return;
		}
	}
}
