package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class COPause extends Command {
	GameWorld target;
	Game control;
	
	COPause(){
		super("Pause");
	}
	COPause(GameWorld t, Game g){
		super("Pause");
		target=t;
		control=g;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getKeyEvent() != -1) {
			control.pause();
			target.pause();
			return;
		}
	}
}
