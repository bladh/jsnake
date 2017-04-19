package ninja.bladh.jsnake.entity;

public class Segment extends Piece {
    private int life;

    public Segment(int life) {
        this.life = life;
    }

    /**
     * @return True of the segment is still alive.
     */
    public boolean tick() {
        life--;
        return life > 0;
    }

    public void extendLife() {
        life++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;
        return life == segment.life;
    }

    @Override
    public int hashCode() {
        return life;
    }
}
