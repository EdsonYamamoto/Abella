package features;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import main.AppPrinc;
import main.Configuracoes;
import modelo.ArquivoModelo;
import modelo.ConsultaModelo;
import modelo.ExceptionsModelo;
import modelo.IfModelo;
import modelo.MetodoModelo;
import service.ArquivoService;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class BancoDados {
	@SuppressWarnings("unchecked")
	public static void inserirDadosBanco()
	{
		EntityManagerFactory emf;
		switch (Configuracoes.getAcoesBanco()) {
		case 1:
			
			emf = Persistence.createEntityManagerFactory("ProjetoAbellaDropCreate");
			break;
		case 2:
			emf = Persistence.createEntityManagerFactory("ProjetoAbellaCreate");
			break;
		case 3:
			emf = Persistence.createEntityManagerFactory("ProjetoAbellaDrop");
			break;
		default:
			emf = Persistence.createEntityManagerFactory("ProjetoAbellaNaoFazNada");
		}

		if(Configuracoes.getAcoesBanco()==1)
		{
			if(Configuracoes.isInserirBancoListaArquivo())
				inserirNoBanco(emf, ArquivoService.getListaArquivos());
			if(Configuracoes.isInserirBancoListaMetodos())
				inserirNoBanco(emf, MetodoService.getListaMetodos());
			if(Configuracoes.isInserirBancoListaIf())
				inserirNoBanco(emf, IfService.getListaIf());
			if(Configuracoes.isInserirBancoListaExcecoes())
				inserirNoBanco(emf, ExceptionService.getListaExceptions());
			if(Configuracoes.isInserirBancoListaConsultas())
				inserirNoBanco(emf, ConsultaService.getListaConsultas());
			
			System.out.println("A persistencia de dropar e criar tabelas com os dados obtidos por leitura no banco de dados foi executada.");
		}
		/*
		 * Nao implementado
		 * */
		else if(Configuracoes.getAcoesBanco()==2)
		{
			/*
			if(Configuracoes.isInserirBancoListaArquivo())
				metodoVerificaDadosParaArmazenarArquivo(emf,"select * from metodosXPCELL");
			if(Configuracoes.isInserirBancoListaMetodos())
				metodoVerificaDadosParaArmazenar(emf, null, null);
			if(Configuracoes.isInserirBancoListaIf())
				metodoVerificaDadosParaArmazenar(emf, null, null);
			if(Configuracoes.isInserirBancoListaExcecoes())
				metodoVerificaDadosParaArmazenar(emf, null, null);
			if(Configuracoes.isInserirBancoListaConsultas())
				metodoVerificaDadosParaArmazenar(emf, null, null);
				*/
			//System.out.println("A persistencia de criar dados por comparação no banco de dados foi executada.");
		}

        else if(Configuracoes.getAcoesBanco()==3)
			System.out.println("A persistencia de dropar todo o banco foi executada");
        else
        	System.out.println("Não há persistencia colocada");
        emf.close();
	}
	private static void inserirNoBanco(EntityManagerFactory emf, @SuppressWarnings("rawtypes") List lista) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object c : lista) 
	        em.persist(c);
        em.close();
        em.getTransaction().commit();
	}
	/*
	 * Contem a logica para inserção no banco de dados
	 * */
	public static void leQueryEEscreveNovosDados() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoAbellaNaoFazNada");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		TypedQuery<MetodoModelo> listaBancoDados = (TypedQuery<MetodoModelo>) em.createNativeQuery("select * from metodosXPCELL", MetodoModelo.class);

		MetodoService.setListaBancoMetodos(listaBancoDados.getResultList());

        em.close();
        emf.close();

        for (MetodoModelo metodoBanco : MetodoService.getListaBancoMetodos())
		{
	        Iterator<MetodoModelo> i = MetodoService.getListaMetodos().iterator();
	        while (i.hasNext()) {
	        	MetodoModelo o = i.next();
	        	if((metodoBanco.getUnit()+metodoBanco.getMetodo()).compareTo(o.getUnit()+o.getMetodo())==0)
	        	{
	        		i.remove();
	        	}
	        }
		}
		for (MetodoModelo m : MetodoService.getListaMetodos()) 
			System.out.println(m.getMetodo());
		
		
		emf = Persistence.createEntityManagerFactory("ProjetoAbellaCreate");
		inserirNoBanco(emf,MetodoService.getListaMetodos());
		
		
	}


}
