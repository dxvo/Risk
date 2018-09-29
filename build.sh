#!/usr/bin/env bash
echo \#building!

cd src

javac *.java

echo \#running!

java Risk

cd ..

./clean.sh

echo \#done!
