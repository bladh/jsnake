JSnake
======

Created in my spare time in response to my friend making
[snake in python](https://github.com/jocke-l/snake).

I hope this very simple game can be a resource for any beginners to java game
development - feel free to study and copypasta.

I'm using AWT to render the graphics and collecting keyboard input. The game
loop is governed by a java.util.Timer, since the logic is so simple. The
rendering is also done in the game loop. I wouldn't recommend these approaches
for a more advanced game, but in this case simplicity suffices. If you
want something more sophisticated, I suggest looking into
[Slick2D](http://slick.ninjacave.com/).

The Java source compatibility is set to 1.5, and I'm using standard libraries
for everything, so you'd be hardpressed to find a system with Java installed
that can't run this. The .jar file should land on around 17K.

The game currently stops on game over. The game window is no longer redrawn
after this, so resizing the window at this point will probably blank out the
game. There are obviously solutions for this, but I'm lazy :)

How to run
----------

JSnake requires that you have at least Java 1.5 installed (but it is only tested
on Oracle Java 1.8).

linux, bsd, osx...

    git pull https://github.com/bladh/jsnake.git
    cd jsnake
    ./gradlew run

windows...

    git pull https://github.com/bladh/jsnake.git
    cd jsnake
    gradlew run

Running the gradle wrapper will download Gradle if you don't have it on your
system already.
