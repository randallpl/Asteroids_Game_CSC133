package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class MyButton extends Button {
	MyButton(String s){
		super(s);
		init();
	}
	
	public void init() {
		this.getAllStyles().setBgTransparency(185);
		this.getAllStyles().setBgColor(ColorUtil.rgb(255,0,0));
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255,0,0)));
	}
}
