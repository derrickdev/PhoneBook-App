#!/bin/bash

# Remove compiled classes
rm -rf ./bin

# Compile Java source files
mkdir -p ./bin
javac -d ./bin ./src/*.java

# Run Java application and write output to new files
java -cp ./bin Main > output.txt

# Display the output
cat output.txt
