package com.mycompany.a3;

public abstract class FixedObject extends GameObject {
	private static int fid=0;

	public int getfid() {
		return fid;
	}
	public void setfid(int i) {
		fid=i;
		return;
	}
}
