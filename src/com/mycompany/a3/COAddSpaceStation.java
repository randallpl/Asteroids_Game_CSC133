package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COAddSpaceStation extends Command {
	GameWorld target;
	
	COAddSpaceStation() {
		super("Add Space Station");
	}
	COAddSpaceStation(GameWorld gw) {
		super("Add Space Station");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.addspacestation();
			return;
		}
	}		
}
