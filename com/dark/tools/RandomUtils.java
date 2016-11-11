package com.dark.tools;

import java.security.SecureRandom;

public final class RandomUtils {
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public static int diceRoll() {
		return inclusive(0, 100);
	}
	
	public static int inclusive(int lower, int upper) {
		return RANDOM.nextInt(upper - lower + 1) + lower;
	}

}
