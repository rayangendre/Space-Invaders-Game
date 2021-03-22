
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.List;

interface PathingStrategy
{
   /*
    * Returns a prefix of a path from the start point to a point within reach
    * of the end point.  This path is only valid ("clear") when returned, but
    * may be invalidated by movement of other entities.
    *
    * The prefix includes neither the start point nor the end point.
    */
   List<APoint> computePath(APoint start, APoint end,
      Predicate<APoint> canPassThrough,
      BiPredicate<APoint, APoint> withinReach,
      Function<APoint, Stream<APoint>> potentialNeighbors);

   static final Function<APoint, Stream<APoint>> CARDINAL_NEIGHBORS =
      point ->
         Stream.<APoint>builder()
            .add(new APoint(point.x, point.y - 1))
            .add(new APoint(point.x, point.y + 1))
            .add(new APoint(point.x - 1, point.y))
            .add(new APoint(point.x + 1, point.y))
            .build();
}