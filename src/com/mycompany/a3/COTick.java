package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COTick extends Command {
	GameWorld target;
	
	COTick() {
		super("Time Tick");
	}
	COTick(GameWorld gw) {
		super("Time Tick");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.tick();
			return;
		}
	}
}
