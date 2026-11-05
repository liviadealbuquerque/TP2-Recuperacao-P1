package br.edu.pedidos;
import java.net.URI;import java.io.IOException;
public class Main {
    public static void main(String[] args){
        try {var resposta=new ClienteApi().buscar(URI.create("https://dummyjson.com/carts?limit=0"));
            System.out.println("Carrinhos recebidos: "+resposta.getCarts().size()+" | total informado: "+resposta.getTotal());
            Relatorio.imprimir(resposta.getCarts());
        } catch(InterruptedException e){Thread.currentThread().interrupt();System.err.println("Consulta interrompida.");}
        catch(IOException e){System.err.println("Não foi possível consultar ou interpretar os pedidos: "+e.getMessage());}
    }
}
