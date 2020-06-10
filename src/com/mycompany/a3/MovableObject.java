package com.mycompany.a3;

public abstract class MovableObject extends GameObject implements IMovable {
	private int speed;
	private int azimuth;
	
	public int getspeed() {
		return speed;
	};
	public int getazimuth() {
		return azimuth;
	};
	public void setspeed(int s) {
		speed=s;
		return;
	}
	public void setazimuth(int a) {
		azimuth=a;
		return;
	}
}
