package org.example;

/**
 * Clase Libro creada para el Ejercicio3
 */

public class Libro {

    private String titulo;
    private String autor;
    private String ISBN;
    private int paginas;
    private int edicion;
    private String editorial;
    private int anyoEdicion;

    // Constructores

    public Libro(String titulo, String autor, String ISBN, int paginas, int edicion,
                 String editorial, int anyoEdicion){

        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.paginas = paginas;
        this.edicion = edicion;
        this.editorial = editorial;
        this.anyoEdicion = anyoEdicion;

    }

    // Getters

    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public int getPaginas(){
        return this.paginas;
    }

    public int getEdicion(){
        return this.edicion;
    }

    public String getEditorial(){
        return this.editorial;
    }

    public int getAnyoEdicion(){
        return this.anyoEdicion;
    }


    public String getData(String Data){
        if (Data.equals("titulo")){
            return getTitulo();
        }
        if (Data.equals("autor")){
            return getAutor();
        }
        if (Data.equals("isbn")){
            return getISBN();
        }
        if (Data.equals("paginas")){
            return String.valueOf(getPaginas());
        }
        if (Data.equals("edicion")){
            return String.valueOf(getEdicion());
        }
        if (Data.equals("editorial")){
            return getEditorial();
        }
        if (Data.equals("anyoEdicion")){
            return String.valueOf(getAnyoEdicion());
        }
        else{
            return "Error";
        }
    }
}
