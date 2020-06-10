package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COAddNonPlayerShip extends Command {
	GameWorld target;
	
	COAddNonPlayerShip() {
		super("Add NPS");
	}
	COAddNonPlayerShip(GameWorld gw) {
		super("Add NPS");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.addnonplayership();
			return;
		}
	}
}
