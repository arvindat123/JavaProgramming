package org.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Jukebox2 {
	public static void main(String[] args) {
		new Jukebox2().go();
	}

	public void go() {
		List<SongV2> songList = MockSongs.getSongV2();

		System.out.println("===========Original List ===========" + songList);
		 Collections.sort(songList);//Compilation error if SongV2 doesn't implement
		// Comparable interface and implement compareTo

		Collections.sort(songList); // Sort on the basis of title because SongV2 implement compareTo on title
		System.out.println("===========Using Comparable - title sorting===========" + songList);

		ArtistCompare artistCompare = new ArtistCompare();
		songList.sort(artistCompare);
		System.out.println("===========Using Comparator - artist sorting===========" + songList);

		// Using inner class
		songList.sort(new Comparator<SongV2>() { // The sort is stable: this method must not reorder equal elements.
			public int compare(SongV2 title1, SongV2 title2) {
				return title1.getTitle().compareTo(title2.getTitle());// for reverse order exchange title 1 to title2
			}
		});
		System.out.println("===========Using Inner class - title sorting=========" + songList);

		songList.sort((one, two) -> one.getArtist().compareTo(two.getArtist()));
		System.out.println("Sorted using Lambda expression Artist" + songList);

		songList.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
		System.out.println("Sorted using Lambda expression title" + songList);

		System.out.println(songList);
		Set<SongV2> songSet = new HashSet<>(songList);
		System.out.println(songSet);

		Set<SongV2> songTreeSet = new TreeSet<>(songList);
		System.out.println("Tree set default Sorting ====" + songTreeSet);

		Set<SongV2> songTreeSetComparator = new TreeSet<>((a, b) -> a.getBpm() - b.getBpm());
		songTreeSetComparator.addAll(songList);
		System.out.println("Tree set customised using comparator==" + songList);

		List<SongV2> unmodifiableList = Collections.unmodifiableList(songList);
		System.out.println(unmodifiableList); // return last state list

	}
}

class ArtistCompare implements Comparator<SongV2> {
	public int compare(SongV2 artist1, SongV2 artist2) {
		return artist1.getArtist().compareTo(artist2.getArtist());
	}
}
