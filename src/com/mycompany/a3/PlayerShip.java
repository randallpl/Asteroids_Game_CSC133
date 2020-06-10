package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerShip extends Ship implements SteerableObject {
	private MissileLauncherPS ml;
	
	public PlayerShip(int xb, int yb)
	{
		setlocationx((double)xb/2.0);
		setlocationy((double)yb/2.0);
		setcollisionradius(18);
		setspeed(0);
		setazimuth(0);
		setmissilecount(10);
		setcolor(ColorUtil.GREEN);
		ml = new MissileLauncherPS(getlocationx(),getlocationy(),getazimuth());
		ml.setlocationx(getlocationx());
		ml.setlocationy(getlocationy());
		return;
	}
	public MissileLauncherPS getml() {
		return ml;
	}
	public void fire() {
		if(getmissilecount()==0) {
			return;
		}
		else {
			setmissilecount(getmissilecount()-1);
			return;
		}
	}
	public int move(int xb, int yb) {
		setlocationx(getlocationx()+(getspeed()*Math.sin(Math.PI*getazimuth()/180)/2.5));
		setlocationy(getlocationy()-(getspeed()*Math.cos(Math.PI*getazimuth()/180)/2.5));
		if(getlocationx()<0) {
			setlocationx(xb+getlocationx());
		}
		else if(getlocationx()>xb) {
			setlocationx(getlocationx()-xb);
		}
		else if(getlocationy()<0) {
			setlocationy(yb+getlocationy());
		}
		else if(getlocationy()>yb) {
			setlocationy(getlocationy()-yb);
		}
		ml.setlocationx(getlocationx());
		ml.setlocationy(getlocationy());
		return 0;
	}
	public String toString() {
		String s="Player Ship: X="+Math.round(getlocationx()*10)/10.0+" Y="+Math.round(getlocationy()*10)/10.0+" Color=["+ColorUtil.red(getcolor())+","+ColorUtil.green(getcolor())+","+ColorUtil.blue(getcolor())+"] Speed="+getspeed()+" Azimuth="+getazimuth()+" Missiles="+getmissilecount()+" MLAzimuth="+ml.getazimuth();
		return s;
	};
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		g.rotate((float)Math.toRadians((double)getml().getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		ml.draw(x,y,g,xa,ya);
		g.rotate(-(float)Math.toRadians((double)getml().getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		g.rotate((float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		g.setColor(getcolor());
		int xt[] = new int[]{x+(int)getlocationx(),x+(int)getlocationx()+13,x+(int)getlocationx()-13};
		int yt[] = new int[]{y+(int)getlocationy()-25,y+(int)getlocationy()+20,y+(int)getlocationy()+20};
		g.fillPolygon(xt,yt,3);
		g.rotate(-(float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		return;
	}
}
