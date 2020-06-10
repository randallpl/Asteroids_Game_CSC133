package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class MissileLauncherNPS extends MovableObject {
	MissileLauncherNPS(double x, double y, int a){
		setlocationx(x);
		setlocationy(y);
		setcollisionradius(0);
		setcolor(ColorUtil.LTGRAY);
		setspeed(0);
		setazimuth(a);
	}
	public int move(int xb, int yb) {
		return 0;
	}
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		return;
	}
}
