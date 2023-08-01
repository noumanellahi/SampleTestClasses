@FunctionalInterface // It is optional
interface Drawable {
	public void draw(int width);
}

public class LambdaExpressionExample2 {
	public static void main(String[] args) {
		int width = 10;

		// with lambda
		Drawable d2 = (value) -> {
			System.out.println("Drawing " + value);
		};
		d2.draw(20);
	}
}