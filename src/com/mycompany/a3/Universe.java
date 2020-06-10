package com.mycompany.a3;

import java.util.ArrayList;

public class Universe implements ICollection {
	protected ArrayList<GameObject> list;

	public Universe() {
		list=new ArrayList<GameObject>();
	}
	
	public void spawn(GameObject o) {
		list.add(o);
		return;
	}
	public void spawn(int d, GameObject o) {
		list.add(d,o);
		return;
	}
	public void despawn(int o) {
		list.remove(o);
	}
	public Iiterator getIterator() {
		return new UniverseIterator();
	}

	private class UniverseIterator implements Iiterator{
		private int currentindex;
		
		public UniverseIterator() {
			currentindex=-1;
		}
		
		public int getcurrentindex() {
			return currentindex;
		}
		public boolean hasNext() {
			if(currentindex<(list.size()-1)) {
				return true;
			}
			else {
				return false;
			}
		}
		public GameObject getNext() {
			currentindex++;
			return list.get(currentindex);
		}
	}
}
