package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COJump extends Command {
	GameWorld target;
	
	COJump() {
		super("Hyperspace Jump");
	}
	COJump(GameWorld gw) {
		super("Hyperspace Jump");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.jumpdrive();
			return;
		}
	}
}
