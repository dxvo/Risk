#!/usr/bin/env bash
echo \#building!

mvn compile
mvn package
mvn install assembly:assembly
java -cp target/junit5-jupiter-starter-maven-1.0-SNAPSHOT-jar-with-dependencies.jar

cd src/main/java
javac *.java

echo \#running!

java Risk

cd ..
cd ..
cd ..

./clean.sh

echo \#done!
