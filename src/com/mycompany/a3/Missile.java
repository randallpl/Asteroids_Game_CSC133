package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Missile extends MovableObject implements ISelectable {
	private int fuel;
	private String owner;
	private boolean selected;
	
	public Missile(double x, double y, int a, String s) {
		setlocationx(x);
		setlocationy(y);
		setcollisionradius(10);
		if(s=="Player") {
			setspeed(15);
		}
		else {
			setspeed(8);
		}
		setazimuth(a);
		fuel=125;
		owner=s;
		setcolor(ColorUtil.LTGRAY);
		selected=false;
		return;
	}
	
	public int getfuel() {
		return fuel;
	};
	public String getowner() {
		return owner;
	}
	public void setfuel (int f) {
		fuel=f;
		return;
	};
	public int move(int xb, int yb) {
		setlocationx(getlocationx()+(getspeed()*Math.sin(Math.PI*getazimuth()/180)/2.5));
		setlocationy(getlocationy()-(getspeed()*Math.cos(Math.PI*getazimuth()/180)/2.5));
		if(getlocationx()<0) {
			return 1;
		}
		else if(getlocationx()>xb) {
			return 1;
		}
		else if(getlocationy()<0) {
			return 1;
		}
		else if(getlocationy()>yb) {
			return 1;
		}
		fuel--;
		return 0;
	}
	public boolean getselected() {
		return selected;		
	}
	public void setselected(boolean s) {
		selected=s;
		return;
	}
	public String toString() {
		String s="Missile: X="+Math.round(getlocationx()*10)/10.0+" Y="+Math.round(getlocationy()*10)/10.0+" Color=["+ColorUtil.red(getcolor())+","+ColorUtil.green(getcolor())+","+ColorUtil.blue(getcolor())+"] Speed="+getspeed()+" Azimuth="+getazimuth()+" Fuel="+fuel+" Owner="+owner;
		return s;
	}
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		g.rotate((float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		g.setColor(getcolor());
		int xt[] = new int[]{x+(int)getlocationx()-4,x+(int)getlocationx()+4,x+(int)getlocationx()+4,x+(int)getlocationx()-4};
		int yt[] = new int[]{y+(int)getlocationy()-6,y+(int)getlocationy()-6,y+(int)getlocationy()+10,y+(int)getlocationy()+10};
		g.fillPolygon(xt,yt,4);
		g.fillArc(x+(int)getlocationx()-5,y+(int)getlocationy()-14,9,16,0,180);
		g.rotate(-(float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		if(selected) {
			g.setColor(ColorUtil.rgb(255,0,0));
			g.drawArc(x+(int)getlocationx()-13,y+(int)getlocationy()-13,26,26,0,360);
		}
		return;
	}

}
