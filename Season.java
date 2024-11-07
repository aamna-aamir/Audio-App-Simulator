import java.util.ArrayList;

public class Season {
    public ArrayList<String> episodeFiles;
    public ArrayList<String> episodeTitles;
    public ArrayList<Integer> episodeLengths;

    // constructor method
    public Season() {
        episodeFiles= new ArrayList<String> ();
        episodeTitles= new ArrayList<String> ();
        episodeLengths= new ArrayList<Integer> ();

    }

    // accessor method for arraylist of episode content 
    public ArrayList<String> getEpFiles() {
        return episodeFiles;
    }

    // accessor method for arraylist of episode titles
    public ArrayList<String> getEPtitles () {
        return episodeTitles;
    }

    // accessor method for arraylist of episode lengths 
    public ArrayList<Integer> getEPlength() {
        return episodeLengths;
    }

    // returns num of episodes
    public int numEP() {
        return episodeTitles.size();
    }

}
