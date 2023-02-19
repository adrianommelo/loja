
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {

		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);

		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarAdicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		em.getTransaction().begin();
		
		pedidoDao.cadadstrar(pedido);
		
		em.getTransaction().commit();
		
		System.out.println(produto.getPreco());

		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p2.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoPorNomeProduto("Iphone");
		System.out.println(precoDoProduto);

	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Iphone", "Muito bom!!!", new BigDecimal("5000"), celulares);
		Cliente cliente = new Cliente("Adriano", "11122233344");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadadstrar(celulares);
		produtoDao.cadadstrar(celular);
		clienteDao.cadadstrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}
