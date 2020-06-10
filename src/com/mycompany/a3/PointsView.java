package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer {
	private Label timelabel;
	private Label soundlabel;
	private Label pointslabel;
	private Label liveslabel;
	private Label missilelabel;
	private Label shieldlabel;
	
	PointsView(){
		timelabel = new Label("");
		soundlabel = new Label("");
		pointslabel = new Label("");
		liveslabel = new Label("");
		missilelabel = new Label("");
		shieldlabel = new Label("");
		timelabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		soundlabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pointslabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		liveslabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		missilelabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		shieldlabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		Container c = new Container();
		c.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		timelabel.setText("Time: "+0);
		c.add(timelabel);
		soundlabel.setText("Sound: ON");
		c.add(soundlabel);
		pointslabel.setText("Points: "+0);
		c.add(pointslabel);
		liveslabel.setText("Lives: "+3);
		c.add(liveslabel);
		missilelabel.setText("Missiles: "+10);
		c.add(missilelabel);
		shieldlabel.setText("Station Shields: "+100);
		c.add(shieldlabel);
		this.add(c);
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
	}

	public void update(Observable observable, Object data) {
		ProxyGameWorld g = (ProxyGameWorld)data;
		Iiterator i = g.getIterator();
		Iiterator j = g.getIterator();
		timelabel.setText("Time: "+g.gettime()/25);
		if(g.getsound()==true) {
			soundlabel.setText("Sound: ON");
		}
		else {
			soundlabel.setText("Sound: OFF");
		}
		pointslabel.setText("Points: "+g.getscore());
		liveslabel.setText("Lives: "+g.getlives());
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				missilelabel.setText("Missiles: "+ps.getmissilecount());
				while(j.hasNext()) {
					GameObject p = j.getNext();
					if(p instanceof SpaceStation) {
						SpaceStation ss = (SpaceStation)p;
						shieldlabel.setText("Station Shields: "+ss.getshields());
						this.repaint();
						return;
					}
				}
				this.repaint();
				return;
			}
		}
		missilelabel.setText("Missile: "+0);
		this.repaint();
		return;
	}
}
