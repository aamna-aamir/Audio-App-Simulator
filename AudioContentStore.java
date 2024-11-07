// Aamna Aamir - 501153037

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	private ArrayList<AudioContent> contents; 
	private Map <String, Integer> titleIndex;
	private Map <String, ArrayList<Integer> > artistIndexes;
	Map <String, ArrayList <Integer>> genreIndexes;
	
	public AudioContentStore()
	{
		// content for the store
		contents = new ArrayList<AudioContent>();
		getallcontent();

		// map 1->  key= string= title & value = integer= index value in contents arraylist
		titleIndex= new HashMap<String, Integer>();
		for (int i=0; i< contents.size(); i++) {
			titleIndex.put(contents.get(i).getTitle(), i+1);
		}

		// map 2-> key= string= artist (song) / author (audiobook) & value= integer arraylist list= represent indices in contents arraylist
		artistIndexes = new HashMap<String, ArrayList<Integer> > ();
		for (int i=0; i < contents.size(); i++) {

			// if the content is a song
			if (contents.get(i).getType().equals("SONG")) {

				// converting to song 
				Song s= (Song) contents.get(i);

				// is artist not already in map
				if (! artistIndexes.containsKey(s.getArtist())) {
					// creating a new arraylist for values
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(i+1); // adding the i+1 index into the arraylist

					// putting it on map
					artistIndexes.put(s.getArtist(), a);

				// if artist is  already in map
				} else if( artistIndexes.containsKey(s.getArtist())) {
					artistIndexes.get(s.getArtist()).add(i+1);
				}

			} else if (contents.get(i).getType().equals("AUDIOBOOK")) {
				// converting to song 
				AudioBook b= (AudioBook) contents.get(i);

				// is artist not already in map
				if (! artistIndexes.containsKey(b.getAuthor())) {
					// creating a new arraylist for values
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(i+1); // adding the i+1 index into the arraylist

					// putting it on map
					artistIndexes.put(b.getAuthor(), a);

				// if artist is  already in map
				} else if(artistIndexes.containsKey(b.getAuthor())) {
					artistIndexes.get(b.getAuthor()).add(i+1);
				}
			}

		}


		// map 3 -> key= string= genre of song & value= integer array list= represent indices into array list
		genreIndexes= new HashMap<String, ArrayList<Integer>>();
		for (int i=0; i< contents.size(); i++) {

			if (contents.get(i).getType().equals("SONG")) {
				// converting to song bc genre only exists for songs 
				Song song= (Song) contents.get(i);
				String genre= song.getGenre().toString();

				// is artist not already in map
				if (!genreIndexes.containsKey(genre)) {
					// creating a new arraylist for values
					ArrayList<Integer> ints = new ArrayList<Integer>();
					ints.add(i+1); // adding the i+1 index into the arraylist

					// putting it on map
					genreIndexes.put(genre, ints);

				// if artist is  already in map
				} else if(genreIndexes.containsKey(genre)) {
					genreIndexes.get(genre).add(i+1);
				}
			}
		}

	}
	
	public void getallcontent(){

		try {
			// reading text from the file
			File file= new File("store.txt");
			Scanner in= new Scanner(file);

			while(in.hasNextLine()) {

				// next element
				String type= in.nextLine();

				// if the type = song
				if (type.equals("SONG")) {

					String id= in.nextLine();
					String title= in.nextLine();
					int year= Integer.parseInt(in.nextLine());
					int length= Integer.parseInt(in.nextLine());
					String artist= in.nextLine();
					String composer= in.nextLine();
					Song.Genre genre = Song.Genre.valueOf(in.nextLine());

					String lyrics= "";
					int lyricLines= Integer.parseInt(in.nextLine());
					for (int i=0; i<lyricLines; i++) {
						lyrics+= in.nextLine() + "\n";
					}
					
					contents.add(new Song(title, year, id, Song.TYPENAME, "", length, artist, composer, genre, lyrics));

				} else if (type.equals("AUDIOBOOK")) {
					String id = in.nextLine();
					String title= in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String author = in.nextLine();
					String narrator= in.nextLine();

					ArrayList<String> chapterTitles= new ArrayList<String> ();
					int numChapter= Integer.parseInt(in.nextLine());
					for (int i=0; i<numChapter; i++) {
						chapterTitles.add(in.nextLine());
					}

					ArrayList<String> chapters= new ArrayList <String> ();
					for (int i=0; i<numChapter; i++) {
						String ch= "";
						int chLines= Integer.parseInt(in.nextLine());
						for (int j=0; j<chLines; j++) {
							ch+= in.nextLine() + "\n";
						}
						chapters.add(ch);
					}	

					contents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters));

				}
			}

			in.close(); 
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

	} 

	public ArrayList<AudioContent> allContent () {
		return contents;
	}


	public AudioContent getContent(int index)
	{
		if (index < 1 || index > contents.size())
		{
			return null;
		}
		return contents.get(index-1);
	}
	
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	public void printIndexValueInfo(int index) {
		if (contents.get(index-1).getType().equals("SONG")) {
			Song s= (Song) contents.get(index-1);
			System.out.print(index + ". ");
			s.printInfo();
			System.out.print("\n");


		} else if (contents.get(index-1).getType().equals("AUDIOBOOK")) {
			AudioBook a= (AudioBook) contents.get(index-1);
			System.out.print(index + ". ");
			a.printInfo();
			System.out.print("\n");


		}
	}

	// search title method
	public void search(String title) throws AudioContentNotFoundException{
		if (titleIndex.containsKey(title)) {
			int index= titleIndex.get(title);
			printIndexValueInfo(index);
		} else {
			throw new AudioContentNotFoundException("No Matches for " + title);
		}
	}

	// search by artist method
	public void searchA(String artist) throws AudioContentNotFoundException {
		if (artistIndexes.containsKey(artist)) {
			ArrayList <Integer> indexes= artistIndexes.get(artist);

			for (int index: indexes) {
				printIndexValueInfo(index);
			}
		} else {
			throw new AudioContentNotFoundException("No Matches for " + artist);
		}
	}

	// search genre method
	public void searchG(String genre) throws AudioContentNotFoundException{
		if (genreIndexes.containsKey(genre)) {
			ArrayList <Integer> indexes= genreIndexes.get(genre);

			for (int index: indexes) {
				Song s= (Song) contents.get(index-1);
				System.out.print(index + ". ");
				s.printInfo();
				System.out.print("\n");
		
			}
		} else {
			throw new AudioContentNotFoundException("No Matches for " + genre);
		}

	}

	// search by artist method
	public void downloadA(String artist, Library x) throws AudioContentNotFoundException {
		if (artistIndexes.containsKey(artist)) {
			ArrayList <Integer> indexes= artistIndexes.get(artist);
			for (int index: indexes) {
				try {
					AudioContent content = contents.get(index-1);
					x.download(content); 
				} 
				catch (AudioContentAlreadyExistsException e) {
					System.out.println(e.getMessage());
				} 
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			throw new AudioContentNotFoundException("No Matches for " + artist);
		}
	}

	// search genre method
	public void downloadG(String genre, Library x) throws AudioContentNotFoundException{
		if (genreIndexes.containsKey(genre)) {
			ArrayList <Integer> indexes= genreIndexes.get(genre);

			for (int index: indexes) {
				try {
					AudioContent content = contents.get(index-1);
					x.download(content); 
				} 
				catch (AudioContentAlreadyExistsException e) {
					System.out.println(e.getMessage());
				} 
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			
		} else {
			throw new AudioContentNotFoundException("No Matches for " + genre);
		}

	}



}
	