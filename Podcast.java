import java.util.ArrayList;

public class Podcast extends AudioContent {
    public static final String TYPENAME = "PODCAST";
	
	private String host; 
	private ArrayList<Season> seasons;
    private int currentSeason= 0;
    private int currentEp = 0;


	// constructor method
	public Podcast(String title, int year, String id, String type, String audioFile, int length,
									String host, ArrayList<Season> seasons )
	{
		super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons= seasons;

	}

    // accessor method for type
    public String getType() {
        return TYPENAME;
    }


    // accesor for title of podcast
    public String getName() {
        return this.getTitle();
    }

    // assecor for num seasons in a podcast
    public int numSeasons() {
        return seasons.size();
    }

    // printing info about podcast
    public void printInfo()
	{
		super.printInfo();
		System.out.println("Host: " + host + "\nSeasons: " + this.numSeasons());

	}

    //selecting season in the podcast
    public void selectSeason(int s) {
        if (s>0 && s<= seasons.size()) {
            currentSeason= s-1;
        }
    }

    // selecting episode in the podcast
    public void selectEP(int ep) {
        if (ep>0 && ep<=seasons.get(currentSeason).numEP()) {
            currentEp = ep-1;
        }
    }


    // method for playing podcast 
    public void play() {

        // setting audio file to title of current episode at current season & printing the content of that episode
        setAudioFile("" + seasons.get(currentSeason).getEPtitles().get(currentEp)  + ".\n" + seasons.get(currentSeason).getEpFiles().get(currentEp));
		// playing the episode
        super.play();
    }

    // printing episode titles 
    public void printPod() {

        // checking is episode is within range
        for (int i=0; i<seasons.get(currentSeason).numEP() ; i++) {
            System.out.println("Episode " + (i+1) + ". " + seasons.get(currentSeason).getEPtitles().get(i) + "\n");
        }
        
    }

    // equals method
    public boolean equals(Object other)
	{
		Podcast others = (Podcast) other;
		return super.equals(others) && host.equals(others.host) && seasons.equals(others.seasons);
	}

}
