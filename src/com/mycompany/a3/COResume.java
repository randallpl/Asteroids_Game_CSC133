package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COResume extends Command {
	GameWorld target;
	Game control;
	
	COResume(){
		super("Resume");
	}
	COResume(GameWorld t, Game g){
		super("Resume");
		target=t;
		control=g;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			target.resume();
			control.resume();
			return;
		}
	}
}
