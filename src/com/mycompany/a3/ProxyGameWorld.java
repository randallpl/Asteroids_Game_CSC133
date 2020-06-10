package com.mycompany.a3;

import java.util.Observable;

public class ProxyGameWorld extends Observable implements IGameWorld {
	private GameWorld gw;
	
	ProxyGameWorld(GameWorld g){
		gw=g;
	}
	public void init(int xb, int yb) {
		return;
	}
	public int gettime() {
		return gw.gettime();
	}
	public int getscore() {
		return gw.getscore();
	}
	public int getlives() {
		return gw.getlives();
	}
	public boolean getsound() {
		return gw.getsound();
	}
	public Iiterator getIterator() {
		return gw.getIterator();
	}
	public boolean getgameover() {
		return gw.getgameover();
	}
	public boolean getispaused() {
		return gw.getispaused();
	}
	public void settime(int t) {
		return;
	}
	public void setscore(int s) {
		return;
	}
	public void setlives(int l) {
		return;
	}
	public void setsound(boolean s) {
		return;
	}
	public void setispaused(boolean s) {
		return;
	}
	public void addasteroid() {
		return;
	}
	public void addnonplayership() {
		return;
	}
	public void addspacestation() {
		return;
	}
	public void addplayership() {
		return;
	}
	public void speedup() {
		return;
	}
	public void slowdown() {
		return;
	}
	public void port() {
		return;
	}
	public void starboard() {
		return;
	}
	public void portml() {
		return;
	}
	public void starboardml() {
		return;
	}
	public void firemissile() {
		return;
	}
	public void firenonplayermissile() {
		return;
	}
	public void jumpdrive() {
		return;
	}
	public void reload() {
		return;
	}
	public void tick() {
		return;
	}
	public void newgame() {
		return;
	}
	public void pause() {
		return;
	}
	public void resume() {
		return;
	}
	public void refuelselected() {
		return;
	}
}
