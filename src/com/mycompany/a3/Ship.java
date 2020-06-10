package com.mycompany.a3;

public abstract class Ship extends MovableObject {
	private int missilecount;
	
	public int getmissilecount() {
		return missilecount;
	}
	public void setmissilecount(int m) {
		missilecount=m;
		return;
	}
	abstract public void fire();
}
