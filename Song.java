package SongLibView;

public class Song {
	public String songName;
	public String artist;
	public String album;
	public int year;
	
	public Song(String songName, String artist, String album, int year) {
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.year = year;		
	}
	
	public String toString() {
		return songName + ", " + artist + ", " + album + ", " + year;
	}
	
	public String outString() {
		return songName + "~" + artist + "~" + album + "~" + year;
	}
}
