# INSTRUCTIONS

## Build

```mvn clean package```

## Execute

```mvn exec:java```

The format of the output is a table with the columns:

* Position
* Pilot Code
* Pilot Name
* Laps Completed
* Total Race Time
* Best Lap
* Best Lap Time
* Time Difference to winner racer

Followed by the best lap of the race in the format:

* Hour
* Pilot Code
* Pilot Name
* Best Lap
* Best Lap Time
* Average Lap Speed

## Coverage

```mvn clean package -Pjacoco```

Coverage report: ${project.build.directory}/site/jacoco/index.html 