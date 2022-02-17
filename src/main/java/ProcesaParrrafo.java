import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProcesaParrrafo  extends HTMLEditorKit.ParserCallback {

    private int contador ;
    private int contador2 ;
    private boolean inParagraph;

    public ProcesaParrrafo() {
        contador = 0;
        contador2 = 0;
        inParagraph = false;

    }

    @Override
    public void handleText(char[] data, int pos) {

        int n1 = 0;
        if( inParagraph ) {
            contador2++;
            int u=0;
            int i=0;
            int r=0;
            String Parafo = new String(data);
            Set<String> Stop = new HashSet<String>(Arrays.asList("stop-word-list.txt"));
            Set<String> unicas = new HashSet<String>();
            Set<String> duplicadas = new HashSet<String>();
            System.out.println("El parrafo numero "+contador2);
            String palabras[] = Parafo.split(" ");
            n1 += palabras.length;
            System.out.println("Cuenta con una cantidad de "+n1+" palabras");
            for (String theWord : palabras) {

                if (!Stop.contains(theWord)) {
                    u++;
                    if (!unicas.add(theWord)){
                        duplicadas.add(theWord);
                        r++;
                    }
                    unicas.removeAll(duplicadas);
                }
                i =u-r;
                unicas.removeAll(duplicadas);

            }
            System.out.println("Parrafo despues de eliminar stopwords");
            System.out.println(unicas);
            System.out.println("Cuenta con una cantidad de "+i+" palabras uicas \n");
        }

    }

    @Override
    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
        if( t == HTML.Tag.P ) {
            inParagraph = true;
        }
    }

    @Override
    public void handleEndTag(HTML.Tag t, int pos) {
        if( t == HTML.Tag.P ) {
            inParagraph = false;
            contador++;

        }
        if( t == HTML.Tag.BODY ) {
            System.out.printf("Total de parrafos en el documento: %d%n", contador);
        }
    }

}
