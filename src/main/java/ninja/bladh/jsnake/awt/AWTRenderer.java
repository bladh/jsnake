package ninja.bladh.jsnake.awt;

import ninja.bladh.jsnake.Configuration;
import ninja.bladh.jsnake.entity.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class AWTRenderer implements ninja.bladh.jsnake.Renderer {
    private final BufferStrategy buffer;
    private final int blockSize;
    private final int dimensionWidth;
    private final int dimensionHeight;
    private final GraphicsConfiguration gConfiguration;

    public AWTRenderer(JFrame frame, Configuration configuration) {
        final int boardWidth = configuration.boardWidth;
        final int boardHeight = configuration.boardHeight;
        final GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gDevice = gEnvironment.getDefaultScreenDevice();
        this.blockSize = configuration.cellSize;
        this.dimensionWidth = boardWidth * blockSize + blockSize;
        this.dimensionHeight = boardHeight * blockSize + blockSize;
        this.gConfiguration = gDevice.getDefaultConfiguration();
        final Canvas canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(dimensionWidth, dimensionHeight);
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        this.buffer = canvas.getBufferStrategy();
    }

    public void render(Collection<Position> cells, int score) {
        Graphics graphics = null;
        Graphics2D g = null;
        try {
            final BufferedImage bufferedImage = gConfiguration.createCompatibleImage(dimensionWidth, dimensionHeight);
            g = bufferedImage.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, dimensionWidth, dimensionHeight);
            g.setColor(Color.DARK_GRAY);
            for (Position cell : cells) {
                g.fillRect(cell.x * blockSize, cell.y * blockSize, blockSize, blockSize);
            }
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, dimensionWidth - 1, dimensionHeight - 1);
            g.drawString("Score: " + score, 10, 20);
            graphics = buffer.getDrawGraphics();
            graphics.drawImage(bufferedImage, 0, 0, null);
            if (!buffer.contentsLost()) {
                buffer.show();
            }
        } finally {
            if (graphics != null) {
                graphics.dispose();
            }
            if (g != null) {
                g.dispose();
            }
        }
    }
}
