package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CONew extends Command {
	GameWorld target;
	
	CONew() {
		super("New");
	}
	CONew(GameWorld gw) {
		super("New");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.newgame();
			return;
		}
	}
}
