package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.ConsultaModelo;
import modelo.ExceptionsModelo;
import modelo.IfModelo;
import modelo.MetodoModelo;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class Features {
	static void impressaoDados()
	{
		if (Configuracoes.isImprimiListaMetodos()) 
			for (MetodoModelo c : MetodoService.getListaMetodos()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaExcecoes())
			for (ExceptionsModelo c : ExceptionService.getListaExceptions()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaIf())
			for (IfModelo c : IfService.getListaIf()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaConsultas())
			for (ConsultaModelo c : ConsultaService.getListaConsultas()) 
				System.out.println(c);
	}
	
	static void inserirDadosBanco()
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

		if(Configuracoes.getAcoesBanco()!=3)
		{
			if(Configuracoes.isInserirBancoListaMetodos())
				metodoArmazenaMetodos(emf);
			if(Configuracoes.isInserirBancoListaIf())
				metodoArmazenaCondicoes(emf);
			if(Configuracoes.isImprimiListaExcecoes())
				metodoArmazenaExcecoes(emf);
			if(Configuracoes.isImprimiListaConsultas())
				metodoArmazenaConsultasBanco(emf);
		}
        emf.close();
	}
	
	private static void metodoArmazenaConsultasBanco(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (ConsultaModelo c : ConsultaService.getListaConsultas()) 
			System.out.println(c);
        em.close();
        em.getTransaction().commit();
	}

	private static void metodoArmazenaCondicoes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (IfModelo e : IfService.getListaIf()) 
	        em.persist(e);
        em.close();
        em.getTransaction().commit();
	}

	private static void metodoArmazenaExcecoes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (ExceptionsModelo e : ExceptionService.getListaExceptions())
	        em.persist(e);
        em.close();
        em.getTransaction().commit();
	}
	
	public static void metodoArmazenaMetodos(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (MetodoModelo c : MetodoService.getListaMetodos()) 
			em.persist(c);
        em.close();
        em.getTransaction().commit();
	}
}
