package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COFireMissile extends Command {
	GameWorld target;
	
	COFireMissile() {
		super("Fire Missile");
	}
	COFireMissile(GameWorld gw) {
		super("Fire Missile");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.firemissile();
			return;
		}
	}
}
