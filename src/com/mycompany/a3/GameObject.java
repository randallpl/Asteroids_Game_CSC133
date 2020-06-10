package com.mycompany.a3;

public abstract class GameObject implements IDrawable{
	private double locationx;
	private double locationy;
	private double collisionradius;
	private int color;
	
	public double getlocationx() {
		return locationx;
	}
	public double getlocationy() {
		return locationy;
	}
	public double getcollisionradius() {
		return collisionradius;
	}
	public int getcolor() {
		return color;
	}
	public void setlocationx(double x) {
		locationx=x;
		return;
	}
	public void setlocationy(double y) {
		locationy=y;
		return;
	}
	public void setcollisionradius(double c) {
		collisionradius=c;
		return;
	}
	public void setcolor(int c) {
		color=c;
		return;
	}
}
