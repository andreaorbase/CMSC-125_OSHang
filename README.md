# OSHang: OS-Themed Hangman Game

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Files and Directories](#files-and-directories)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [User System Flow](#user-system-flow)
  - [Preview](#preview)
  - [Example](#example)
- [License](#license)

## Overview
OSHang is a Hangman game where players guess words related to Operating System (OS) concepts. Designed to test knowledge on terms like "scheduling", "kernel", or "deadlock". The game features a hacking inspired aesthetic, immersive pixel art GUI, perfect for anyone wanting to review OS topics in an interactive way.

## Features 
- Splash Screen  
- Music and Sound Effect  
- Mouse Keyboard Input Handling  
- Scoring System  
- Hint System

## Files and Directories
- OSHang GUI/ - design assets for user interface (e.g., splash screen, backgrounds)
- bin/ - compiled .class files
- src/ - java source files
- exe - executable versions
- jar - exported Java Archive files
- word.txt - list of one-word OS terms
- word2.txt - list of two-word OS terms 

## Getting Started

### Prerequisites

To run **OSHang**, ensure the following are installed or available:

- **Java Development Kit (JDK) 21** or later  
- A system that supports running `.exe` or `.jar` files  
- *Optional:* Java Runtime Environment (JRE) for running `.jar` without compiling

To launch the game:

- Double-click the `OSHang.exe` file, or  
- Run the `.jar` file using:
  <pre>  java -jar OSHang.jar </pre>

### User-System Flow

Once the splash screen finishes, the **Main Menu** will appear with four options:

1. **Play** – Starts the game. Instructions are shown in the game screen.  
2. **Settings** – Toggle background music and sound effects.  
3. **Exit** – Closes the program.  
4. **?** – Displays author credits.

#### Gameplay Overview

- When "Play" is clicked, a random OS-related word and its description will be loaded from a `.txt` file.
- The word will appear as underscores (e.g., `_ _ _ _ _`) to indicate unguessed letters.
- The player types a single **alphabet letter** and submits the guess by pressing **Enter**.
- Numbers and special characters are disabled in the input field.
- The system checks the input:
- If the letter **exists** in the word:
  - All instances of the letter are revealed.
  - The letter turns **green** on the virtual keyboard.
- If the letter **does not exist**:
  - A visual effect (like glitches or button changes) simulates a system “hang.”
  - The letter turns **red** on the virtual keyboard.
  - The number of remaining attempts decreases.

- The player is allowed **7 incorrect guesses**. If all are used, the game ends.

- If the player completes the word before reaching the limit:
  - They earn **10 points**.
  - The next word loads automatically.
  - Players may spend **5 points** to purchase a hint, which reveals one random letter in the current word.

- The game ends if:
  - The word is not guessed in 7 tries  
  - The player exits  
  - The player returns to the main menu

### Example

- **Correct guess**:
  - Matching letters appear in place of underscores.  
  - Letter turns green on the keyboard.

- **Incorrect guess**:
  - Visual changes simulate a system glitch or error.  
  - Letter turns red.  
  - Remaining attempts decrease.
 
## License
This project is **free to use** and can be modified or distributed without any restrictions.


