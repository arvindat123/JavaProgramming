package org.collection;

public class SongV2 implements Comparable<SongV2> {
	private String title;
	private String artist;
	private int bpm;

	SongV2(String title, String artist, int bpm) {
		this.title = title;
		this.artist = artist;
		this.bpm = bpm;
	}

	public int compareTo(SongV2 s) {
		return title.compareTo(s.getTitle());
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getBpm() {
		return bpm;
	}

	public String toString() {
		return title;
	}
}
