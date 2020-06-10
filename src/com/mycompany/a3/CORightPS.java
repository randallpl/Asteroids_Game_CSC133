package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CORightPS extends Command {
	GameWorld target;
	
	CORightPS() {
		super("Turn to Starboard");
	}
	CORightPS(GameWorld gw) {
		super("Turn to Starboard");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.starboard();
			return;
		}
	}
}
