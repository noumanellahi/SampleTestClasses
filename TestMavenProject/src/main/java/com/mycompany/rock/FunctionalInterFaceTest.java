package com.mycompany.rock;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

public class FunctionalInterFaceTest {

	public static void main(String[] args) {
		/**
		 * Predicate take an input and return boolean
		 */
		Predicate<Integer> p = i -> i > 10;
		System.out.println(p.test(50));

		/**
		 * Biconsumer accepts two input arguments and does not return any result.
		 */
		BiConsumer<Integer, Integer> bic = (i, j) -> {
			Integer s = i + j;
			System.out.println("SUM OF TWO VALUES IS : " + s);
		};
		bic.accept(5, 10);

		/**
		 * Biconsumer accepts one input arguments and does not return any result.
		 */
		Consumer<String> cons = i -> System.out.println("USER ENTER VALUE : " + i.toUpperCase());
		cons.accept("cat");

		/**
		 * Function take one value and can return any value
		 */
		Function<String, String> firstNonRepeatableCharacter = i -> {
			for (char c : i.toCharArray()) {
				if (i.indexOf(c) == i.lastIndexOf(c)) {
					return Character.toString(c);
				}
			}
			return null;
		};

		String result = firstNonRepeatableCharacter.apply("sssssssssssss");
		if (result == null) {
			System.out.println("THERE ARE NO NON REPATABLE CHARCTER IN STRING");
		} else {
			System.out.println("THE FIRST NON REPEATABLE CHARACTER IN STRING IS : " + result);
		}
	}

}
