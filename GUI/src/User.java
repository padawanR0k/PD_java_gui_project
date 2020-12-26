import java.util.Map;

public class User {
	int AccountId;
	String nick;
	String id;

	public User(Map<String, Object> data) {
		this.AccountId = (int) data.get("AccountId");
		this.nick = (String) data.get("nick");
		this.id = (String) data.get("id");
	}
}
