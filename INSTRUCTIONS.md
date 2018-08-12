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

## Coverage

```mvn clean package -Pjacoco```

Coverage report: ${project.build.directory}/site/jacoco/index.html 