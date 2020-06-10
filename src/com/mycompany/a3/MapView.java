package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	ProxyGameWorld proxy;
	
	MapView(){
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
	}

	public void update(Observable observable, Object data) {
		proxy=(ProxyGameWorld)data;
		this.repaint();
		return;
	}
	public void pointerPressed(int x, int y) {
		if(proxy.getispaused()) {
			x=x-getAbsoluteX();
			y=y-getAbsoluteY();
			Iiterator i = proxy.getIterator();
			while(i.hasNext()) {
				GameObject o = i.getNext();
				if(o instanceof ISelectable) {
					ISelectable s = (ISelectable)o;
					s.setselected(false);
					if(x<=o.getlocationx()+10 && y<=o.getlocationy()+10 && x>=o.getlocationx()-10 && y>=o.getlocationy()-10) {
						s.setselected(true);
					}
				}
			}
			this.repaint();
		}
		return;
	}
	public void paint(Graphics g) {
		super.paint(g);
		Iiterator i = proxy.getIterator();
		while(i.hasNext()) {
			IDrawable o = i.getNext();
			o.draw(getX(),getY(),g,getAbsoluteX(),getAbsoluteY());
		}
		g.resetAffine();
		return;
	}
}
