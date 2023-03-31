package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.*;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {
    public void executa(Path diretorioDosMD, String formato, Path arquivoDeSaida) {
        var renderizadorMDParaHTML = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);


        if ("pdf".equals(formato)) {
            var geradorPDF = new GeradorPDF();
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            var geradorEPUB = new GeradorEPUB();
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

    }
}
