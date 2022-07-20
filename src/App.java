import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_6jlyr260";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Pegar só os dados que interessam (Título, poster do filme,classificação)
        var parser = new JsonParser();
        
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 
        
        //Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(Map<String, String> filme : listaDeFilmes){

            String urlImagem = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeDoArquivo = title + ".png";

            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(title);
            System.out.println();
        }
    }
}
