// Aamna Aamir - 501153037

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
  	private ArrayList<Podcast> 		podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	// accessor method for error message 
	public String getErrorMessage()
	{
		return errorMsg;
	}

	// constructor method for class 
	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); ;
	}


	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{	

		// if audiobook
		if (content.getType().equals(AudioBook.TYPENAME)) {
			// casting content to be a audiobook object
			AudioBook c= (AudioBook) content;
			
			// checking to see if audiobook already in list
			for (int i=0; i<audiobooks.size(); i++) {
				if (audiobooks.get(i).equals(c)) {
					errorMsg = "AudioBook already downloaded";
					return false;
				}	
			}

			//adding audiobook to list bc its not already in list
			audiobooks.add(c);
			return true;
		

		// if song 
		} else if (content.getType().equals(Song.TYPENAME)) {

			// casting content to be a song object
			Song s = (Song) content;
			
			// checking to see if song already in list 
			for (int i=0; i<songs.size(); i++) {
				if (songs.get(i).equals(s)) {
					errorMsg = "Song already downloaded";
					return false;
				}
			}

			// adding song to list bc its not already in list
			songs.add(s);
			return true;

		
		// if podcast
		} else if (content.getType().equals(Podcast.TYPENAME)) {

			// casting content to be a podcast object
			Podcast p = (Podcast) content;
			
			// checking to see if podcast already in list 
			for (int i=0; i<podcasts.size(); i++) {
				if (podcasts.get(i).equals(p)) {
					errorMsg = "Podcast already downloaded";
					return false;
				}
			}

			// adding podcast to list bc its not already in list
			podcasts.add(p);
			return true;
		}

		return false;
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i=0; i<audiobooks.size(); i++ ) {
			System.out.print("" + (i+1) + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
  	
	public void listAllPodcasts()
	{
		for (int i=0; i<podcasts.size(); i++ ){
			System.out.print("" + (i+1) + ". ");
			podcasts.get(i).printInfo();
			System.out.println();

		}
	}
	

  	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i=0; i<playlists.size(); i++ ){
			System.out.print("" + (i+1) + ". " + playlists.get(i).getTitle());
			System.out.println();

		}
	}

	// extra:  method to check if the string (artist) is already in an arraylist (songs)
	public boolean containsString(String j, ArrayList<String> al) {
		for (int i=0; i < al.size(); i++) {
			if (al.get(i).equals(j)) return true;
		}
		return false;
	}

  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		ArrayList <String> allArtists = new ArrayList <String>();
		// Go through the songs array list and add the artist name to the new arraylist only if it is not already there.
		for (int i=0; i<songs.size(); i++) {
			if (! containsString(songs.get(i).getArtist(), allArtists)) {
				allArtists.add(songs.get(i).getArtist());
			}
		}

		//  Once the artist arraylist is complete, print the artists names
		for (int i=0; i < allArtists.size(); i++) {
			System.out.println((i+1) + ". " + allArtists.get(i));
		}
	}


	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		// checking if range of index if valid
		if (index >0 && index<= songs.size()) {

			// song to be deleted 
			Song songToDel = songs.get(index-1);

			// going through all playlists and checking to see if songToDel exists in them
			for (int i=0; i<playlists.size(); i++){

				// contents in the current playlist
				ArrayList<AudioContent>  contents = playlists.get(i).getContent();

				// going through all contents in current playlist to see if it matches songToDel
				for (int j=0; j< contents.size(); j++) {
					if ((contents.get(j).getType().equals(Song.TYPENAME)) && contents.get(j).equals(songToDel)) {
						// if song found, then removing them from those playlists
						playlists.get(i).deleteContent(j+1);
					}
				}
				
			}	
			
			// deleting song from song Arraylist 
			songs.remove(index-1);
			

			return true;
		}

		// if index was not valid
		errorMsg= "Song Not Found";
		System.out.println(errorMsg);
		return false;
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	}

  	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b) {
			if (a.getYear() > b.getYear()) return 1;
			if (a.getYear() < b.getYear()) return -1;
			return 0;

		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
		Collections.sort(songs, new SongLengthComparator());
	}

  	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b) {
			if (a.getLength() > b.getLength()) return 1;
			if (a.getLength() < b.getLength()) return -1;
			return 0;

		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		// if index is not in range
		if (index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			System.out.println(errorMsg);
			return false;
		}

		// otherwise, play the song at index -1 
		songs.get(index-1).play();
		return true;
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		if (index >0 && index <= podcasts.size()) {
			
			// selecting season and episode
			podcasts.get(index-1).selectEP(episode);
			podcasts.get(index-1).selectSeason(season);

			// playing podcast
			podcasts.get(index-1).play();
			
			return true;
		}

		// if index is not in range
		errorMsg= "Podcast Not Found";
		System.out.println(errorMsg);
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		if (index >0 && index <= podcasts.size() && season>0 && season<= podcasts.get(index-1).numSeasons()) {
			
			// selecting season 
			podcasts.get(index-1).selectSeason(season);

			// printing podcasts episodes for current seaons
			podcasts.get(index-1).printPod();
			
			return true;
		}

		// if index is not in range
		errorMsg= "Podcast Not Found";
		System.out.println(errorMsg);
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{	
		if (index > 0 && index <= audiobooks.size() && ((chapter> 0 && chapter <= audiobooks.get(index-1).getNumberOfChapters()))) {
			
			// selecting current chapter for audio book at index-1
			audiobooks.get(index-1).selectChapter(chapter);

			// playing the chapter for book at index-1
			audiobooks.get(index-1).play();

			return true;
		}

		// if index is not within range
		errorMsg = "AudioBook Not Found";
		System.out.println(errorMsg);
		return false;
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		// printing chapter titles for audiobook at index-1 if index is within range
		if (index >0 && index <= audiobooks.size()) {
			audiobooks.get(index-1).printTOC();
			return true;
		}

		// if index is not within range
		errorMsg = "AudioBook Not Found";
		System.out.println(errorMsg);
		return false;
	}
	
  /*
   * Playlist Related Methods
   */
	
   // extra: method to check if a playlist title is in the playlist array
	public boolean checkPlaylist (String title) {
		for (int i=0; i<playlists.size(); i++) {
			if (playlists.get(i).getTitle().equals(title)) {
				return true;
			}
		}
		return false;
	}

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{
		// checking if title is already used for another playlist and setting error
		if (checkPlaylist(title)) {
			errorMsg= "Playlist " + title + " Already Exists";
			System.out.println(errorMsg);
			return false;
		}
		
		// creating new playlist & adding the playlist with the input title to playlists arraylist
		Playlist addition= new Playlist(title);
		playlists.add(addition);
		return true;
		
	}
	
	// extra: method to return index of where title playlist is found in playlist arraylist
	public int playlistIndex(String title) {
		for (int i=0; i<playlists.size(); i++) {
			if (playlists.get(i).getTitle().equals(title)) {
				return i;
			}
		}

		return -1;
	}

	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		// check if title playlist is in arraylist
		if (checkPlaylist(title)) {
			// get index of playlist named "title" by using playlistIndex() and print contents
			playlists.get(playlistIndex(title)).printContents();
			return true;
		}
		
		// error message bc playlist doesn't exist otherwise
		errorMsg= "Playlist " + title + " Does Not Exist";
		System.out.println(errorMsg);
		return false;
	}
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		// checking if title playlist title exists
		if (checkPlaylist(playlistTitle)) {

			// getting index of title playlist in the arraylist
			int index = playlistIndex(playlistTitle);

			// playing all content
			playlists.get(index).playAll();
			return true;
		}

		// when title playlist doesn't exist
		errorMsg= "Playlist " + playlistTitle + " Does Not Exist";
		System.out.println(errorMsg);
		return false;
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)
	{
		// checking if playlist exists 
		if (checkPlaylist(playlistTitle)) {

			System.out.println(playlistTitle);
			// getting index of playlist names "playlistTitle" and playing its content
			int index = playlistIndex(playlistTitle);
			playlists.get(index).play(indexInPL -1);
			return true;
		}

		// when playlistTitle playlist doesn't exist 
		errorMsg= "Playlist " + playlistTitle + " Does Not Exist";
		return false;
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{	
		// checking is playlist exists 
		if (checkPlaylist(playlistTitle)) {
			
			// if content being added is a song 
			if (type.toUpperCase().equals(Song.TYPENAME)) {

				// getting index of title playlist in playlist array list 
				int i = playlistIndex(playlistTitle);
				Playlist current= playlists.get(i);

				// adding song to playlist if its not already in playlist 
				if (!checkPlaylist(songs.get(index-1).getTitle())) {
					current.addContent(songs.get(index-1));
					return true;

				// otherwise setting error message
				} else {
					errorMsg= "Song Already In Playlist";
					System.out.println(errorMsg);
					return false;
				}
				
			// if content being added is an audiobook 
			} else if (type.toUpperCase().equals(AudioBook.TYPENAME)) {

				// getting index of title playlist in playlist array list 
				int i = playlistIndex(playlistTitle);
				Playlist current=  playlists.get(i);

				// adding audiobook to playlist if its not already in playlist
				if (!checkPlaylist(audiobooks.get(index-1).getName())) {
					current.addContent(audiobooks.get(index-1));
					return true;
				
				// otherwise setting error message
				} else {
					errorMsg= "AudioBook Already In Playlist";
					System.out.println(errorMsg);
					return false;
				}
			
			// if content being added is a podcast
			} else if (type.toUpperCase().equals(Podcast.TYPENAME)) {

				// getting index of title playlist in playlist array list 
				int i = playlistIndex(playlistTitle);
				Playlist current=  playlists.get(i);

				// adding podcast to playlist if its not already in playlist
				if (!checkPlaylist(podcasts.get(index-1).getName())) {
					current.addContent(podcasts.get(index-1));
					return true;

				// otherwise setting error message
				} else {
					errorMsg= "Podcast Already In Playlist";
					System.out.println(errorMsg);
					return false;
				}

			// if type is neither song, audio book, or podcast - setting error
			} else { 
				errorMsg= "Type:" + type.toUpperCase() + " Does Not Exist";
				System.out.println(errorMsg);
				return false;
			}
		
		// if playlist doesn't exist
		} else {
			errorMsg= "Playlist " + playlistTitle +  " Not Found";
			System.out.println(errorMsg);
			return false;
		}
		
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		// checking if title exists
		if (checkPlaylist(title)) {

			// getting index of title playlist in playlist arraylist 
			int i= playlistIndex(title);
			Playlist current= playlists.get(i);

			// checking if index in current playlist is valid
			if (i>=0 && i< current.getContent().size()) {

				// deleting content from playlist at index
				current.deleteContent(index);

				return true;
			
			// otherwise, setting error if that song, audio book, or podcast was not found
			} else {
				errorMsg= "The Song// Audiobook// Podcast not found";
				return false;
			}

		}
		
		// if playlist names "title" is not in arraylist
		errorMsg= "Playlist not found";
		System.out.println(errorMsg);
		return false;
	}

	
}

