package controller;
import model.Cliente;


import java.util.ArrayList;

public class ClienteController extends  ControllGenerica{

    public String Cadastrar(Cliente cliente){
        if (clienteServ.ler(cliente)) {
            return "Cliente cadastrado";
        }
        if (clienteServ.esc(cliente)){
            return "Cliente Cadastro com Sucesso";
        }else{
            return "Tente Novamente";
        }
    }


    public ArrayList<Cliente> Listar(){

        return  clienteServ.ler();

    }
    public boolean Atualizar(Cliente cliente){
        return clienteServ.atualizar(cliente);
    }

    public boolean Deletar(Cliente cliente){
        return clienteServ.Deletar(cliente);
    }




}
