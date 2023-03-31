package cotuba.epub;

import cotuba.domain.Ebook;
import cotuba.pdf.GeradorPDF;
import cotuba.pdf.GeradorPDFImpl;

public interface GeradorEPUB {
    void gera(Ebook ebook);

    static GeradorEPUB cria() {
        return new GeradorEPUBImpl();
    }
}
