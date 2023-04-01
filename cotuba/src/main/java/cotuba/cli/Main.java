package cotuba.cli;

import cotuba.application.Cotuba;
import cotuba.CotubaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Path diretorioDosMD;
        String formato;
        Path arquivoDeSaida;
        boolean modoVerboso = false;
        try {
            var opcoesCLI = new LeitorOpcoesCLI(args);
            diretorioDosMD = opcoesCLI.getDiretorioDosMD();
            formato = opcoesCLI.getFormato();
            arquivoDeSaida = opcoesCLI.getArquivoDeSaida();
            modoVerboso = opcoesCLI.isModoVerboso();
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CotubaConfig.class);
            Cotuba cotuba = applicationContext.getBean(Cotuba.class);

            cotuba.executa(opcoesCLI);
            System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
    }

}
