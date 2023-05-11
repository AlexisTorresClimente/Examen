/**
 * En el archivo Empleados.xml hay una serie de empleados. El objetivo es
 * disponer de esa información en un programa java para poder trabajar con
 * objetos en lugar de un archivo. Una vez importado de una manera correcta,
 * se mostrará la información de todos los empleados.
 */
package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class Filereader {


    public static String[] Etiquetas = {"libro", "titulo", "autor", "isbn", "paginas", "edicion", "editorial", "anyoEdicion"};


    public static void main(String[] args) {
        ArrayList<Libro> libros = leerLibros();
        for (Libro libro : libros) {
            for (int i = 1;i< Etiquetas.length; i++)
            System.out.println(Etiquetas[i]+": "+libro.getData(Etiquetas[i]));
        }
    }

    public static ArrayList<Libro> leerLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document documento = db.parse("src/main/java/biblioteca.xml");
            NodeList nodosLibros = documento.getElementsByTagName("libro");
            for (int i = 0; i < nodosLibros.getLength(); i++) {
                Node nodoLibro = nodosLibros.item(i);
                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    String titulo = getTextValue(nodoLibro, "titulo");
                    String autor = getTextValue(nodoLibro, "autor");
                    String isbn = getTextValue(nodoLibro, "isbn");
                    int paginas = Integer.parseInt(getTextValue(nodoLibro, "paginas"));
                    int edicion = Integer.parseInt(getTextValue(nodoLibro, "edicion"));
                    String editorial = getTextValue(nodoLibro, "editorial");
                    int anyoEdicion = Integer.parseInt(getTextValue(nodoLibro, "anyoEdicion"));
                    Libro libro = new Libro(titulo, autor, isbn, paginas, edicion, editorial, anyoEdicion);
                    libros.add(libro);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return libros;
    }
    private static String getTextValue(Node padre, String nombreHijo) {
        NodeList hijos = padre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            if (hijos.item(i).getNodeName().equalsIgnoreCase(nombreHijo)) {
                return hijos.item(i).getTextContent();
            }
        }
        return "";
    }
}
