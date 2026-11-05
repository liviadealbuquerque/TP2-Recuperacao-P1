package br.edu.pedidos;
import java.net.URI; import java.net.http.*; import java.time.Duration; import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ClienteApi {
    private final HttpClient client=HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15)).build();
    public RespostaCarrinhos buscar(URI endpoint) throws IOException,InterruptedException {
        var request=HttpRequest.newBuilder(endpoint).timeout(Duration.ofSeconds(30)).GET().build();
        var response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()!=200)throw new IOException("A API retornou HTTP "+response.statusCode());
        return converter(response.body());
    }
    public RespostaCarrinhos converter(String json) throws IOException {
        RespostaCarrinhos resposta=new ObjectMapper().readValue(json,RespostaCarrinhos.class);
        if(resposta==null||resposta.getCarts()==null||resposta.getCarts().stream().anyMatch(c->c==null||c.getProducts()==null||c.getProducts().stream().anyMatch(p->p==null)))
            throw new IOException("JSON sem a lista carts/products válida.");
        return resposta;
    }
}
