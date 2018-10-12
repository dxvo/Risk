#!/usr/bin/env bash
echo \#building!

cd src
cd main
cd java

javac *.java

echo \#running!

java Risk

cd ..
cd ..
cd ..

./clean.sh

echo \#done!
