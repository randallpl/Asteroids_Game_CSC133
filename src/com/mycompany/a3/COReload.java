package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COReload extends Command {
	GameWorld target;
	
	COReload() {
		super("Reload Missiles");
	}
	COReload(GameWorld gw) {
		super("Reload Missiles");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.reload();
			return;
		}
	}
}
