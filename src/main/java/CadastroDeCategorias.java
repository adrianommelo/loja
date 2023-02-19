
import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeCategorias {

	public static void main(String[] args) {

		Categoria celulares = new Categoria("CELULARES");

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		em.persist(celulares);
		em.flush();
		
		celulares.setNome("CPTO");
		celulares = em.merge(celulares);
		
		celulares.setNome("CPTO 2 ");

		em.flush();
//		em.getTransaction().commit();
		em.close();
	}
}
;