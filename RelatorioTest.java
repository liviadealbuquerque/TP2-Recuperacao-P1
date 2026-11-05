package br.edu.pedidos;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
class RelatorioTest {
    private final String fixture="{\"carts\":[{\"id\":1,\"userId\":2,\"total\":1200,\"discountedTotal\":1000,\"totalProducts\":1,\"totalQuantity\":2,\"products\":[{\"id\":10,\"title\":\"Mouse\",\"price\":600,\"quantity\":2,\"total\":1200,\"discountPercentage\":16,\"ignorado\":true}]},{\"id\":2,\"total\":1000,\"discountedTotal\":950,\"totalProducts\":1,\"products\":[{\"title\":\"Livro\",\"discountPercentage\":15}]}],\"total\":2,\"skip\":0,\"limit\":0,\"novoCampo\":true}";
    @Test void desserializaECalcula() throws Exception {
        var carts=new ClienteApi().converter(fixture).getCarts();
        assertEquals(1,Relatorio.acimaDeMil(carts).size());assertEquals(List.of("Mouse"),Relatorio.titulosComDesconto(carts));
        assertEquals(200,carts.get(0).getEconomia(),0.001);assertEquals(1,Relatorio.porEconomia(carts).get(0).getId());
        assertEquals(1950,Relatorio.totalComDesconto(carts),0.001);assertEquals(2L,Relatorio.porQuantidadeProdutos(carts).get(1));assertEquals(1,Relatorio.maior(carts).orElseThrow().getId());
    }
    @Test void vazioEJsonInvalido() throws Exception {
        assertTrue(Relatorio.maior(List.of()).isEmpty());assertEquals(0,Relatorio.totalComDesconto(List.of()));
        assertThrows(java.io.IOException.class,()->new ClienteApi().converter("{erro"));
        assertThrows(java.io.IOException.class,()->new ClienteApi().converter("{}"));
    }
    @Test void erroHttp() throws Exception {
        var server=com.sun.net.httpserver.HttpServer.create(new java.net.InetSocketAddress("127.0.0.1",0),0);
        server.createContext("/erro",ex->{ex.sendResponseHeaders(503,-1);ex.close();});server.start();
        try {var e=assertThrows(java.io.IOException.class,()->new ClienteApi().buscar(java.net.URI.create("http://127.0.0.1:"+server.getAddress().getPort()+"/erro")));assertTrue(e.getMessage().contains("503"));}finally{server.stop(0);}
    }
}
