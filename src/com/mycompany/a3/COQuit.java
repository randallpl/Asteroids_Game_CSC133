package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class COQuit extends Command {
	GameWorld target;
	
	COQuit() {
		super("Quit");
	}
	COQuit(GameWorld gw) {
		super("Quit");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.pause();
	        Boolean bOk = Dialog.show(
	            "Confirm quit",
	            "Are you sure you want to quit?",
	            "Ok",
	            "Cancel"
	        );
	        if (bOk) {
	            Display.getInstance().exitApplication();
	        }
	        else {
	        	target.resume();
	        	return;
	        }
		}
	}
}
