package ninja.bladh.jsnake;

import ninja.bladh.jsnake.entity.Position;

import java.util.Collection;

public interface Renderer {
    void render(Collection<Position> cells, int score);
}
