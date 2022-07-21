import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudoJava{

    public List<Conteudo> extraiConteudos(String json){

        //Extrair só os dados que interessam (titulo, poster, classificaçao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //Popular a lista de conteúdos
        for(Map<String, String> atributos: listaDeAtributos){
            String titulo = atributos.get("title");
            String urlImage = atributos.get("image")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            var conteudo = new Conteudo(titulo, urlImage);

            conteudos.add(conteudo);
        }

        return conteudos;

    }
}

