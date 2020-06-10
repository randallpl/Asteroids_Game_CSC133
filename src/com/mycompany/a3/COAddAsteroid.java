package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COAddAsteroid extends Command {
	GameWorld target;
	
	COAddAsteroid() {
		super("Add Asteroid");
	}
	COAddAsteroid(GameWorld gw) {
		super("Add Asteroid");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.addasteroid();
			return;
		}
	}
}