package cotuba.md;

import cotuba.domain.Capitulo;
import cotuba.pdf.GeradorPDF;
import cotuba.pdf.GeradorPDFImpl;

import java.nio.file.Path;
import java.util.List;

public interface RenderizadorMDParaHTML {
    List<Capitulo> renderiza(Path diretorioDosMD);

    static RenderizadorMDParaHTML cria() {
        return new RenderizadorMDParaHTMLImpl();
    }
}