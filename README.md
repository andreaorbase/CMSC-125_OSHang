# OSHang
### An Operating-System-Themed Hangman Game
Guess the secret Operating System-related word before your system "hangs" and crashes!

## Table of Contents
- [Overview](#overview)

## Features
📍 Splash Screen  
📍 Music and Sound Effect  
📍 Keyboard Input Handling  
📍 Scoring System  
📍 Hint System  

## User-System Flow
🎯 Once the splash screen exits and the main menu appears, player can choose between 4 buttons  
  &emsp; 1️⃣ **Play** -- Redirects to game proper and where the **Instructions** can be found  
  &emsp; 2️⃣ **Settings** -- Contains the music and sound effect option  
  &emsp; 3️⃣ **Exit** -- Will close the program  
  &emsp; 4️⃣ **?** -- Author Credits  
    
🎯 Upon clicking Play, the system will load a random word from a txt file and its corresponding description. This word will initially be displayed as a series of _.
  
🎯 Player enters an alphabet key in the text field and submit this guess by pressing the enter key. No number and special symbols are allowed to be typed.  
  
🎯 The system will check if the letter is in the word.   
  &emsp; 🟩 If it **IS** in the word  
  &emsp; 👉🏻 all the instances of the letter in the word will appear, replacing the _ they are positioned in; and   
  &emsp; 👉🏻 the letter in the virtual keyboard will turn green;     
  
  &emsp; 🟩 If it is **NOT** in the word  
  &emsp; 👉🏻 the window will change towards "hanging" --- may be in the form of error messages, buttons changing in appearance, glitches, etc.  
  &emsp; 👉🏻 the letter in the virtual keyboard will turn red; and
  &emsp; 👉🏻 attempt counter decrements;  
    
🎯 The player only has **7 worng attempts** before the system "hangs". If all attempts are used, the game will end.  
  
🎯 If the word is guessed before the player runs out of attempts, they will **earn 10 points** and load the next word. Using these points, the player can buy a hint to reveal a random letter. Each costs 5 points.
  
🎯 The game will only end when player fails to guess the word, exits the game, or go back to main menu.

## Overview

## How to RUN
REQUISITE: **jdk 21** 

Double click the exe file "OSHang.exe"  

## License 📜
This project is **free to use** and can be modified or distributed without any restrictions.


