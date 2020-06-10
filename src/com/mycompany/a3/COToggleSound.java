package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COToggleSound extends Command {
	GameWorld target;
	
	COToggleSound() {
		super("Sound:");
	}
	COToggleSound(GameWorld gw) {
		super("Sound:");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(target.getsound()==true) {
			target.setsound(false);
		}
		else {
			target.setsound(true);
		}
		return;
	}
}
