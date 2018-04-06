package pacoteGrafo;

public class Edge {
	public Node from;
	public Node to;
	public String dados;
	public Edge()
	{}
	public Edge(Node from, Node to, String dado)
	{
		this.from = from;
		this.to = to;
		this.dados = dado;
	}
	
}
