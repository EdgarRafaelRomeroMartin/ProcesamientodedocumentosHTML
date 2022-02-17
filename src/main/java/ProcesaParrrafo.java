import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

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
            String Parafo = new String(data);
            System.out.println("El parrafo numero "+contador2);
            String palabras[] = Parafo.split(" ");
            n1 += palabras.length;
            System.out.println("Cuenta con una cantidad de "+n1+" palabras \n");

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
