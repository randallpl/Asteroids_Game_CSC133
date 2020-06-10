package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class MissileLauncherPS extends MovableObject implements SteerableObject {
	
	MissileLauncherPS(double x, double y, int a){
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
		g.setColor(getcolor());
		int xt[] = new int[]{x+(int)getlocationx()-4,x+(int)getlocationx()+4,x+(int)getlocationx()+4,x+(int)getlocationx()-4};
		int yt[] = new int[]{y+(int)getlocationy()-30,y+(int)getlocationy()-30,y+(int)getlocationy()-10,y+(int)getlocationy()-10};
		g.fillPolygon(xt,yt,4);
		return;
	}

}

