package ifpr.pgua.eic.projetovendas.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ifpr.pgua.eic.projetovendas.models.Pessoa;
import ifpr.pgua.eic.projetovendas.models.Produto;

public class RepositorioVendas {

    private ArrayList<Produto> produtos;

    public RepositorioVendas() {
        produtos = new ArrayList<>();
    }

    public boolean cadastrarProduto(String nome, String descricao, int quantidadeEstoque, double valor) {

        if (buscarProduto(nome) == null) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://root@localhost:3306/info_kalil");

                Statement stm = con.createStatement();

                String sql = "INSERT INTO produtos(nome,descricao,quantidadeEstoque,valor) VALUES ('" + nome + "','"
                        + descricao + "'," + quantidadeEstoque + "," + valor + ")";

                stm.execute(sql, Statement.RETURN_GENERATED_KEYS);

                // pegando o id gerado para o produto
                ResultSet rsId = stm.getGeneratedKeys();
                rsId.next();
                int id = rsId.getInt(1);

                rsId.close();
                stm.close();
                con.close();

                Produto p = new Produto(nome, descricao, quantidadeEstoque, valor);

                p.setId(id);

                this.produtos.add(p);

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }

        return false;
    }

    public Produto buscarProduto(String nome) {
        return this.produtos.stream().filter((produto) -> produto.getNome().equals(nome)).findFirst().orElse(null);
    }
//
    public ArrayList<Produto> listarProdutos() {
        return this.produtos;
    }

}

