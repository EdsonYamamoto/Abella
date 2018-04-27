package pacoteGrafo;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	public List<Node> nodes;
	
	public Graph()
	{
		nodes = new ArrayList<Node>();
	}

	//metodo que armazena um nome mais uma informacao
	public void addNode(int i, String nome, String info)
	{
		if(find(i)==null)
			nodes.add(new Node(i, nome, info));
	}
	//metodo que armazena apenas o nome de um nó
	public void addNode(int i,String nome)
	{
		if(find(i)==null)
			nodes.add(new Node(i,nome));
	}
	//metodo que armazena apenas o nome de um nó
	public void addNode(int i)
	{
		nodes.add(new Node(i));
	}
	public void addEdge(int inicio, int fim, String info) 
	{
		Node nFrom = find(inicio);
        Node nTo = find(fim);
        if (nFrom != null && nTo != null)
        {
            nFrom.AddEdge(nTo, info);
        }
	}
	
	public Node find(int i)
	{
		for (Node node : nodes) 
			if(node.id==i)
				return node;
			
		return null;
	}
}
