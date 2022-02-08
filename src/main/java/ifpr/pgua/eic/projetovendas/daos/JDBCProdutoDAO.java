package ifpr.pgua.eic.projetovendas.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ifpr.pgua.eic.projetovendas.daos.interfaces.ProdutoDAO;
import ifpr.pgua.eic.projetovendas.models.Produto;

public class JDBCProdutoDAO implements ProdutoDAO {

    @Override
    public boolean cadastrar(Produto p) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:mysql://root@localhost:3306/info_kalil");

        String sql = "INSERT INTO pessoas(nome,descricao,quantidadeEstoque, valor) VALUES (?,?,?,?)";

        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, p.getNome());
        pstmt.setString(2, p.getDescricao());
        pstmt.setInt(3, p.getQuantidadeEstoque());
        pstmt.setDouble(4, p.getValor());

        pstmt.execute();

        // pegando o id gerado para o produto
        ResultSet rsId = pstmt.getGeneratedKeys();
        rsId.next();
        int id = rsId.getInt(1);

        rsId.close();
        pstmt.close();
        con.close();

        p.setId(id);

        return true;
       
    }

    @Override
    public boolean atualizar(int id, Produto p) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remover(Produto p) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Produto> listar() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    
   
}
