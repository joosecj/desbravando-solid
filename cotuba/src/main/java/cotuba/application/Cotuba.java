package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
@Component
public class Cotuba {
    private final RenderizadorMDParaHTML renderizadorMDParaHTML;
    private final GeradorPDF geradorPDF;
    private final GeradorEPUB geradorEPUB;

    public Cotuba(RenderizadorMDParaHTML renderizadorMDParaHTML, GeradorPDF geradorPDF, GeradorEPUB geradorEPUB) {
        this.renderizadorMDParaHTML = renderizadorMDParaHTML;
        this.geradorPDF = geradorPDF;
        this.geradorEPUB = geradorEPUB;
    }


    public void executa(ParametrosCotuba params) {
        Path diretorioDosMD = params.getDiretorioDosMD();
        String formato = params.getFormato();
        Path arquivoDeSaida = params.getArquivoDeSaida();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);


        if ("pdf".equals(formato)) {
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

    }
}
