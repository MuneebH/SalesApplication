package csc.b07.e2;

public class EdgeImpl implements Edge {

  private Node firstNode;
  private Node secondNode;

  public EdgeImpl(Node firstNode, Node secondNode) {
    this.firstNode = firstNode;
    this.secondNode = secondNode;
  }
  
  @Override
  public Node getFirstNode() {
    return firstNode;
  }

  @Override
  public Node getSecondNode() {
    return secondNode;
  }

  @Override
  public int nodePosition(Node a) {
    if (a.equals(this.firstNode)) {
      return 1;
    }
    else if (a.equals(secondNode)) {
      return 2;
    }
    else {
      return 0;
    }
  }

  @Override
  public boolean compareOrderAgnostic(Edge edge) {
    
    Node firstNodeEdge = edge.getFirstNode();
    Node secondNodeEdge = edge.getSecondNode();
    if ((firstNodeEdge.equals(firstNode) || firstNodeEdge.equals(secondNode))
        && (secondNodeEdge.equals(firstNode) || secondNodeEdge.equals(secondNode))) {
      return true;
    }
    else {
      return false; 
    }
  }

  @Override
  public boolean compare(Edge edge) {
    Node first = edge.getFirstNode();
    Node second = edge.getSecondNode();
    if (first.equals(this.firstNode) && second.equals(this.secondNode)) {
      return true;
    }
    else {
      return false;
    }
  }
  
}
