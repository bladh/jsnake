package ninja.bladh.jsnake;

import ninja.bladh.jsnake.awt.AWTControls;
import ninja.bladh.jsnake.awt.AWTRenderer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("JSnake");
        final Configuration config = new Configuration(20, 20, 16, 150);
        final Renderer renderer = new AWTRenderer(frame, config);
        final JRootPane pane = frame.getRootPane();
        final Controls awtControls = new AWTControls(pane);
        final Game game = Game.create(config, Board.create(config), renderer, awtControls);
        final Thread thread = new Thread(game);
        thread.start();
    }
}
