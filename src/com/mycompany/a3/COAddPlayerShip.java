package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COAddPlayerShip extends Command {
	GameWorld target;
	
	COAddPlayerShip() {
		super("Add Player Ship");
	}
	COAddPlayerShip(GameWorld gw) {
		super("Add Player Ship");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.addplayership();
			return;
		}
	}
}
