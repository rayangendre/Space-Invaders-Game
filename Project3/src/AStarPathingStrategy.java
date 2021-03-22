import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{


    public List<APoint> computePath(APoint start, APoint end, Predicate<APoint> canPassThrough,
                                   BiPredicate<APoint, APoint> withinReach,
                                   Function<APoint, Stream<APoint>> potentialNeighbors)
    {
        List<APoint> path = new LinkedList<>();
        TreeSet<Node> open = new TreeSet<>();
        HashMap<Node, APoint> closed = new HashMap<>();
        Node pathNode = null;
        boolean pathFound = false;

        open.add(new Node(start, null, 0, 0));


        while (!pathFound && !open.isEmpty()){
            Node currentNode = open.pollFirst();

            if (withinReach.test(currentNode.getPoint(), end)){
                pathNode = currentNode;
                pathFound = true;
            }
            List<APoint> potential = potentialNeighbors.apply(currentNode.getPoint()).filter(canPassThrough).collect(Collectors.toList());
            for (APoint point: potential){

                Node adjacent = new Node(point, currentNode,Math.abs(end.x - point.x) + Math.abs(end.y - point.y), currentNode.getG() + 1);
                if (!closed.containsKey(adjacent) && !open.contains(adjacent)){
                        open.add(adjacent);



                }


            }
            if (!closed.containsKey(currentNode)){
                closed.put(currentNode, currentNode.getPoint());
            }



        }
        if (open.isEmpty()){
            return path;
        }

        Node previous = pathNode;
        while(previous != null){
            path.add(previous.getPoint());
            previous = previous.getPrevious();

        }


        Collections.reverse(path);
        return path;
    }
}
