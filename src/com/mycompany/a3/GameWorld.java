package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

public class GameWorld extends Observable implements IGameWorld {
	private int time;
	private int score;
	private int lives;
	private boolean sound;
	private Universe worldlist;
	private int yboundry;
	private int xboundry;
	private BGSound bgm;
	private Vector<Sound> sounds;
	private boolean gameover;
	private boolean ispaused;
	
	GameWorld(){
		time=0;
		score=0;
		lives=3;
		sound=true;
		worldlist = new Universe();
		sounds = new Vector<Sound>();
		gameover=false;
		ispaused=false;
	}
	
	public void init(int xb, int yb) {
		xboundry=xb;
		yboundry=yb;
		worldlist.spawn(new PlayerShip(xboundry,yboundry));
		worldlist.spawn(new SpaceStation(xboundry,yboundry));
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		bgm = new BGSound("BG.wav");
		Sound missile = new Sound("Missile.wav");
		sounds.add(missile);
		Sound missileimpact = new Sound("MissileImpact.wav");
		sounds.add(missileimpact);
		Sound collision = new Sound("Collision.wav");
		sounds.add(collision);
		Sound shieldimpact = new Sound("ShieldImpact.wav");
		sounds.add(shieldimpact);
		Sound shieldbreak = new Sound("ShieldBreak.wav");
		sounds.add(shieldbreak);
		Sound gameover = new Sound("GameOver.wav");
		sounds.add(gameover);
		bgm.play();
		return;
	}
	public int gettime() {
		return time;
	}
	public int getscore() {
		return score;
	}
	public int getlives() {
		return lives;
	}
	public boolean getsound() {
		return sound;
	}
	public Iiterator getIterator() {
		return worldlist.getIterator();
	}
	public boolean getgameover() {
		return gameover;
	}
	public boolean getispaused() {
		return ispaused;
	}
	public void settime(int t) {
		time=t;
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void setscore(int s) {
		score=s;
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void setlives(int l) {
		lives=l;
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void setsound(boolean s) {
		sound=s;
		if(sound==true && ispaused==false) {
			bgm.play();
		}
		else if(sound==false && ispaused==false){
			bgm.pause();
		}
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void setispaused(boolean b) {
		ispaused=b;
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void addasteroid() {
		worldlist.spawn(new Asteroid(xboundry,yboundry));
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void addnonplayership() {
		worldlist.spawn(new NonPlayerShip(xboundry,yboundry));
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void addspacestation() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof SpaceStation) {
				System.out.println("Cannot spawn multiple Space Stations!");
				return;
			}
		}
		worldlist.spawn(new SpaceStation(xboundry,yboundry));
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void addplayership() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				System.out.println("Cannot spawn multiple Player Ships!");
				return;
			}
		}
		worldlist.spawn(0,new PlayerShip(xboundry,yboundry));
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void speedup() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getspeed()==0) {
					ps.setspeed(2);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==2) {
					ps.setspeed(4);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==4) {
					ps.setspeed(6);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==6) {
					ps.setspeed(10);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void slowdown() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getspeed()==10) {
					ps.setspeed(6);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==6) {
					ps.setspeed(4);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==4) {
					ps.setspeed(2);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				else if(ps.getspeed()==2) {
					ps.setspeed(0);
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void port() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getazimuth()==0) {
					ps.setazimuth(358);
				}
				else {
					ps.setazimuth(ps.getazimuth()-2);
				}
				if(ps.getml().getazimuth()==0) {
					ps.getml().setazimuth(358);
				}
				else {
					ps.getml().setazimuth(ps.getml().getazimuth()-2);
				}
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void starboard() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getazimuth()==358) {
					ps.setazimuth(0);
				}
				else {
					ps.setazimuth(ps.getazimuth()+2);
				}
				if(ps.getml().getazimuth()==358) {
					ps.getml().setazimuth(0);
				}
				else {
					ps.getml().setazimuth(ps.getml().getazimuth()+2);
				}
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void portml() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getml().getazimuth()==0) {
					ps.getml().setazimuth(358);
				}
				else {
					ps.getml().setazimuth(ps.getml().getazimuth()-2);
				}
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void starboardml() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getml().getazimuth()==358) {
					ps.getml().setazimuth(0);
				}
				else {
					ps.getml().setazimuth(ps.getml().getazimuth()+2);
				}
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void firemissile() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				if(ps.getmissilecount()<=0) {
					System.out.println("No more missiles!");
				}
				else {
					ps.fire();
					if(sound==true) {
						sounds.get(0).play();
					}
					worldlist.spawn(new Missile(ps.getlocationx(),ps.getlocationy(),ps.getml().getazimuth(),"Player"));
					setChanged();
					notifyObservers(new ProxyGameWorld(this));
				}
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void firenonplayermissile() {
		boolean flag=false;
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof NonPlayerShip) {
				NonPlayerShip nps = (NonPlayerShip)o;
				nps.fire();
				worldlist.spawn(new Missile(nps.getlocationx(),nps.getlocationy(),nps.getml().getazimuth(),"NPS"));
				flag=true;
			}
		}
		if(flag==false) {
			System.out.println("No Non Player Ships!");
		}
		else {
			setChanged();
			notifyObservers(new ProxyGameWorld(this));
		}
		return;
	}
	public void jumpdrive() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				ps.setlocationx(xboundry/2);
				ps.setlocationy(yboundry/2);
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void reload() {
		Iiterator i = worldlist.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip)o;
				ps.setmissilecount(10);
				setChanged();
				notifyObservers(new ProxyGameWorld(this));
				return;
			}
		}
		System.out.println("No Player Ship!");
		return;
	}
	public void tick() {
		int a=-1;
		Random rand = new Random();
		Boolean flag = false;
		Iiterator i = worldlist.getIterator();
		Iiterator j = worldlist.getIterator();
		Iiterator k = worldlist.getIterator();
		Iiterator l = worldlist.getIterator();
		Iiterator p = worldlist.getIterator();
		Stack<Integer> oof = new Stack<Integer>();
		Stack<Integer> oob = new Stack<Integer>();
		time++;
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof Missile) {
				Missile m = (Missile)o;
				if(m.getfuel()<=0) {
					oof.push(i.getcurrentindex());
				}
			}
		}
		while(!oof.isEmpty()) {
			a=oof.pop();
			worldlist.despawn(a);
		}
		while(j.hasNext()) {
			GameObject o = j.getNext();
			if(o instanceof IMovable) {
				MovableObject m =(MovableObject)o;
				if(m.move(xboundry,yboundry)==1) {
					oob.push(j.getcurrentindex());
				}
			}
		}
		while(!oob.isEmpty()) {
			a=oob.pop();
			worldlist.despawn(a);
		}
		while(k.hasNext()) {
			GameObject o = k.getNext();
			if(o instanceof SpaceStation) {
				SpaceStation s = (SpaceStation)o;
				if(time%(s.getblinkrate()*25)==0) {
					if(s.getcolor()==ColorUtil.GRAY) {
						s.setcolor(ColorUtil.rgb(55,55,55));
					}
					else {
						s.setcolor(ColorUtil.GRAY);
					}
				}
			}
		}
		while(l.hasNext()) {
			GameObject o = l.getNext();
			if(o instanceof NonPlayerShip) {
				NonPlayerShip n = (NonPlayerShip)o;
				if(time%(n.getfirerate()*25)==0) {
					n.fire();
					worldlist.spawn(new Missile(n.getlocationx(),n.getlocationy(),n.getml().getazimuth(),"NPS"));
				}
			}
		}
		if(time<=1500) {
			a=rand.nextInt(1000)+1;
			if(a<=8) {
				addasteroid();
			}
		}
		else if(time<=3000) {
			a=rand.nextInt(1000)+1;
			if(a<=12) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=4) {
				addnonplayership();
			}
		}
		else if(time<=4500) {
			a=rand.nextInt(1000)+1;
			if(a<=15) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=8) {
				addnonplayership();
			}
		}
		else if(time<=6000) {
			a=rand.nextInt(1000)+1;
			if(a<=20) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=10) {
				addnonplayership();
			}
		}
		else if(time<=7500) {
			a=rand.nextInt(1000)+1;
			if(a<=40) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=12) {
				addnonplayership();
			}
		}
		else if(time<=9000) {
			a=rand.nextInt(1000)+1;
			if(a<=80) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=15) {
				addnonplayership();
			}
		}
		else if(time<=10500) {
			a=rand.nextInt(1000)+1;
			if(a<=160) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=20) {
				addnonplayership();
			}
		}
		else if(time<=12000) {
			a=rand.nextInt(1000)+1;
			if(a<=320) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=40) {
				addnonplayership();
			}
		}
		else if(time<=13500) {
			a=rand.nextInt(1000)+1;
			if(a<=640) {
				addasteroid();
			}
			a=rand.nextInt(1000)+1;
			if(a<=80) {
				addnonplayership();
			}
		}
		else {
			addasteroid();
			a=rand.nextInt(1000)+1;
			if(a<=160) {
				addnonplayership();
			}
		}
		while(p.hasNext()) {
			GameObject o = p.getNext();
			Iiterator q = worldlist.getIterator();
			int counter = p.getcurrentindex();
			for(int c=0;c<=counter;c++) {
				q.getNext();
			}
			while(q.hasNext()) {
				GameObject b = q.getNext();
				if(o instanceof PlayerShip) {
					if(b instanceof SpaceStation) {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							reload();
						}
					}
					else if(b instanceof NonPlayerShip) {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							worldlist.despawn(q.getcurrentindex());
							worldlist.despawn(p.getcurrentindex());
							lives--;
							if(sound==true) {
								sounds.get(2).play();
							}
							worldlist.spawn(0,new PlayerShip(xboundry,yboundry));
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else if(b instanceof Missile) {
						Missile m = (Missile)b;
						if(((o.getlocationx()-m.getlocationx())*(o.getlocationx()-m.getlocationx()))+((o.getlocationy()-m.getlocationy())*(o.getlocationy()-m.getlocationy()))<=(o.getcollisionradius()+m.getcollisionradius())*(o.getcollisionradius()+m.getcollisionradius()) && m.getowner()!="Player") {
							int temp = q.getcurrentindex();
							int c = 0;
							worldlist.despawn(q.getcurrentindex());
							worldlist.despawn(p.getcurrentindex());
							if(sound==true) {
								sounds.get(1).play();
							}
							lives--;
							worldlist.spawn(0,new PlayerShip(xboundry,yboundry));
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							worldlist.despawn(q.getcurrentindex());
							worldlist.despawn(p.getcurrentindex());
							lives--;
							if(sound==true) {
								sounds.get(2).play();
							}
							worldlist.spawn(0,new PlayerShip(xboundry,yboundry));
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
				}
				else if(o instanceof SpaceStation) {
					if(b instanceof NonPlayerShip) {
						SpaceStation ss = (SpaceStation)o;
						NonPlayerShip ns = (NonPlayerShip)b;
						if(((ss.getlocationx()-ns.getlocationx())*(ss.getlocationx()-ns.getlocationx()))+((ss.getlocationy()-ns.getlocationy())*(ss.getlocationy()-ns.getlocationy()))<=(ss.getcollisionradius()+ns.getcollisionradius())*(ss.getcollisionradius()+ns.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							if(ss.getshields()==0) {
								gameover=true;
							}
							worldlist.despawn(q.getcurrentindex());
							if(sound==true && ss.getshields()==0) {
								sounds.get(2).play();
							}
							else if(sound==true) {
								sounds.get(3).play();
							}
							ss.setshields(ss.getshields()-ns.getsize());
							if(ss.getshields()==0 && !gameover) {
								sounds.get(4).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else if(b instanceof Missile) {
						SpaceStation ss = (SpaceStation)o;
						Missile m = (Missile)b;
						if(((ss.getlocationx()-m.getlocationx())*(ss.getlocationx()-m.getlocationx()))+((ss.getlocationy()-m.getlocationy())*(ss.getlocationy()-m.getlocationy()))<=(ss.getcollisionradius()+m.getcollisionradius())*(ss.getcollisionradius()+m.getcollisionradius()) && m.getowner()!="Player") {
							int temp = q.getcurrentindex();
							int c = 0;
							if(ss.getshields()==0) {
								gameover=true;
							}
							worldlist.despawn(q.getcurrentindex());
							if(sound==true && ss.getshields()==0) {
								sounds.get(1).play();
							}
							else if(sound==true) {
								sounds.get(3).play();
							}
							ss.setshields(ss.getshields()-10);
							if(ss.getshields()==0 && !gameover) {
								sounds.get(4).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else {
						SpaceStation ss = (SpaceStation)o;
						Asteroid as = (Asteroid)b;
						if(((ss.getlocationx()-as.getlocationx())*(ss.getlocationx()-as.getlocationx()))+((ss.getlocationy()-as.getlocationy())*(ss.getlocationy()-as.getlocationy()))<=(ss.getcollisionradius()+as.getcollisionradius())*(ss.getcollisionradius()+as.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							if(ss.getshields()==0) {
								gameover=true;
							}
							worldlist.despawn(q.getcurrentindex());
							if(sound==true && ss.getshields()==0) {
								sounds.get(2).play();
							}
							else if(sound==true) {
								sounds.get(3).play();
							}
							ss.setshields(ss.getshields()-as.getsize());
							if(ss.getshields()==0 && !gameover) {
								sounds.get(4).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
				}
				else if(o instanceof NonPlayerShip) {
					if(b instanceof NonPlayerShip) {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(2).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else if(b instanceof Missile) {
						Missile m = (Missile)b;
						if(((o.getlocationx()-m.getlocationx())*(o.getlocationx()-m.getlocationx()))+((o.getlocationy()-m.getlocationy())*(o.getlocationy()-m.getlocationy()))<=(o.getcollisionradius()+m.getcollisionradius())*(o.getcollisionradius()+m.getcollisionradius()) && m.getowner()!="NPS") {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(1).play();
							}
							score=score+3;
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(2).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
				}
				else if(o instanceof Missile) {
					if(b instanceof NonPlayerShip) {
						Missile m = (Missile)o;
						if(((m.getlocationx()-b.getlocationx())*(m.getlocationx()-b.getlocationx()))+((m.getlocationy()-b.getlocationy())*(m.getlocationy()-b.getlocationy()))<=(m.getcollisionradius()+b.getcollisionradius())*(m.getcollisionradius()+b.getcollisionradius()) && m.getowner()!="NPS") {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(1).play();
							}
							score=score+3;
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else if(b instanceof Missile) {
						Missile m = (Missile)o;
						Missile n = (Missile)b;
						if(((m.getlocationx()-n.getlocationx())*(m.getlocationx()-n.getlocationx()))+((m.getlocationy()-n.getlocationy())*(m.getlocationy()-n.getlocationy()))<=(m.getcollisionradius()+n.getcollisionradius())*(m.getcollisionradius()+n.getcollisionradius()) && m.getowner()!=n.getowner()) {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(1).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else {
						Missile m = (Missile)o;
						if(((m.getlocationx()-b.getlocationx())*(m.getlocationx()-b.getlocationx()))+((m.getlocationy()-b.getlocationy())*(m.getlocationy()-b.getlocationy()))<=(m.getcollisionradius()+b.getcollisionradius())*(m.getcollisionradius()+b.getcollisionradius()) && m.getowner()!="NPS") {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							score=score+1;
							if(sound==true) {
								sounds.get(1).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
				}
				else {
					if(b instanceof NonPlayerShip) {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(2).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else if(b instanceof Missile) {
						Missile m = (Missile)b;
						if(((o.getlocationx()-m.getlocationx())*(o.getlocationx()-m.getlocationx()))+((o.getlocationy()-m.getlocationy())*(o.getlocationy()-m.getlocationy()))<=(o.getcollisionradius()+m.getcollisionradius())*(o.getcollisionradius()+m.getcollisionradius()) && m.getowner()!="NPS") {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							score=score+1;
							if(sound==true) {
								sounds.get(1).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
					else {
						if(((o.getlocationx()-b.getlocationx())*(o.getlocationx()-b.getlocationx()))+((o.getlocationy()-b.getlocationy())*(o.getlocationy()-b.getlocationy()))<=(o.getcollisionradius()+b.getcollisionradius())*(o.getcollisionradius()+b.getcollisionradius())) {
							int temp = q.getcurrentindex();
							int c = 0;
							flag=true;
							worldlist.despawn(q.getcurrentindex());
							if(sound==true) {
								sounds.get(2).play();
							}
							q = worldlist.getIterator();
							while(q.hasNext() && c<temp) {
								q.getNext();
								c++;
							}
						}
					}
				}
			}
			if(flag==true) {
				flag=false;
				int temp = p.getcurrentindex();
				int c = 0;
				worldlist.despawn(p.getcurrentindex());
				p = worldlist.getIterator();
				while(p.hasNext() || c<temp) {
					p.getNext();
					c++;
				}
			}
		}
		if(lives==0) {
			gameover=true;
		}
		if(gameover) {
			bgm.pause();
			sounds.get(5).play();
			Boolean bOk = Dialog.show(
		            "GAME OVER",
		            "Your final score was: "+score,
		            "Quit",
		            "New Game"
		        );
		        if (bOk) {
		            Display.getInstance().exitApplication();
		            return;
		        }else {
		        	sounds.get(5).pause();
		        	newgame();
		        	return;
		        }
		}
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
	}
	public void newgame() {
		time=0;
		score=0;
		lives=3;
		worldlist = new Universe();
		gameover=false;
		worldlist.spawn(new PlayerShip(xboundry,yboundry));
		worldlist.spawn(new SpaceStation(xboundry,yboundry));
		if(sound==true && ispaused==false) {
			bgm.play();
		}
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void pause() {
		if(sound==true) {
			bgm.pause();
		}
		ispaused=true;
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void resume() {
		if(sound==true) {
			bgm.play();
		}
		ispaused=false;
		Iiterator i = getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof ISelectable) {
				ISelectable s = (ISelectable)o;
				s.setselected(false);
			}
		}
		setChanged();
		notifyObservers(new ProxyGameWorld(this));
		return;
	}
	public void refuelselected() {
		Iiterator i = getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o instanceof ISelectable) {
				ISelectable s = (ISelectable)o;
				if(s.getselected()==true && s instanceof Missile) {
					Missile m = (Missile)s;
					m.setfuel(125);
				}
			}
		}
	}
}
