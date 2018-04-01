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
			if(Configuracoes.isInserirBancoListaArquivo())
				metodoVerificaDadosParaArmazenar(emf);
			if(Configuracoes.isInserirBancoListaMetodos())
				metodoVerificaDadosParaArmazenar(emf);
			if(Configuracoes.isInserirBancoListaIf())
				metodoVerificaDadosParaArmazenar(emf);
			if(Configuracoes.isInserirBancoListaExcecoes())
				metodoVerificaDadosParaArmazenar(emf);
			if(Configuracoes.isInserirBancoListaConsultas())
				metodoVerificaDadosParaArmazenar(emf);
			System.out.println("A persistencia de criar dados por comparação no banco de dados foi executada.");
		}

        else if(Configuracoes.getAcoesBanco()==3)
			System.out.println("A persistencia de dropar todo o banco foi executada");
        else
        	System.out.println("Não há persistencia colocada");
        emf.close();

	}

	private static void inserirNoBanco(EntityManagerFactory emf, List lista) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object c : lista) 
	        em.persist(c);
        em.close();
        em.getTransaction().commit();
	}
	
	//"select * from metodosXPCELL"
	/*
	private static <ob> List retornaDadosBanco(String query, Class classe)
	{

        try {
            Object ob = classe.newInstance();
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoAbellaNaoFazNada");
    		EntityManager em = emf.createEntityManager();

    		em.getTransaction().begin();
    		
    		@SuppressWarnings("unchecked")
    		TypedQuery<ob> listaBancoDados = (TypedQuery<ob>) em.createNativeQuery(query, ob.class);

    		MetodoService.setListaBancoMetodos(listaBancoDados.getResultList());

            em.close();
            emf.close();
            
        } 
        catch (InstantiationException ex) {
            Logger.getLogger(AppPrinc.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            Logger.getLogger(AppPrinc.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		return null;
	}
	*/
	private static void metodoVerificaDadosParaArmazenar(EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		
		
		emf = Persistence.createEntityManagerFactory("ProjetoAbellaNaoFazNada");
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
