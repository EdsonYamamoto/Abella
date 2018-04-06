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
	public void addNode(String nome, String pasName)
	{
		if(find(nome)==null)
			nodes.add(new Node(nome, pasName));
	}
	//metodo que armazena apenas o nome de um nó
	public void addNode(String nome)
	{
		if(find(nome)==null)
			nodes.add(new Node(nome));
	}
	
	public void addEdge(String nomeInicio, String nomeFim, String info) 
	{
		Node nFrom = find(nomeInicio);
        Node nTo = find(nomeFim);
        if (nFrom != null && nTo != null)
        {
            nFrom.AddEdge(nTo, info);
        }
	}
	
	public Node find(String nome)
	{
		for (Node node : nodes) 
			if(node.nome.compareTo(nome)==0)
				return node;
			
		return null;
	}
}
