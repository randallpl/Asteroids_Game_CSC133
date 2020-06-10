package com.mycompany.a3;

public interface ICollection {
	public abstract Iiterator getIterator();
	public abstract void spawn(GameObject o);
	public abstract void despawn(int o);
}
