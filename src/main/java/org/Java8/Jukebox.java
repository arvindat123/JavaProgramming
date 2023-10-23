package org.Java8;

import java.util.Iterator;
import java.util.List;

public class Jukebox {

	public static void main(String[] args) {
		new Jukebox().go();
	}
	
	void go() {
		List<String> songList = MockSongs.getSongStrings();
		System.out.println(songList);
		
		Iterator<String> iter = songList.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
