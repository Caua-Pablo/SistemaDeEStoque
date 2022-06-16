package Service;
import model.Fornecedor;

import java.io.*;
import java.util.ArrayList;

public class FornecedorService {


    //ATRIBUTOS
    private final String diretorioArq = "src/data_base/fornecedor.txt";
    private FileReader arqLeitura;
    private BufferedReader memoriaLeitura;
    private File arquivo;
    private FileWriter escArq;
    private BufferedWriter memoriaEscrita;

    public FornecedorService(){
        try{
            arqLeitura = new FileReader(diretorioArq);
            memoriaLeitura = new BufferedReader(arqLeitura);
            arquivo = new File(diretorioArq);

        }catch (IOException e){
            System.out.println("Não foi possivel abrir o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );

        }
    }



    //METODO DE ESCRITA
    public boolean esc(Fornecedor forn){
        try{
            if (existeArq()){
                int contLinha = 0;
                String linha = null;
                while ((linha = memoriaLeitura.readLine()) != null){
                    contLinha = contLinha +1;
                }

                contLinha = contLinha +1;

                String dadoParaEscrever = contLinha + "; " + "Id Do Fornecedor: "+forn.getId() +"; "
                        +"Nome da Razão Social: "+forn.getRazaoSocial() +"; " +"CNPJ do Fornecedor: "+forn.getCnpj();
                escArq = new FileWriter(arquivo, true);
                memoriaEscrita = new BufferedWriter(escArq);

                memoriaEscrita.write(dadoParaEscrever);
                memoriaEscrita.newLine();

                memoriaEscrita.close();
                return true;

            }else{
                criarArq();
                esc(forn);
            }
        }catch (FileNotFoundException e){
            System.out.println("arquivo não pode ser aberto");
            System.out.println("O erro gerado é: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Não foi possivel abrir o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );
        }
        return false;
    }



    //METODO DE LEIITURA
    public Boolean ler(Fornecedor forn){

        try{
            if(existeArq()){
                String linha = null;
                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");

                    if(forn.getRazaoSocial().equals(linha_split[1])){
                        return true;
                    }
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("arquivo não pode ser aberto");
            System.out.println("O erro gerado é: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Não foi possivel ler o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );
        }
        return false;
    }



    //METODO DE COLOCAR NOME EM LISTAS
    public ArrayList<Fornecedor> ler(){

        ArrayList<Fornecedor> listForn = new ArrayList<>();

        try{
            if(existeArq()){
                String linha = null;
                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");

                    Fornecedor forn = new Fornecedor();
                    forn.setId(Integer.parseInt(linha_split[0]));
                    forn.setCnpj((linha_split[1]));
                    forn.setRazaoSocial((linha_split[2]));

                    listForn.add(forn);

                }

            }
        }catch (FileNotFoundException e){
            System.out.println("arquivo não pode ser aberto");
            System.out.println("O erro gerado é: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Não foi possivel ler o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );
        }
        return listForn;
    }

    public boolean Deletar(Fornecedor forn){
        boolean excluirForn = false;
        try{

            if(existeArq()){
                ArrayList<String> userListGravar = new ArrayList<>();
                String linha = null;
                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");

                    if(!forn.getRazaoSocial().equals(linha_split[1])){
                        userListGravar.add(linha);
                    }else{
                        excluirForn = true;
                    }
                }
                arqLeitura.close();
                memoriaLeitura.close();

                escArq = new FileWriter(arquivo, false);
                memoriaEscrita = new BufferedWriter(escArq);

                for(String novaLinha: userListGravar){
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }

                memoriaEscrita.close();
            }else{

                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("arquivo não pode ser aberto");
            System.out.println("O erro gerado é: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Não foi possivel ler o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );
        }
        return excluirForn;

    }



    public boolean atualizar(Fornecedor forn){
        boolean atualizarForn = false;
        try{
            if(existeArq()){
                ArrayList<String> userListGravar = new ArrayList<>();
                String linha = null;
                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");

                    if(!forn.getRazaoSocial().equals(linha_split[1])){
                        String novaLinha = linha_split[0] + ";" + linha_split[1] + ";" + forn.getId();
                        userListGravar.add(novaLinha);
                        atualizarForn = true;
                    }else{
                        userListGravar.add(linha);
                    }
                    userListGravar.add(linha);
                }

                arqLeitura.close();
                memoriaLeitura.close();

                escArq = new FileWriter(arquivo, false);
                memoriaEscrita = new BufferedWriter(escArq);

                for(String novaLinha: userListGravar){
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }

                memoriaEscrita.close();

            }else{
                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("arquivo não pode ser aberto");
            System.out.println("O erro gerado é: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Não foi possivel ler o arquivo");
            System.out.println("O erro Gerado é:"+e.getMessage() );
        }
        return atualizarForn;
    }



    private boolean existeArq(){
        return arquivo.exists();
    }


    private boolean criarArq(){

        try {
            return arquivo.createNewFile();

        }catch (IOException e){
            System.out.println("Erro ao criar arquivo do usuario");
            System.out.println("Erro gerado"+e.getMessage());
            return false;

        }

    }

}
