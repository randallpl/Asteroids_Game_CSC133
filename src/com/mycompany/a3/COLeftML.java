package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COLeftML extends Command {
	GameWorld target;
	
	COLeftML() {
		super("Missile Launcher to Port");
	}
	COLeftML(GameWorld gw) {
		super("Missile Launcher to Port");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.portml();
			return;
		}
	}
}
