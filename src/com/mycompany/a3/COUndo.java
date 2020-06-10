package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COUndo extends Command {
	GameWorld target;
	
	COUndo() {
		super("Undo");
	}
	COUndo(GameWorld gw) {
		super("Undo");
		target=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			System.out.println("Undo command not yet implemented!");
			return;
		}
	}
}
