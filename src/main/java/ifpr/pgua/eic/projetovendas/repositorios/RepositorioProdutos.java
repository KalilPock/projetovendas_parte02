package ifpr.pgua.eic.projetovendas.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ifpr.pgua.eic.projetovendas.daos.interfaces.ProdutoDAO;
import ifpr.pgua.eic.projetovendas.models.Produto;

public class RepositorioProdutos {

    private ArrayList<Produto> Produtos;
    
    private ProdutoDAO ProdutoDAO;

    public RepositorioProdutos() {
        Produtos = new ArrayList<>();
        this.ProdutoDAO = ProdutoDAO;
    }

    public boolean cadastrarProduto(String descricao, String nome, int quantidadeEstoque , Double valor) throws SQLException {
        if (buscarProduto(nome) == null) {
            Produto p = new Produto(descricao, nome, quantidadeEstoque, valor);
            
            try{
                ProdutoDAO.cadastrar(p);
            }catch(Exception e){
                throw new SQLException(e.getCause());
            }
            

            this.Produtos.add(p);

            return true;

        }

        return false;
    }

    public Produto buscarProduto(String nome) {
        return this.Produtos.stream().filter((Produto) -> Produto.getNome().equals(nome)).findFirst().orElse(null);
    }

    public ArrayList<Produto> listarProdutos() throws Exception {

        ArrayList<Produto> lista = ProdutoDAO.listar();

        return lista;
    }

}
