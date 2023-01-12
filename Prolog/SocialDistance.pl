smallSizes(6,15,10).
mediumSizes(13,30,30).
largeSizes(27,120,50).

distance(6,13,27).
duration(15,30,120).
exhalationLevel(10,30,50).

givenSizes(6,15,10).
givenSizes(13,30,30).
givenSizes(27,120,50).
	
givenSafe(13,30,30).
givenSafe(6,30,10).
givenSafe(27,30,50).
givenSafe(13,15,50).
givenSafe(13,120,10).
givenSafe(27,120,30).
givenSafe(6,15,30).



	
derivedSafe(Distance,Duration,ExhalationLevel):-
	givenSafe(SafeDistance,SafeDuration,SafeExhalationLevel),
	Distance >= SafeDistance,
	Duration =< SafeDuration,
	ExhalationLevel =< SafeExhalationLevel.
	
	
min(0).
max(999).

interpolateLow(Small,Medium,Large,Original,Interpolated):-
	min(Number),
	(Original<Small, Interpolated=Number);
	(Original>=Small, Original<Medium, Interpolated=Small);
	(Original>=Medium, Original<Large, Interpolated=Medium);
	(Original>=Large, Interpolated=Large).

	
interpolateHigh(Small,Medium,Large,Original,Interpolated):-
	max(Number),
	(Original>Large, Interpolated=Number);
	(Original=<Large, Original>Medium, Interpolated=Large);
	(Original=<Medium, Original>Small, Interpolated=Medium);
	(Original=<Small, Interpolated=Small).
	
interpolatedSafe(Distance,Duration,ExhalationLevel):-
	smallSizes(SmallDistance,SmallDuration,SmallExhalationLevel),
	mediumSizes(MediumDistance,MediumDuration,MediumExhalationLevel),
	largeSizes(LargeDistance,LargeDuration,LargeExhalationLevel),
	interpolateLow(SmallDistance,MediumDistance,LargeDistance,Distance,InterpolatedDistance),
	interpolateHigh(SmallDuration,MediumDuration,LargeDuration,Duration,InterpolatedDuration),
	interpolateHigh(SmallExhalationLevel,MediumExhalationLevel,LargeExhalationLevel,ExhalationLevel,InterpolatedExhalationLevel),
	givenSafe(InterpolatedDistance,InterpolatedDuration,InterpolatedExhalationLevel).

interpolatedSafe(Distance,Duration):-
	mediumSizes(_,_,MediumExhalationLevel),
	interpolatedSafe(Distance,Duration,MediumExhalationLevel).
	
interpolatedSafe(Distance):-
	mediumSizes(_,MediumDuration,_),
	interpolatedSafe(Distance,MediumDuration).
	
generateSafeDistancesAndDurations(Distance,Duration,ExhalationLevel):-
	exhalationLevel(SmallExhalationLevel,MediumExhalationLevel,LargeExhalationLevel),
	interpolateHigh(SmallExhalationLevel,MediumExhalationLevel,LargeExhalationLevel,ExhalationLevel,InterpolatedExhalationLevel),
	givenSafe(Distance,Duration,InterpolatedExhalationLevel).

givenSafe([
	[13,30,30],
	[6,30,10],
	[27,30,50],
	[13,15,50],
	[13,120,10],
	[27,120,30],
	[6,15,30]
	]).
listGivenSafe(SafeTuple, [SafeTuple|T]).

listGivenSafe(SafeTuple, [H|T]):-
	listGivenSafe(SafeTuple, T).

listGivenSafe(SafeTuple):-
	givenSafe([H|T]),
	listGivenSafe(SafeTuple, [H|T]).
	
%safe tuple. check against head of tuplelist. move head.

printGivenCombinations(N, [[Distance,Duration,ExhalationLevel]|T]):-
	N>0,
	write(Distance),write(','),
	write(Duration),write(','),
	write(ExhalationLevel),write(','),
	write('true\n'),
	M is N-1,
	printGivenCombinations(M,T).
	
printGivenCombinations(N):-
	givenSafe([H|T]),
	write('Distance, Duration, Exhalation, isSafe\n'),
	printGivenCombinations(N,[H|T]).
	
listGenerateSafeDistancesAndDurations(ExhalationLevel,GeneratingTable,[],GeneratedTable):-
	GeneratedTable=GeneratingTable.
%								may need to remove distanceduration headm could restrict what goes here.
listGenerateSafeDistancesAndDurations(InterpolatedExhalationLevel,GeneratingTable,[[Distance,Duration,InterpolatedExhalationLevel]|T],GeneratedTable):-
	listGenerateSafeDistancesAndDurations(InterpolatedExhalationLevel,[[Distance,Duration]|GeneratingTable],T,GeneratedTable).	

listGenerateSafeDistancesAndDurations(InterpolatedExhalationLevel,GeneratingTable,[H|T],GeneratedTable):-
	H\=[_,_,InterpolatedExhalationLevel],
	listGenerateSafeDistancesAndDurations(InterpolatedExhalationLevel,GeneratingTable,T,GeneratedTable).
	

listGenerateSafeDistancesAndDurations(ExhalationLevel,GeneratedTable):-
	givenSafe([H|T]),
	exhalationLevel(Small,Medium,Large),
	interpolateHigh(Small,Medium,Large,ExhalationLevel,InterpolatedExhalationLevel), 
	listGenerateSafeDistancesAndDurations(InterpolatedExhalationLevel,[],[H|T],GeneratedTable).
	