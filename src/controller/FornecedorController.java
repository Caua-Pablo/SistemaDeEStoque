package controller;
import model.Fornecedor;

import java.util.ArrayList;

public class FornecedorController extends ControllGenerica {


    public String Cadastrar(Fornecedor forn){
        if (fornService.ler(forn)) {
            return "Usuario cadastrado";
        }
        if (fornService.esc(forn)){
            return "Usuario Cadastro com Sucesso";
        }else{
            return "Tente Novamente";
        }
    }


    public ArrayList<Fornecedor> Listar(){

        return  fornService.ler();

    }
    public boolean Atualizar(Fornecedor forn){
        return fornService.atualizar(forn);
    }


    public boolean Deletar(Fornecedor forn){
        return fornService.Deletar(forn);
    }


}


