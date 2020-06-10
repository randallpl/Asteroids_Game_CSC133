package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CORightML extends Command {
GameWorld target;
	
	CORightML() {
		super("Missile Launcher to Starboard");
	}
	CORightML(GameWorld gw) {
		super("Missile Launcher to Starboard");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.starboardml();
			return;
		}
	}

}
