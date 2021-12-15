package ifpr.pgua.eic.projetovendas.telas;

import ifpr.pgua.eic.projetovendas.models.Pessoa;
import ifpr.pgua.eic.projetovendas.models.Produto;
import ifpr.pgua.eic.projetovendas.repositorios.RepositorioPessoas;
import ifpr.pgua.eic.projetovendas.repositorios.RepositorioVendas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class Listas {

    @FXML
    private ListView<Pessoa> lstPessoas;

    @FXML
    private ListView<Produto> lstProdutos;

    private RepositorioPessoas repositorioPessoas;
    private RepositorioVendas repositorio;

    public Listas(RepositorioVendas repositorio, RepositorioPessoas repositorioPessoas){
        this.repositorio = repositorio;
        this.repositorioPessoas = repositorioPessoas;
    }

    public void initialize(){

        lstPessoas.setCellFactory(lista -> new ListCell<>(){
            protected void updateItem(Pessoa pessoa, boolean alterou) {
                super.updateItem(pessoa, alterou);
                if(pessoa != null){
                    setText("("+pessoa.getId()+")"+pessoa.getNome());
                }else{
                    setText(null);
                }
            };
        });

        try{
            lstPessoas.getItems().addAll(repositorioPessoas.listarPessoas());
            lstProdutos.getItems().addAll(repositorio.listarProdutos());
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR,e.getMessage());
            alert.showAndWait();
        }

    }

}
