# bleed: an extensible program for Java Swing that adds a bubbly particle effect to the mouse.

#### Benjamin Steenhoek
#### 6/28/2018

## To Run
- Extract the latest release of the project to your pc.
- Build the project in one of two ways
    - Using Gradle:
        - Open a command line window in the root directory of the project.
        - Enter **./gradlew clean build**
        - Run the project via the executable in the output directory.
    - Using IntelliJ IDEA:
        - Open the project in IntelliJ.
        - Build the project using IntelliJ's built-in Gradle support.
        - Click Run on Main.java.

## To Use in Your Project
The Drip package should be isolated from the other classes; just rip that project out and pop an instance of DripPanel into your project and you'll be golden.

## Description
I got the idea for this while reading Clean Code, by Robert C. Martin. In chapter 3, page 34 in my copy, he mentions that he got together with Kent Beck and was looking at a Java Swing program that produced a sparkly visual effect. I used this project as an extensible spin-off to learn myself how to write clean, concise functions.

This project *should* take on some of the properties of clean code described in the first few chapters of the book (all I have read thus far). I hope I make ol' Uncle Bob proud.

## Technical notes
Technologies used
- IntelliJ IDEA
- Java 8
- Swing
- JUnit test
