
public class SingletonPattern {

	public static SingletonPattern obj;

	private SingletonPattern() {

	}

	public static SingletonPattern getObj() {
		if (obj == null) {
			obj = new SingletonPattern();
		}
		return obj;
	}
}
