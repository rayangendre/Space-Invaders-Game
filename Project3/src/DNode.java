import java.util.Objects;

public class DNode implements Comparable<DNode>{
    private APoint point;
    private DNode previous;
    private int length;


    public DNode(APoint point, DNode previous, int length) {
        this.point = point;
        this.previous = previous;
        this.length = length;


    }

    public APoint getPoint() {
        return point;
    }

    public DNode getPrevious() {
        return previous;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DNode dNode = (DNode) o;
        return point.x==dNode.point.x && point.y == dNode.point.y && this.length == ((DNode) o).length;
    }


    @Override
    public int compareTo(DNode o) {
        if (this.length < o.length){
            return -1;
        }else if (this.length > o.length){
            return 1;
        }else {
            if (!(point.x==o.point.x && point.y == o.point.y)) {
                return 1;
            }
        }
        return 0;

    }
}
