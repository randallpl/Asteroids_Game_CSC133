package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class NonPlayerShip extends Ship {
	private int size;
	private int firerate;
	private MissileLauncherNPS ml;
	
	NonPlayerShip(int xb, int yb){
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
		setspeed(rand.nextInt(5)+2);
		size=rand.nextInt(8)+3;
		firerate=rand.nextInt(4)+2;
		setcollisionradius(size*4);
		setmissilecount(0);
		ml= new MissileLauncherNPS(getlocationx(),getlocationy(),rand.nextInt(360));
		ml.setlocationx(getlocationx());
		ml.setlocationy(getlocationy());
		setcolor(ColorUtil.MAGENTA);
		return;
	}
	int getsize() {
		return size;
	}
	int getfirerate() {
		return firerate;
	}
	public MissileLauncherNPS getml() {
		return ml;
	}
	public void fire() {
		return;
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
		ml.setlocationx(getlocationx());
		ml.setlocationy(getlocationy());
		return 0;
	}
	public String toString() {
		String s="NPS: X="+Math.round(getlocationx()*10)/10.0+" Y="+Math.round(getlocationy()*10)/10.0+" Color=["+ColorUtil.red(getcolor())+","+ColorUtil.green(getcolor())+","+ColorUtil.blue(getcolor())+"] Speed="+getspeed()+" Azimuth="+getazimuth()+" Size="+size+" Fire Rate="+getfirerate();
		return s;
	};
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		g.rotate((float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		g.setColor(getcolor());
		int xt[] = new int[]{x+(int)getlocationx(),x+(int)getlocationx()+2*size,x+(int)getlocationx()+4*size,x+(int)getlocationx()+4*size,x+(int)getlocationx()+2*size,x+(int)getlocationx()-2*size,x+(int)getlocationx()-4*size,x+(int)getlocationx()-4*size,x+(int)getlocationx()-2*size};
		int yt[] = new int[]{y+(int)getlocationy()-5*size,y+(int)getlocationy()+1*size,y+(int)getlocationy()-3*size,y+(int)getlocationy()+1*size,y+(int)getlocationy()+4*size,y+(int)getlocationy()+4*size,y+(int)getlocationy()+1*size,y+(int)getlocationy()-3*size,y+(int)getlocationy()+1*size};
		g.drawPolygon(xt,yt,9);
		g.rotate(-(float)Math.toRadians((double)getazimuth()),xa+(int)getlocationx(),ya+(int)getlocationy());
		return;
	}
}
