package csc.b07.e2;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphImpl implements Graph, Reversible {

  private List<Node> allNodes = new ArrayList<>();
  private List<Node> floatNodes = new ArrayList<>();
  private List<Edge> allEdges = new ArrayList<>();

  @Override
  public boolean addEdge(Node a, Node b) throws NodeNotFoundException {
    Edge edge = new EdgeImpl(a, b);
    boolean result = false;
    if (allNodes.contains(a) == false || allNodes.contains(b) == false) {
      throw new NodeNotFoundException();
    } else if (a == b) {
      result = false;
    } else if (allEdges.isEmpty()) {
      result = true;
    } else {
      for (int i = 0; i < allEdges.size(); i++) {
        Edge tempEdge = allEdges.get(i);
        if (tempEdge.compare(edge)) {
          result = false;
        } else {
          if (!allEdges.contains(edge)) {
            result = true;
          }
        }
      }
    }
    if (result == true) {
      allEdges.add(edge);
      floatNodes.remove(a);
      floatNodes.remove(b);
    }
    return result;
  }

  @Override
  public boolean addNode(Node a) {
    if (allNodes.contains(a)) {
      return false;
    } else {
      allNodes.add(a);
      floatNodes.add(a);
      return true;
    }
  }

  @Override
  public boolean removeEdge(Node a, Node b) throws NodeNotFoundException {
    Edge edge = new EdgeImpl(a, b);
    boolean result = false;
    if (allNodes.contains(a) == false || allNodes.contains(b) == false) {
      throw new NodeNotFoundException();
    } else {
      for (int i = 0; i < allEdges.size(); i++) {
        if (allEdges.get(i).compare(edge)) {
          allEdges.remove(allEdges.get(i));
          result = true;
        }
      }
    }

    return result;
  }

  @Override
  public boolean contains(Node a) {
    if (allNodes.contains(a)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int size() {
    return allNodes.size();
  }

  @Override
  public List<Node> getConnectedNodes(Node a) throws NodeNotFoundException {
    List<Node> connectedNodes = new ArrayList<>();
    if (allNodes.contains(a) == false) {
      throw new NodeNotFoundException();
    } else {
      for (Edge tempEdge : allEdges) {
        if (tempEdge.nodePosition(a) == 1) {
          Node node = tempEdge.getSecondNode();
          if (connectedNodes.contains(node) == false) {
            connectedNodes.add(node);
          }
        }
      }
    }
    return connectedNodes;
  }

  @Override
  public List<Edge> getEdges() {
    return allEdges;
  }
  
  @Override
  public void reverse() {
    for (Edge tempEdge : allEdges) {
        this.addEdge(tempEdge.getSecondNode(), tempEdge.getFirstNode());
        this.removeEdge(tempEdge.getFirstNode(), tempEdge.getSecondNode());
    }
  }

  @Override
  public String toString() {
    String result = "";
    if (allEdges.size() == 1) {
      result +=
          allEdges.get(0).getFirstNode().getId() + "->" + allEdges.get(0).getSecondNode().getId() + ";";
    } else {
      int i = 0;
      int y = 1;
      while (i < allEdges.size()) {
        Edge tempEdge = allEdges.get(i);
        while (y < allEdges.size()) {
          Edge edge = allEdges.get(y);
          if (tempEdge.compareOrderAgnostic(edge)) {
            result +=
                tempEdge.getFirstNode().getId() + "<->" + tempEdge.getSecondNode().getId() + ";";
          } else {
            result += edge.getFirstNode().getId() + "->" + edge.getSecondNode().getId() + ";";
          }
          y++;
        }
        i++;
      }
    }
    int a = 0;
    while (a < floatNodes.size()) {
      result += floatNodes.get(a).getId() + ";";
      a++;
    }

    return result;
  }

}
