package View;

public class Message {
	private String Sender;
	private String text;
	
	public Message(String sender, String text) {
		super();
		Sender = sender;
		this.text = text;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
