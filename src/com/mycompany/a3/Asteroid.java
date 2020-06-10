package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Asteroid extends MovableObject implements ISelectable{
	private int size;
	private boolean selected;
	
	public Asteroid(int xb, int yb) {
		Random rand = new Random();
		int spawnfacing=rand.nextInt(4);
		if(spawnfacing==0) {
			setlocationx(rand.nextDouble()*xb);
			setlocationy(0);
			setazimuth(rand.nextInt(60)+150);
		}
		else if(spawnfacing==1) {
			setlocationx(xb);
			setlocationy(rand.nextDouble()*yb);
			setazimuth(rand.nextInt(60)+240);
		}
		else if(spawnfacing==2) {
			setlocationx(rand.nextDouble()*xb);
			setlocationy(yb);
			int temp=rand.nextInt(60);
			if(temp<30) {
				setazimuth(359-temp);
			}
			else {
				setazimuth(temp-30);
			}
		}
		else if(spawnfacing==3) {
			setlocationx(0);
			setlocationy(rand.nextDouble()*yb);
			setazimuth(rand.nextInt(60)+60);
		}
		setspeed(rand.nextInt(6)+1);
		size=rand.nextInt(18)+3;
		setcollisionradius(size*2);
		setcolor(ColorUtil.WHITE);
		selected=false;
		return;
	}
	
	public int getsize() {
		return size;
	}
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
		String s="Asteroid: X="+Math.round(getlocationx()*10)/10.0+" Y="+Math.round(getlocationy()*10)/10.0+" Color=["+ColorUtil.red(getcolor())+","+ColorUtil.green(getcolor())+","+ColorUtil.blue(getcolor())+"] Speed="+getspeed()+" Azimuth="+getazimuth()+" Size="+size;
		return s;
	}
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		g.setColor(getcolor());
		g.drawArc(x+(int)getlocationx()-size*2,y+(int)getlocationy()-size*2,size*4,size*4,0,360);
		if(selected) {
			g.setColor(ColorUtil.rgb(255,0,0));
			g.drawArc(x+(int)getlocationx()-size*3,y+(int)getlocationy()-size*3,size*6,size*6,0,360);
		}
		return;
	}
}
