package features;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
				metodoDroparEArmazenaArquivo(emf);
			if(Configuracoes.isInserirBancoListaMetodos())
				metodoDroparEArmazenaMetodos(emf);
			if(Configuracoes.isInserirBancoListaIf())
				metodoDroparEArmazenaCondicoes(emf);
			if(Configuracoes.isInserirBancoListaExcecoes())
				metodoDroparEArmazenaExcecoes(emf);
			if(Configuracoes.isInserirBancoListaConsultas())
				metodoDroparEArmazenaConsultasBanco(emf);
		}
		/*
		 * Nao implementado
		 * */
		if(Configuracoes.getAcoesBanco()==2)
		{
			if(Configuracoes.isInserirBancoListaArquivo())
				metodoVerificaParaArmazenaArquivoBanco(emf);
			if(Configuracoes.isInserirBancoListaMetodos())
				metodoVerificaParaArmazenaMetodos(emf);
			if(Configuracoes.isInserirBancoListaIf())
				metodoVerificaParaArmazenaCondicoes(emf);
			if(Configuracoes.isInserirBancoListaExcecoes())
				metodoVerificaParaArmazenaExcecoes(emf);
			if(Configuracoes.isInserirBancoListaConsultas())
				metodoVerificaParaArmazenaConsultasBanco(emf);
		}
        emf.close();
	}
	
	private static void metodoVerificaParaArmazenaArquivoBanco(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
	}

	private static void metodoVerificaParaArmazenaConsultasBanco(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
	}

	private static void metodoVerificaParaArmazenaExcecoes(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
	}

	private static void metodoVerificaParaArmazenaCondicoes(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
	}

	private static void metodoVerificaParaArmazenaMetodos(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
	}
	
	private static void metodoDroparEArmazenaConsultasBanco(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (ConsultaModelo c : ConsultaService.getListaConsultas()) 
	        em.persist(c);
        em.close();
        em.getTransaction().commit();
	}

	private static void metodoDroparEArmazenaCondicoes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (IfModelo e : IfService.getListaIf()) 
	        em.persist(e);
        em.close();
        em.getTransaction().commit();
	}

	private static void metodoDroparEArmazenaArquivo(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (ArquivoModelo e : ArquivoService.getListaArquivos())
	        em.persist(e);
        em.close();
        em.getTransaction().commit();
	}

	private static void metodoDroparEArmazenaExcecoes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (ExceptionsModelo e : ExceptionService.getListaExceptions())
	        em.persist(e);
        em.close();
        em.getTransaction().commit();
	}
	
	public static void metodoDroparEArmazenaMetodos(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (MetodoModelo c : MetodoService.getListaMetodos()) 
			em.persist(c);
        em.close();
        em.getTransaction().commit();
	}
	/*
	 * Query será usada para retirar valores lidos ja existentes no banco
	 * */
	static void leQuery()
	{
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("ProjetoAbellaCreate");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		TypedQuery<MetodoModelo> listaBancoDados = (TypedQuery<MetodoModelo>) em.createNativeQuery("select * from metodosXPCELL", MetodoModelo.class);

		MetodoService.setListaBancoMetodos(listaBancoDados.getResultList());

        em.close();
        emf.close();
        
		for (MetodoModelo metodoBanco : MetodoService.getListaBancoMetodos())
			for (MetodoModelo metodoExtraido : MetodoService.getListaMetodos()) 
				if(metodoBanco.getMetodo().compareTo(metodoExtraido.getMetodo())==0)
					MetodoService.getListaMetodos().remove(metodoBanco);
		for (MetodoModelo m : MetodoService.getListaMetodos()) 
			System.out.println(m);
		
	}
}
