package pacoteGrafo;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String nome;
	public String informacao;
	public String pasName;
	public boolean visited;
	private List<Edge> edges = new ArrayList<Edge>();
	public Node()
	{
		
	}
	public Node(String nome, String PasName)
	{
		this.nome = nome;
		this.pasName = PasName;
	}
	
	public Node(String nome)
	{
		this.nome = nome;
	}
	
	public List<Edge> getArestas() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public void AddEdge(Node to, String cost)
    {
		edges.add(new Edge(this, to, cost));
    }
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	@Override
	public String toString()
	{
		return this.nome;
	}
}
