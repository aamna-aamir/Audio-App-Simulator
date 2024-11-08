// Aamna Aamir - 501153037

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {

			// putting code in a try block 
			try {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List store items
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all books
				{
					mylibrary.listAllAudioBooks(); 
				}
				// else if (action.equalsIgnoreCase("PODCASTS"))	// List all podcasts
				// {
				// 	mylibrary.listAllPodcasts(); 
				// } 
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int index1 = 0;
					int index2= 0;
					
					// reading index 1
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						index1 = scanner.nextInt();
						scanner.nextLine();
					}

					// reading index 2
					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						index2 = scanner.nextInt();
						scanner.nextLine(); 
					}
					
					if (index1 >0 && index2<=store.allContent().size()) {
						for (int i=index1; i>=index1 && i<=index2; i++){
							try {
								AudioContent content = store.getContent(i);
								mylibrary.download(content); 
							} 
							catch (AudioContentAlreadyExistsException e) {
								System.out.println(e.getMessage());
							} 
							catch (AudioContentNotFoundException e) {
								System.out.println(e.getMessage());
							}

						}
					} else {
						throw new AudioContentNotFoundException("Index Out of Range!");
					} 

										
				}

				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int index =0;

					// getting song number
					System.out.print("Song Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					
					// playsong method already checks range and sets error message 
					mylibrary.playSong(index);

				}


				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int index =0;

					// getting audiobook number
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						index= scanner.nextInt();
						scanner.nextLine();
					}

					// print audibook toc checks range and sets error message
					mylibrary.printAudioBookTOC(index);
				}


				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int book =0;
					int chapter =0;

					// reading book
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						book = scanner.nextInt();
						scanner.nextLine();
					}

					// reading chapter 
					System.out.print("Chapter: ");
					if (scanner.hasNextInt()) {
						chapter = scanner.nextInt();
						scanner.nextLine();
					}

					// playaudiobook method already checks for range of audiobook and chapter and prints error message
					mylibrary.playAudioBook(book, chapter);

				}


				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String name = "";

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}

					// playing the audio content of the whole playlist 
					mylibrary.playPlaylist(name);

				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					int index= 0;
					String name = "";

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}


					// reading index 
					System.out.print("Content Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}

					// playing content at index
					mylibrary.playPlaylist(name, index);

				}

				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int index =0;

					// reading song index 
					System.out.print("Library Song #: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}	

					// deleting the song at index
					mylibrary.deleteSong(index);

				}

				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String name = "";

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}

					// making new playlist with name given
					mylibrary.makePlaylist(name);

				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String name = "";

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}

					// printing contents
					mylibrary.printPlaylist(name);

				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					
					int index =0;
					String name= "";
					String type= "";

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}

					// reading content type
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNext()) {
						type = scanner.nextLine();
					}

					// reading content number
					System.out.print("Library Content #: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}	

					// adding content of type 
					mylibrary.addContentToPlaylist(type, index, name);
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String name = "";
					int index = 0;

					// reading playlist title 
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						name = scanner.nextLine();
					}

					// reading content number
					System.out.print("Playlist Content #: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}	

					// deleting from playlist
					mylibrary.delContentFromPlaylist(index, name);
				}
				else if (action.equalsIgnoreCase("SEARCH")) 
				{
					String title = "";

					// reading playlist title 
					System.out.print("Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}

					// seeing if this title exist
					store.search(title);
				}
				else if (action.equalsIgnoreCase("SEARCHA")) 
				{
					String artist = "";

					// reading playlist title 
					System.out.print("Artist: ");
					if (scanner.hasNext()) {
						artist = scanner.nextLine();
					}

					// seeing if this title exist
					store.searchA(artist);
				}
				else if (action.equalsIgnoreCase("SEARCHG")) 
				{
					String genre = "";

					// reading playlist title 
					System.out.print("Genre [POP, ROCK, JAZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNext()) {
						genre = scanner.nextLine();
					}

					// seeing if this title exist
					store.searchG(genre);
				}
				else if (action.equalsIgnoreCase("DOWNLOADA")) 
				{
					String artist = "";

					// reading playlist title 
					System.out.print("Artist: ");
					if (scanner.hasNext()) {
						artist = scanner.nextLine();
					}

					// seeing if this title exist
					store.downloadA(artist, mylibrary);
				}
				else if (action.equalsIgnoreCase("DOWNLOADG")) 
				{
					String genre = "";

					// reading playlist title 
					System.out.print("Genre [POP, ROCK, JAZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNext()) {
						genre = scanner.nextLine();
					}

					// seeing if this title exist
					store.downloadG(genre, mylibrary);
				}

				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}

				System.out.print("\n>");
			}

			// catching audiocontent alreayd exists exception
			catch (AudioContentAlreadyExistsException e) {
				System.out.println(e.getMessage() + "\n");
			}

			// catching audiocontent not found exception
			catch (AudioContentNotFoundException e) {
				System.out.println(e.getMessage() + "\n");
			}

		}

	}
}
