# Java Mars Rover Kata [![Build Status](https://travis-ci.org/vincent-vieira/java-mars-rover-kata.svg?branch=develop)](https://travis-ci.org/vincent-vieira/java-mars-rover-kata)
## About the project
This project is my personal answer of the "Mars Rover" kata.

### Rules
Develop an api that moves a rover around on a grid :
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.

### Some precisions
- This implementation is based on Java 8.
- A little part of the project's code (such as class getters/setters/toString functions) is generated using the great
[Lombok plugin](https://projectlombok.org).
- All my commits follow the [AngularJS commit message guidelines](https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#commit),
and the project is fully aware of the [Git Flow](http://danielkummer.github.io/git-flow-cheatsheet/index.fr_FR.html) methodology.
- This project is fully integrated into [TravisCI](https://travis-ci.org) (**as you can see on the small image present
at the right of the title**), and the CI job is configured to run all unit tests using Java 8.
