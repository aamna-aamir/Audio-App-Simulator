// audio content already exists exception
public class AudioContentAlreadyExistsException extends RuntimeException{
    public AudioContentAlreadyExistsException() {}
	public AudioContentAlreadyExistsException(String string) {
		super(string);
	}
}
