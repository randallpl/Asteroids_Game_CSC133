package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class SpaceStation extends FixedObject {
	private int blinkrate;
	private int shields;
	private int id;
	
	public SpaceStation(int xb, int yb) {
		Random rand = new Random();
		setlocationx((double)xb/2.0);
		setlocationy((double)yb/2.0);
		setcollisionradius(60);
		setcolor(ColorUtil.GRAY);
		id=getfid();
		setfid(getfid()+1);
		blinkrate=rand.nextInt(4)+1;
		shields=100;
	}
	public int getblinkrate() {
		return blinkrate;
	}
	public int getshields() {
		return shields;
	}
	public int getid() {
		return id;
	}
	public void setshields(int s) {
		if(s<=0) {
			shields=0;
			setcollisionradius(30);
		}
		else {
			shields=s;
		}
		return;
	}
	public String toString() {
		String s="Space Station: X="+Math.round(getlocationx()*10)/10.0+" Y="+Math.round(getlocationy()*10)/10.0+" Color=["+ColorUtil.red(getcolor())+","+ColorUtil.green(getcolor())+","+ColorUtil.blue(getcolor())+"] Blinkrate="+blinkrate+" Shield Strength= "+shields+" ID= "+getid();
		return s;
	};
	public void draw(int x, int y, Graphics g, int xa, int ya) {
		g.setColor(getcolor());
		int xup[] = new int[]{x+(int)getlocationx()-15,x+(int)getlocationx()+15,x+(int)getlocationx()+15,x+(int)getlocationx()+5,x+(int)getlocationx()+5,x+(int)getlocationx()-5,x+(int)getlocationx()-5,x+(int)getlocationx()-15};
		int yup[] = new int[]{y+(int)getlocationy()-52,y+(int)getlocationy()-52,y+(int)getlocationy()-41,y+(int)getlocationy()-41,y+(int)getlocationy()-20,y+(int)getlocationy()-20,y+(int)getlocationy()-41,y+(int)getlocationy()-41};
		int xlo[] = new int[]{x+(int)getlocationx()-15,x+(int)getlocationx()+15,x+(int)getlocationx()+15,x+(int)getlocationx()+5,x+(int)getlocationx()+5,x+(int)getlocationx()-5,x+(int)getlocationx()-5,x+(int)getlocationx()-15};
		int ylo[] = new int[]{y+(int)getlocationy()+52,y+(int)getlocationy()+52,y+(int)getlocationy()+41,y+(int)getlocationy()+41,y+(int)getlocationy()+20,y+(int)getlocationy()+20,y+(int)getlocationy()+41,y+(int)getlocationy()+41};
		g.fillArc(x+(int)getlocationx()-20,y+(int)getlocationy()-20,40,40,0,360);
		g.fillPolygon(xup,yup,8);
		g.fillPolygon(xlo,ylo,8);
		if(shields>0) {
			g.setColor(ColorUtil.BLUE);
			g.drawArc(x+(int)getlocationx()-60,y+(int)getlocationy()-60,120,120,0,360);
		}
		return;
	}
}
