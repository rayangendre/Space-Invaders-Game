import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DijkstraPathingStrategy implements PathingStrategy{

    @Override
    public List<APoint> computePath(APoint start, APoint end, Predicate<APoint> canPassThrough, BiPredicate<APoint, APoint> withinReach, Function<APoint, Stream<APoint>> potentialNeighbors) {
        List<APoint> path = new LinkedList<>();
        PriorityQueue<DNode> open = new PriorityQueue<>();
        HashMap<APoint, DNode> closed = new HashMap<>();
        DNode pathNode = null;
        boolean pathFound = false;

        open.add(new DNode(start, null, 0));



        while (!pathFound && !open.isEmpty()){
            DNode currentNode = open.poll();
//            System.out.println(currentNode.getPoint().x + ", " + currentNode.getPoint().y);

            if (withinReach.test(end, currentNode.getPoint())){
                pathNode = currentNode;
                pathFound = true;
            }
            List<APoint> potential = potentialNeighbors.apply(currentNode.getPoint()).filter(canPassThrough).collect(Collectors.toList());
            for (APoint APoint : potential){

                DNode adjacent = new DNode(APoint, currentNode,currentNode.getLength() +1);
                if (!open.contains(adjacent)){
                    if (!closed.containsKey(adjacent)){
                        open.add(adjacent);
                    }


                }


            }

            if (!closed.containsKey(currentNode.getPoint())){
                closed.put(currentNode.getPoint(), currentNode);
            }


//            open.sort(Node::compareTo);



        }
        if (open.isEmpty()){
            return path;
        }

        DNode previous = pathNode;
        while(previous != null){
            path.add(previous.getPoint());
            previous = previous.getPrevious();

        }


        Collections.reverse(path);
        return path;
    }
}
