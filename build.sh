#!/usr/bin/env bash

# echo $PATH_TO_FX
# javac --module-path $PATH_TO_FX --add-modules javafx.controls App.java
javac -cp "$PATH_TO_FX/*:." --module-path $PATH_TO_FX --add-modules javafx.controls App.java
java -cp "$PATH_TO_FX/*:." --module-path $PATH_TO_FX --add-modules javafx.controls App
