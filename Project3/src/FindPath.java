import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FindPath {
    private PathingStrategy strategy;
    private static final int TILE_SIZE = 32;
    private Entity grid[][];

    public FindPath(PathingStrategy strategy, Entity[][] grid) {
        this.strategy = strategy;
        this.grid = grid;
    }

    public boolean findGoalAStar(APoint pos, APoint goal, List<APoint> path) {
        List<APoint> points;

        points = strategy.computePath(pos, goal,
                p -> withinBounds(p, grid) && grid[p.y][p.x] == null,
                (p1, p2) -> neighbors(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);

        if (points.size() == 0) {
            System.out.println("No path found");
            return false;
        }

        path.addAll(points);


        return true;
    }

    public boolean findGoalSingleStep(APoint pos, APoint goal, List<APoint> path){
        List<APoint> points;

        points = strategy.computePath(pos, goal,
                p -> withinBoundsYaeger(p, grid) && grid[p.y][p.x] == null,
                (p1, p2) -> neighbors(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);


        if (points.size() == 0) {

            return false;
        }

        pos = points.get(0);
        path.add(pos);


        return true;
    }

    private static boolean withinBounds(APoint p, Entity grid[][]) {
        return p.y >= 0 && p.y < grid.length &&
                p.x>= 0 && p.x < grid[0].length;
    }
    private static boolean withinBoundsYaeger(APoint p, Entity grid[][]) {
        return p.y >= 0 && p.y <= 15 &&
                p.x>= 0 && p.x < grid[0].length;
    }

    private static boolean neighbors(APoint p1, APoint p2) {
        return p1.x + 1 == p2.x && p1.y == p2.y ||
                p1.x - 1 == p2.x && p1.y == p2.y ||
                p1.x == p2.x && p1.y + 1 == p2.y ||
                p1.x == p2.x && p1.y - 1 == p2.y;
    }

    private static final Function<APoint, Stream<APoint>> DIAGONAL_NEIGHBORS =
            point ->
                    Stream.<APoint>builder()
                            .add(new APoint(point.x - TILE_SIZE, point.y - TILE_SIZE))
                            .add(new APoint(point.x + TILE_SIZE, point.y + TILE_SIZE))
                            .add(new APoint(point.x - TILE_SIZE, point.y + TILE_SIZE))
                            .add(new APoint(point.x + TILE_SIZE, point.y - TILE_SIZE))
                            .build();


    private static final Function<APoint, Stream<APoint>> DIAGONAL_CARDINAL_NEIGHBORS =
            point ->
                    Stream.<APoint>builder()
                            .add(new APoint(point.x - TILE_SIZE, point.y - TILE_SIZE))
                            .add(new APoint(point.x + TILE_SIZE, point.y + TILE_SIZE))
                            .add(new APoint(point.x - TILE_SIZE, point.y + TILE_SIZE))
                            .add(new APoint(point.x + TILE_SIZE, point.y - TILE_SIZE))
                            .add(new APoint(point.x, point.y - TILE_SIZE))
                            .add(new APoint(point.x, point.y + TILE_SIZE))
                            .add(new APoint(point.x - TILE_SIZE, point.y))
                            .add(new APoint(point.x + TILE_SIZE, point.y))
                            .build();

}

