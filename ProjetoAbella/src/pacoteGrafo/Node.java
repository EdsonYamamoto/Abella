package pacoteGrafo;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String nome;
	public String informacao;
	public String pasName;
	public int id;
	public boolean visited;
	private List<Edge> edges = new ArrayList<Edge>();
	public Node()
	{
		
	}
	public Node(int i, String nome, String informacao)
	{
		this.id = i;
		this.nome = nome;
		this.informacao = informacao;
	}
	
	public Node(int i, String nome)
	{
		this.id = i;
		this.nome = nome;
	}
	
	public Node(int i)
	{
		this.id = i;
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
		return this.id+" "+this.nome+" "+this.informacao;
	}
}
