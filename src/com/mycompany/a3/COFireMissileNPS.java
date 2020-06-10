package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COFireMissileNPS extends Command {
	GameWorld target;
	
	COFireMissileNPS() {
		super("Fire NPS Missiles");
	}
	COFireMissileNPS(GameWorld gw) {
		super("Fire NPS Missiles");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.firenonplayermissile();
			return;
		}
	}
}
