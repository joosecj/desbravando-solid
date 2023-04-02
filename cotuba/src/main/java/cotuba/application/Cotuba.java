package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHTMLComCommonMark;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba params) {
        Path diretorioDosMD = params.getDiretorioDosMD();
        String formato = params.getFormato();
        Path arquivoDeSaida = params.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTMLComCommonMark();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbook geradorEbook;
        if ("pdf".equals(formato)) {
            geradorEbook = new GeradorPDF();
        } else if ("epub".equals(formato)) {
            geradorEbook = new GeradorEPUB();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

        geradorEbook.gera(ebook);

    }
}
