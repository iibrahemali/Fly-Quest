<h1 align="center" id="title">
Fly-Quest: A Simple Java Game
<br>
<img id="Ibrahem" src="https://img.shields.io/badge/HEEM%20-%20Yellow?style=flat&label=IBRA&labelColor=%233b439c&color=%23418ce8">
<img id="Star on GitHub" src="https://img.shields.io/badge/Stars%20-%20grey?style=flat&logo=GitHub">

</h1>

<p align="center"><img src="https://socialify.git.ci/iibrahemali/Fly-Quest/image?language=1&amp;name=1&amp;owner=1&amp;theme=Dark" alt="project-image"></p>

## Description
"Fly Away Home" is a simple Java game where the player controls a fly named Mosca and guides it safely to its home square while avoiding hungry predators like frogs and spiders.

`
Weekly Assignments in CPS II class (Algorithms & Data Structure in Java).
`

`
Grade: 100%
`

## Gameplay
- The player controls Mosca using arrow keys to navigate through a grid-based world.
- The objective is to guide Mosca to the home square (marked in red) without being eaten by predators.
- Predators, represented by frogs and spiders, move automatically and can eat Mosca if they occupy the same square.

## Project Structure
The project consists of several Java files:
- `FlyWorldGUI.java`: Main class responsible for drawing the game board and handling user input.
- `GridLocation.java`: Class representing a location on the game board.
- `Fly.java`: Class representing the fly character Mosca.
- `Frog.java`: Class representing the frog predators.
- `FlyWorld.java`: Class representing the game world and managing game logic.

## Setup and Execution
1. Compile the Java files:
    ```bash
    javac *.java
    ```
2. Run the game with a specified world file (e.g., `world0.txt`):
    ```bash
    java FlyWorldGUI world0.txt
    ```

## Features
1. **Initializing the Grid**: Read and parse the world file to initialize the game grid, Mosca's starting position, and the home square.
2. **Adding Frogs**: Populate the game grid with frog predators, which move automatically.
3. **Moving Mosca**: Implement Mosca's movement logic and handle interactions with the home square.
4. **Moving Frogs**: Implement frog movement logic and ensure they do not overlap or move off the screen.
5. **Eating the Fly**: Determine when a frog predator has eaten Mosca and end the game accordingly.
6. **Along Came a Spider**: Implement a new predator, the spider, with unique movement behavior towards Mosca.

## Testing
- Utilize provided sample world files (`world0.txt` and `world1.txt`) for basic testing.
- Create additional world files to test different scenarios and edge cases.

## Notes
- This project emphasizes object-oriented programming principles and encapsulation.
- Documentation generated from code comments provides detailed information about each class and method.
