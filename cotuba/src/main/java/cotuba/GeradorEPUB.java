package cotuba;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.file.Files;

public class GeradorEPUB {

    public void gera(Ebook ebook) {
        var arquivoDeSaida = ebook.getArquivoDeSaida();
        var epub = new Book();

        var epubWriter = new EpubWriter();

        for (Capitulo capitulo : ebook.getCapitulos()) {
            String titulo = capitulo.getTitulo();
            String html = capitulo.getConteudoHTML();
            // TODO: usar título do capítulo
            epub.addSection(titulo, new Resource(html.getBytes(), MediatypeService.XHTML));
        }
        
        try {
            epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
        } catch (IOException ex) {
            throw new IllegalStateException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }
}
