/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto4_estru;

import java.util.ArrayList;

/**
 *
 * @author Michael and Denisse 
 */
public class Clase {//Representa una clase
    private String cod;// Almacenara el código de la clase
    private String name;//Almacenara el nombre de la clase
    private ArrayList<Clase> requisito;//Almacenara el conjunto de clases que son requisito para la clase actual

    public Clase() {//Constructor vacio
    }
    public Clase(String cod, String name, ArrayList<Clase> requisito) {//Constructor sobrecargado
        this.cod = cod;
        this.name = name;
        this.requisito = requisito;
    }
    //Mutadores de los atributos
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Clase> getRequisito() {
        return requisito;
    }

    public void setRequisito(ArrayList<Clase> requisito) {
        this.requisito = requisito;
    }

    @Override
    public String toString() {
        return cod+","+name+","+"{"+requisito+"}";
    }
    
    
    
    
    
    
}
