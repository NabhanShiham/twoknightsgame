# Two Knights on a Chess Board

The following game is played by two players.

Consider a chessboard with a white and a black knight placed on it. 
The pieces are placed on the squares a8 and h1 initially. 
One player moves the white knight, the other one the black knight, they move their piece in turn. 
The knights can move according to the rules of chess with the following restriction: a piece can be moved to a square only if none of the pieces were on it before. 
The player who can make the last move wins the game.

### Prerequisites

- Java 21
- Maven

### Installing

A step by step series of examples that tell you how to get a development environment running.

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean install` to build the project

## Running the tests

Run `mvn test` to execute the unit tests via JUnit.

## Built With

- [Java](https://www.oracle.com/java/)
- [Maven](https://maven.apache.org/)

## Image Credits
- [Knight icons created by Plastic Donut - Flaticon](https://www.flaticon.com/free-icons/knight)

- [Chess icons created by Plastic Donut - Flaticon](https://www.flaticon.com/free-icons/chess)

- [Close icons created by hqrloveq - Flaticon](https://www.flaticon.com/free-icons/close)

## Authors

- Mohamed Nabhan Shiham

## An Example Game:
```
  a b c d e f g h
8 W _ _ _ _ _ _ _ 8
7 _ _ _ _ _ _ _ _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ _ 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ _ _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ B 1
  a b c d e f g h

PLAYER_1' move [from]: a8
PLAYER_1' move [to]: c7
  a b c d e f g h
8 X _ _ _ _ _ _ _ 8
7 _ _ W _ _ _ _ _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ _ 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ _ _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ B 1
  a b c d e f g h

PLAYER_2' move [from]: h1
PLAYER_2' move [to]: g3
  a b c d e f g h
8 X _ _ _ _ _ _ _ 8
7 _ _ W _ _ _ _ _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ _ 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ B _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: c7
PLAYER_1' move [to]: e8
  a b c d e f g h
8 X _ _ _ W _ _ _ 8
7 _ _ X _ _ _ _ _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ _ 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ B _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: g3
PLAYER_2' move [to]: h5
  a b c d e f g h
8 X _ _ _ W _ _ _ 8
7 _ _ X _ _ _ _ _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ B 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: e8
PLAYER_1' move [to]: g7
  a b c d e f g h
8 X _ _ _ X _ _ _ 8
7 _ _ X _ _ _ W _ 7
6 _ _ _ _ _ _ _ _ 6
5 _ _ _ _ _ _ _ B 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: h5
PLAYER_2' move [to]: f6
  a b c d e f g h
8 X _ _ _ X _ _ _ 8
7 _ _ X _ _ _ W _ 7
6 _ _ _ _ _ B _ _ 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: g7
PLAYER_1' move [to]: e6
  a b c d e f g h
8 X _ _ _ X _ _ _ 8
7 _ _ X _ _ _ X _ 7
6 _ _ _ _ W B _ _ 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: f6
PLAYER_2' move [to]: g8
  a b c d e f g h
8 X _ _ _ X _ B _ 8
7 _ _ X _ _ _ X _ 7
6 _ _ _ _ W X _ _ 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: e6
PLAYER_1' move [to]: f8
  a b c d e f g h
8 X _ _ _ X W B _ 8
7 _ _ X _ _ _ X _ 7
6 _ _ _ _ X X _ _ 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: g8
PLAYER_2' move [to]: h6
  a b c d e f g h
8 X _ _ _ X W X _ 8
7 _ _ X _ _ _ X _ 7
6 _ _ _ _ X X _ B 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: f8
PLAYER_1' move [to]: d7
  a b c d e f g h
8 X _ _ _ X X X _ 8
7 _ _ X W _ _ X _ 7
6 _ _ _ _ X X _ B 6
5 _ _ _ _ _ _ _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: h6
PLAYER_2' move [to]: f5
  a b c d e f g h
8 X _ _ _ X X X _ 8
7 _ _ X W _ _ X _ 7
6 _ _ _ _ X X _ X 6
5 _ _ _ _ _ B _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: d7
PLAYER_1' move [to]: b8
  a b c d e f g h
8 X W _ _ X X X _ 8
7 _ _ X X _ _ X _ 7
6 _ _ _ _ X X _ X 6
5 _ _ _ _ _ B _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: f5
PLAYER_2' move [to]: e7
  a b c d e f g h
8 X W _ _ X X X _ 8
7 _ _ X X B _ X _ 7
6 _ _ _ _ X X _ X 6
5 _ _ _ _ _ X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: b8
PLAYER_1' move [to]: c6
  a b c d e f g h
8 X X _ _ X X X _ 8
7 _ _ X X B _ X _ 7
6 _ _ W _ X X _ X 6
5 _ _ _ _ _ X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: e7
PLAYER_2' move [to]: g6
  a b c d e f g h
8 X X _ _ X X X _ 8
7 _ _ X X X _ X _ 7
6 _ _ W _ X X B X 6
5 _ _ _ _ _ X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: c6
PLAYER_1' move [to]: e5
  a b c d e f g h
8 X X _ _ X X X _ 8
7 _ _ X X X _ X _ 7
6 _ _ X _ X X B X 6
5 _ _ _ _ W X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_2' move [from]: g6
PLAYER_2' move [to]: h8
  a b c d e f g h
8 X X _ _ X X X B 8
7 _ _ X X X _ X _ 7
6 _ _ X _ X X X X 6
5 _ _ _ _ W X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1' move [from]: e5
PLAYER_1' move [to]: f7
  a b c d e f g h
8 X X _ _ X X X B 8
7 _ _ X X X W X _ 7
6 _ _ X _ X X X X 6
5 _ _ _ _ X X _ X 5
4 _ _ _ _ _ _ _ _ 4
3 _ _ _ _ _ _ X _ 3
2 _ _ _ _ _ _ _ _ 2
1 _ _ _ _ _ _ _ X 1
  a b c d e f g h

PLAYER_1 won
```


