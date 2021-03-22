import java.util.Objects;

public class Node implements Comparable<Node>{
    private APoint point;
    private Node previous;
    private int h;
    private int g;
    private int f;

    public Node(APoint point, Node previous, int h, int g) {
        this.point = point;
        this.previous = previous;
        this.f = g + h;
        this.g = g;
        this.h = h;
    }

    public APoint getPoint() {
        return point;
    }

    public Node getPrevious() {
        return previous;
    }

    public int getF() {
        return f;
    }

    public int getH() {
        return h;
    }

    public int getG() {
        return g;
    }

    @Override
    public int compareTo(Node o) {
        if (this.f < o.f){
            return -1;
        } else if (this.f> o.f){
            return 1;
        }else {

            if (!Objects.equals(this.point, o.point)){
                return 1;
            }
            if (this.g > o.g){
                return 1;
            } else if (this.g < o.g){
                return -1;
            }else {
                return 0;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return h == node.h && g == node.g && f == node.f && Objects.equals(point, node.point);
    }

}
