package com.dark.core.util.chance;



import java.util.Objects;

/**
 * An item with a common chance.
 * 
 * @author Michael | Chex
 */
public class WeightedChance<T> implements WeightedObject<T> {

	/**
	 * The representation.
	 */
	private final T representation;

	/**
	 * The weight type.
	 */
	private double weight;
	
	public static final double COMMON = 100;
	public static final double UNCOMMON = 30;
	public static final double RARE = 10;
	public static final double VERY_RARE = 1;

	public WeightedChance(double weight, T representation) {
		if (weight <= 0) {
			throw new IllegalArgumentException("The weight of an item must be larger than zero!");
		}
		this.representation = Objects.requireNonNull(representation);
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public T get() {
		return representation;
	}
	
	@Override
	public String toString() {
		return "[Object: " + get() + ", " + "Weight: " + getWeight() + "]";
	}

	@Override
	public int compareTo(WeightedObject<T> o) {
		return (int) Math.signum(getWeight() - o.getWeight());
	}
}