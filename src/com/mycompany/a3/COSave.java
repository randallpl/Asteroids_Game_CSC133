package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COSave extends Command {
	GameWorld target;
	
	COSave() {
		super("Save");
	}
	COSave(GameWorld gw) {
		super("Save");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			System.out.println("Save command not yet implemented!.");
			return;
		}
	}
}
