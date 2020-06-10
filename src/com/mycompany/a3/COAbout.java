package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class COAbout extends Command {
	GameWorld target;
	
	COAbout() {
		super("About");
	}
	COAbout(GameWorld gw) {
		super("About");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.pause();
	        Boolean bOk = Dialog.show(
	            "CSC 133 Assignment 2",
	            "Randall Plant",
	            "Return To Game",
	            "Cancel"
	        );
	        if (bOk) {
	        	target.resume();
	            return;
	        }
	        else {
	        	target.resume();
	        	return;
	        }
		}
	}
}
