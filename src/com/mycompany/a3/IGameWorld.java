package com.mycompany.a3;

public interface IGameWorld {
	public abstract void init(int xb, int yb);
	public abstract int gettime();
	public abstract int getscore();
	public abstract int getlives();
	public abstract boolean getsound();
	public abstract Iiterator getIterator();
	public abstract boolean getgameover();
	public abstract boolean getispaused();
	public abstract void settime(int t);
	public abstract void setscore(int s);
	public abstract void setlives(int l);
	public abstract void setsound(boolean s);
	public abstract void setispaused(boolean s);
	public abstract void addasteroid();
	public abstract void addnonplayership();
	public abstract void addspacestation();
	public abstract void addplayership();
	public abstract void speedup();
	public abstract void slowdown();
	public abstract void port();
	public abstract void starboard();
	public abstract void portml();
	public abstract void starboardml();
	public abstract void firemissile();
	public abstract void firenonplayermissile();
	public abstract void jumpdrive();
	public abstract void reload();
	public abstract void tick();
	public abstract void newgame();
	public abstract void pause();
	public abstract void resume();
	public abstract void refuelselected();
}
