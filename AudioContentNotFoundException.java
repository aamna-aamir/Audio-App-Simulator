// audio content not found exception
public class AudioContentNotFoundException extends RuntimeException{
    public AudioContentNotFoundException() {}
	public AudioContentNotFoundException(String string) {
		super(string);
	}
}
