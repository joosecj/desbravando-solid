package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.*;
import cotuba.md.RenderizadorMDParaHTMLImpl;
import cotuba.pdf.GeradorPDF;
import cotuba.pdf.GeradorPDFImpl;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {
    public void executa(Path diretorioDosMD, String formato, Path arquivoDeSaida) {
        var renderizadorMDParaHTML = new RenderizadorMDParaHTMLImpl();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);


        if ("pdf".equals(formato)) {
            GeradorPDF geradorPDF = new GeradorPDFImpl();
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            GeradorEPUB geradorEPUB = new GeradorEPUBImpl();
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

    }
}
