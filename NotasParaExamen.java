package org.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class NotasParaExamen {
    /**
     * La direccion correcta del archivo es la opcion Path from Content Root al dar click derecho al archivo
     * Este fichero es el de entrada
     */
    private static final String FicheroE = "src/main/java/datos.txt";

    /**
     *  Este progama lee el fichero e imprime todas las palabras que tiene
     */
    public static void fileReader(){

        try {
            FileReader FileR = new FileReader(FicheroE);
            Scanner sc = new Scanner(FileR);
            while(sc.hasNext()){
                System.out.println(sc.next());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * La direccion correcta del archivo es la opcion Path from Content Root al dar click derecho al archivo
     * Este fichero es el de salida
     */
    private static final String FicheroS = "src/main/java/datos.txt";

    private static final String Imput = "Prueba para el file writer";

    public static void fileWriter(){

        try {
            FileWriter FileW = new FileWriter(FicheroS);
            FileW.write(Imput);
            FileW.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private static final String FicheroXMLW = "src/main/java/datos.xml";

    public static void XMLWriter() {

        ArrayList<Libro> biblioteca = cargarLibros();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            Document documento = dbf.newDocumentBuilder().newDocument();

            Node root = documento.createElement("biblioteca");
            documento.appendChild(root);

            String[] Etiquetas = {"libro", "titulo", "autor", "isbn", "paginas", "edicion", "editorial", "anyoEdicion"};
            Node[] Nodes = new Node[8];
            for (Libro l : biblioteca) {
                for (int i = 0; i < Etiquetas.length; i++) {
                    Nodes[i] = documento.createElement(Etiquetas[i]);
                }
                root.appendChild(Nodes[0]);

                for (int i = 1; i < Etiquetas.length; i++) {
                    Nodes[0].appendChild(Nodes[i]);
                    Nodes[i].appendChild(documento.createTextNode(String.valueOf(l.getData(Etiquetas[i]))));
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(documento), new StreamResult(FicheroXMLW));

        } catch (TransformerConfigurationException e) {
            System.out.println("TransformerConfiguration Error");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Libro> cargarLibros() {

        ArrayList<Libro> biblioteca = new ArrayList<>();

        biblioteca.add(new Libro("La comunidad del anillo", "J.R.R.Tolkien", "19-534-56-X",
                675, 13, "Planeta", 1978));
        biblioteca.add(new Libro("Danza de dragones", "George R.R.Martin","32-167-21-K",
                743, 8, "Salamandra", 2016));
        biblioteca.add(new Libro("El ingenioso hidalgo Don Quijote de la Mancha", "Miguel de Cervantes",
                "15-986-57-L", 465, 9, "Espasa", 1985));
        biblioteca.add(new Libro("Harry Potter y la piedra filosofal", "J.K.Rowling", "71-499-21-B",
                322, 11, "Salamandra", 2005));
        return biblioteca;

    }

    public static void main(String[] args) {
        fileWriter();
        fileReader();
        XMLWriter();
    }
}




