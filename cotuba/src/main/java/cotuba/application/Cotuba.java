package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {
    public void executa(ParametrosCotuba params) {
        Path diretorioDosMD = params.getDiretorioDosMD();
        String formato = params.getFormato();
        Path arquivoDeSaida = params.getArquivoDeSaida();
        var renderizadorMDParaHTML = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);


        if ("pdf".equals(formato)) {
            GeradorPDF geradorPDF = GeradorPDF.cria();
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            GeradorEPUB geradorEPUB = GeradorEPUB.cria();
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

    }
}
