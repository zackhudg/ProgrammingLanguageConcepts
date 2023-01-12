package socialDistancing;

import java.util.ArrayList;
import java.util.List;

public class SocialDistancingUtility {
	final static int DISTANCE_SMALL = 6;
	final static int DISTANCE_MEDIUM = 13;
	final static int DISTANCE_LARGE = 27;
	final static int DURATION_SMALL = 15; 
	final static int DURATION_MEDIUM = 30;
	final static int DURATION_LARGE = 120;
	final static int EXHALATION_LEVEL_SMALL = 10;
	final static int EXHALATION_LEVEL_MEDIUM = 30;
	final static int EXHALATION_LEVEL_LARGE = 50;
	final static String COMMA = ",";
	
	final static int[][] SAFE_COMBINATIONS = {
			{DISTANCE_MEDIUM,DURATION_MEDIUM,EXHALATION_LEVEL_MEDIUM},
			{DISTANCE_SMALL,DURATION_MEDIUM,EXHALATION_LEVEL_SMALL},
			{DISTANCE_LARGE,DURATION_MEDIUM,EXHALATION_LEVEL_LARGE},
			{DISTANCE_MEDIUM,DURATION_SMALL,EXHALATION_LEVEL_LARGE},
			{DISTANCE_MEDIUM,DURATION_LARGE,EXHALATION_LEVEL_SMALL},
			{DISTANCE_LARGE,DURATION_LARGE,EXHALATION_LEVEL_MEDIUM},
			{DISTANCE_SMALL,DURATION_SMALL,EXHALATION_LEVEL_MEDIUM}
	};
	
	private static boolean compareCombinations(final int distance, final int duration, final int exhalationLevel, final boolean allowDerivation) {
		boolean result = false;
		for (int i = 0; i < SAFE_COMBINATIONS.length; i++) {
			if (result){
				return result;
			}
			final int safeDistance = SAFE_COMBINATIONS[i][0];
			final int safeDuration = SAFE_COMBINATIONS[i][1];
			final int safeExhalationLevel = SAFE_COMBINATIONS[i][2];
			if (allowDerivation) {
				result = (safeDistance <= distance && safeDuration >= duration && safeExhalationLevel >= exhalationLevel);
			}
			else{
				result = (safeDistance == distance && safeDuration == duration && safeExhalationLevel == exhalationLevel);
			}
		}
		return result;
	}
	
	public static boolean isGivenSafe(final int distance, final int duration, final int exhalationLevel) {
		return compareCombinations(distance, duration, exhalationLevel, false);
	} 
	
	
	private static int interpolateLow(final int numberToInterpolateArg, final int parameterSmall, final int parameterMedium, final int parameterLarge) {
		final int minValue = 0;
		int numberToInterpolate = numberToInterpolateArg;
		if(parameterLarge <= numberToInterpolateArg) {
			numberToInterpolate = parameterLarge;
		}
		if (parameterMedium <= numberToInterpolateArg && numberToInterpolateArg < parameterLarge) {
			numberToInterpolate = parameterMedium;
		}
		if (parameterSmall <= numberToInterpolateArg && numberToInterpolateArg < parameterMedium) {
			numberToInterpolate = parameterSmall;
		}
		if (0 <= numberToInterpolateArg && numberToInterpolateArg < parameterSmall) {
			numberToInterpolate = minValue;
		}
		return numberToInterpolate;
	}
	
	private static int interpolateHigh(final int numberToInterpolateArg, final int parameterSmall, final int parameterMedium, final int parameterLarge) {
		int numberToInterpolate = numberToInterpolateArg;
		if(parameterSmall >= numberToInterpolateArg) {
			numberToInterpolate = parameterSmall;
		}
		if (parameterMedium >= numberToInterpolateArg && numberToInterpolateArg > parameterSmall) {
			numberToInterpolate = parameterMedium;
		}
		if (parameterLarge >= numberToInterpolateArg && numberToInterpolateArg > parameterMedium) {
			numberToInterpolate = parameterLarge;
		}
		if (Integer.MAX_VALUE >= numberToInterpolateArg && numberToInterpolateArg > parameterLarge) {
			numberToInterpolate = Integer.MAX_VALUE;
		}
		return numberToInterpolate;
	}
	
	public static boolean isInterpolatedSafe(final int distance, final int duration, final int exhalationLevel) {
		final int interpolatedDistance = interpolateLow(distance, DISTANCE_SMALL, DISTANCE_MEDIUM, DISTANCE_LARGE);
		final int interpolatedDuration = interpolateHigh(duration, DURATION_SMALL, DURATION_MEDIUM, DURATION_LARGE);
		final int interpolatedExhalationLevel = interpolateHigh(exhalationLevel, EXHALATION_LEVEL_SMALL, EXHALATION_LEVEL_MEDIUM, EXHALATION_LEVEL_LARGE);
		return isGivenSafe(interpolatedDistance, interpolatedDuration, interpolatedExhalationLevel);
	}
	
	public static boolean isInterpolatedSafe(final int distance, final int duration) {
		return isInterpolatedSafe(distance, duration, EXHALATION_LEVEL_MEDIUM);
	}
	
	public static boolean isInterpolatedSafe(final int distance) {
		return isInterpolatedSafe(distance, DURATION_MEDIUM);
	}
	
	
	public static boolean isDerivedSafe(final int distance, final int duration, final int exhalationLevel) {
		return compareCombinations(distance, duration, exhalationLevel, true);
	}
	
	public static void printGeneratedCombinationDerivedSafety() {
		final double maximumRandomMultiplier = 1.2;
		
		final int randomDistance = (int)(Math.random() * DISTANCE_LARGE * maximumRandomMultiplier);
		final int randomDuration = (int)(Math.random() * DURATION_LARGE * maximumRandomMultiplier);
		final int randomExhalationLevel = (int)(Math.random() * EXHALATION_LEVEL_LARGE * maximumRandomMultiplier);
		final boolean isRandomDerivedSafe = isDerivedSafe(randomDistance, randomDuration, randomExhalationLevel);
		System.out.println(randomDistance + COMMA + randomDuration + COMMA + randomExhalationLevel + COMMA + isRandomDerivedSafe);
	}
	
	private static void printGivenCombinationsSafety() {
		System.out.println("Distance,Duration,Exhalation,IsSafe");
		for (int i = 0; i < SAFE_COMBINATIONS.length; i++) {
			final int safeDistance = SAFE_COMBINATIONS[i][0];
			final int safeDuration = SAFE_COMBINATIONS[i][1];
			final int safeExhalationLevel = SAFE_COMBINATIONS[i][2];
			System.out.println(safeDistance + COMMA + safeDuration + COMMA + safeExhalationLevel + COMMA + "true");
		}
	}
	
	public static void printGivenAndGeneratedCombinationsDerivedSafety() {
		printGivenCombinationsSafety();
		System.out.println("----------------");
		final int numberOfRandomlyGeneratedCombinations = 10;
		for (int i = 0; i < numberOfRandomlyGeneratedCombinations; i++) {
			printGeneratedCombinationDerivedSafety();
		}
	}
	
	public static List<Integer[]> generateSafeDistancesAndDurations(final int exhalationLevel){
		final int interpolatedExhalationLevel = interpolateHigh(exhalationLevel, EXHALATION_LEVEL_SMALL, EXHALATION_LEVEL_MEDIUM, EXHALATION_LEVEL_LARGE);
		final List<Integer[]> safeDistancesAndDurationList = new ArrayList<Integer[]>();
		for (int i = 0; i < SAFE_COMBINATIONS.length; i++) {
			if (interpolatedExhalationLevel == SAFE_COMBINATIONS[i][2]) {
				final Integer[] safeDistanceAndDuration = {SAFE_COMBINATIONS[i][0], SAFE_COMBINATIONS[i][1]};
				safeDistancesAndDurationList.add(safeDistanceAndDuration);
			}
		}
		return safeDistancesAndDurationList;
	}
	
	public static void printSafeDistancesAndDurations(final int exhalationLevel) {
		final List<Integer[]> safeDistancesAndDurationList = generateSafeDistancesAndDurations(exhalationLevel);
		String safeDistancesAndDurationString = "[";
		for (Integer[] safeDistanceAndDuration : safeDistancesAndDurationList) {
			safeDistancesAndDurationString += "{" + safeDistanceAndDuration[0] + COMMA + safeDistanceAndDuration[1] + "}";
		}
		safeDistancesAndDurationString += "]";
		System.out.println(exhalationLevel + COMMA + safeDistancesAndDurationString);
	}
	
	
	
}


