fun givenSafe(13,30,30) = true |
		givenSafe(6,30,10) = true |
		givenSafe(27,30,50) = true |
		givenSafe(13,15,50) = true |
		givenSafe(13,120,10) = true |
		givenSafe(27,120,30) = true |
		givenSafe(6,15,30) = true |
		givenSafe(distance,duration,exhalationLevel) = false;		

fun interpolateLowHelper(lowerBound,value,upperBound) = 
	lowerBound <= value andalso value < upperBound;

fun interpolateLow(small,medium,large,original) = 
		if (interpolateLowHelper(0,original,small)) then 0
		else if (interpolateLowHelper(small,original,medium)) then small
		else if (interpolateLowHelper(medium,original,large)) then medium
		else large;
		
fun interpolateHighHelper(lowerBound,value,upperBound) = 
	value <= upperBound andalso lowerBound < value;
		
fun interpolateHigh(small,medium,large,original) = 
		if (interpolateHighHelper(0-1,original,small)) then small
		else if (interpolateHighHelper(small,original,medium)) then medium
		else if (interpolateHighHelper(medium,original,large)) then large
		else 200;
		
fun interpolatedSafe(distance,duration,exhalationLevel) = 
	let 
		val interpolatedDistance = interpolateLow(6,13,27,distance);
		val interpolatedDuration = interpolateHigh(15,30,120,duration);
		val interpolatedExhalationLevel = interpolateHigh(10,30,50,exhalationLevel);
	in 
		givenSafe(interpolatedDistance,interpolatedDuration,interpolatedExhalationLevel)
	end;
	
val SAFETY_TABLE =
  [(13,30,30),(6,30,10),(27,30,50),(13,15,50),(13,120,10),(27,120,30),
   (6,15,30)]
    		
fun listDerivedSafeHelper(distance,duration,exhalationLevel,nil) = false |
 		listDerivedSafeHelper(distance,duration,exhalationLevel,(safeDistance,safeDuration,safeExhalationLevel)::tail) = 
 		(distance >= safeDistance andalso duration <= safeDuration andalso exhalationLevel <= safeExhalationLevel) 
 		orelse listDerivedSafeHelper(distance,duration,exhalationLevel,tail);
   
fun 
	listDerivedSafe(distance,duration,exhalationLevel) = 
 		let 
 			val list = SAFETY_TABLE
 		in
 			listDerivedSafeHelper(distance,duration,exhalationLevel,list)
 		end;
 		
 fun printSafety(safetyComputerFunction, (distance,duration,exhalationLevel)) = 
 	let
 		val result = safetyComputerFunction(distance,duration,exhalationLevel)
 	in
 		print("Distance:"^Int.toString(distance)^" Duration:"^Int.toString(duration)^" Exhalation:"^Int.toString(exhalationLevel)^" Safe:"^Bool.toString(result)^"\n");
 		()
 	end;
 	
fun concisePrintSafety(safetyComputerFunction, (distance,duration,exhalationLevel)) = 
 	let
 		val result = safetyComputerFunction(distance,duration,exhalationLevel)
 	in
 		print("("^Int.toString(distance)^", "^Int.toString(duration)^", "^Int.toString(exhalationLevel)^", "^Bool.toString(result)^")\n");
 		()
 	end;
 	
fun listPrintSafety(printSafetyFunction, safetyComputerFunction, (nil)) = () |
		listPrintSafety(printSafetyFunction, safetyComputerFunction, featureTuple::tail) = 
 			let
 				val result = printSafetyFunction(safetyComputerFunction, featureTuple);
 			in
 				listPrintSafety(printSafetyFunction, safetyComputerFunction, tail)
 			end;
 			
fun matchingSafeHelper(matchingFunction, featureTuple, (nil)) = false | 
 		matchingSafeHelper(matchingFunction, featureTuple, head::tail) = 
			matchingFunction(featureTuple, head) orelse
 			matchingSafeHelper(matchingFunction, featureTuple, tail) 
 			
fun matchingSafe(matchingFunction, featureTuple) =
 			matchingSafeHelper(matchingFunction, featureTuple, SAFETY_TABLE)
 		
fun derivedSafeMatcher((distance,duration,exhalationLevel),(safeDistance,safeDuration,safeExhalationLevel)) = 
	distance >= safeDistance andalso duration <= safeDuration andalso exhalationLevel <= safeExhalationLevel
	
fun matchingDerivedSafe(featureTuple) = 
	matchingSafe(derivedSafeMatcher, featureTuple)
	
fun givenSafeMatcher((distance,duration,exhalationLevel),(safeDistance,safeDuration,safeExhalationLevel)) = 
	distance = safeDistance andalso duration = safeDuration andalso exhalationLevel = safeExhalationLevel
	
fun matchingGivenSafe(featureTuple) = 
	matchingSafe(givenSafeMatcher, featureTuple)
	
fun curryableInterpolatedSafe distance duration exhalationLevel = 
	interpolatedSafe(distance, duration, exhalationLevel)
	
fun curriedOnceInterpolatedSafe duration exhalationLevel = 
	curryableInterpolatedSafe 13 duration exhalationLevel
	
fun curriedTwiceInterpolatedSafe exhalationLevel = 
	curriedOnceInterpolatedSafe 30 exhalationLevel
	
fun curryableMatchingSafe matchingFunction featureTuple = 
	matchingSafe(matchingFunction, featureTuple)
	
fun curriedMatchingDerivedSafe featureTuple = 
	curryableMatchingSafe derivedSafeMatcher featureTuple
	
fun curriedMatchingGivenSafe featureTuple = 
	curryableMatchingSafe givenSafeMatcher featureTuple
