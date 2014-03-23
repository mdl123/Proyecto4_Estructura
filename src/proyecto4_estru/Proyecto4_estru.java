
package proyecto4_estru;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Michael and Denisse
 */
public class Proyecto4_estru {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
    
        menu();
        String entrada=s.nextLine();
        ArrayList<Clase> clases = new ArrayList();//Almacenara inicialmente las clases del plan
        ArrayList<Clase> clasesPasadas = new ArrayList();//Almacenar las clases pasadas
        do{
                if("1".equals(entrada)){
                    System.out.println("Ingrese el numero de la carrera");
                    System.out.println("");
                    menu2();
                    entrada=s.nextLine();
                    int valor= Integer.parseInt(entrada);
                    if(valor<1||valor>6||entrada.length()>1){
                        System.out.println("Valor Incorrecto"); 
                        System.out.println("");
                    }//fin del if
                    else{
                        int numeroClases = 0;
                        try{
                        System.out.println("");
                        System.out.println("Ingrese la cantidad de clases que desea llevar cada periodo, mayor de 1 y menor de 8");
                        numeroClases = s.nextInt();//recibe la cantidad de clases por periodo deseadas
                        if(numeroClases<1 || numeroClases>7){
                            System.out.println("Numero erroneo");   
                        }else{
                        //Se leen los archivos de texto
                        File archivo = null;//archivo con todas las clases del plan
                        File archivo2 =null;//archivo con las clases pasadas del mismo plan
                        BufferedReader buffer = null;
                        try {
                                if(valor==1){
                                    archivo = new File("./SistemasComputacionales.txt");
                                    archivo2 = new File("./PasadasSistemas.txt");
                                }else if(valor==2){
                                    archivo = new File("./AdministracionEmpresasTuristicas.txt");
                                    archivo2 = new File("./PasadasAdministracionEmpresasTuristicas.txt");
                                }else if(valor==3){
                                    archivo = new File("./ComunicacionPublicidad.txt");
                                    archivo2 = new File("./PasadasComunicacionPublicidad.txt");
                                }else if(valor==4){
                                    archivo = new File("./Biomedica.txt");
                                    archivo2 = new File("./PasadasBiomedica.txt");
                                }else if(valor==5){
                                    archivo = new File("./Industrial.txt");
                                    archivo2 = new File("./PasadasIndustrial.txt");
                                }else if(valor==6){
                                    archivo = new File("./Finanzas.txt");
                                    archivo2 = new File("./PasadasFinanzas.txt");
                                }
                                //leer las clases del plan
                             buffer = new BufferedReader(new FileReader(archivo));
                             String linea;
                             try {
                                while(buffer.ready()){
                                    linea = buffer.readLine();
                                    String[] tokens = linea.split(",");
                                    String cod = tokens[0];//codigo de la clase
                                    String name = tokens[1];//nombre de la clase
                                    ArrayList<Clase> requisito = new ArrayList();//lista de clases requisito de la clase actual
                                    //compara los codigos de la clases almacenadas, con el codigo de las clase requisto para almacenarlas
                                    for(int i=2;i<tokens.length;i++){
                                        for(int j=0;j<clases.size();j++){
                                             if(tokens[i].equals(clases.get(j).getCod())){
                                                requisito.add(clases.get(j));
                                             }else if(tokens[i].equals("todas")){//si en el requisito esta todas, quiere decir que es practica
                                              //o proyecto final, entonces asigna como requisito todas las clases anteriores
                                               for(int q=0;q<clases.size();q++){
                                                  requisito.add(clases.get(q));
                                                   
                                               }
                                             }
                                        }
                                    }
                                    clases.add(new Clase(cod,name,requisito));//almacenar la clase en el arraylist clases
                    
                                }
                            } catch (Exception e) {
                                 System.out.println("No se pudo abrir el archivo");
                            }
                             try {
                                 //leer las clases pasadas
                                 buffer = new BufferedReader(new FileReader(archivo2));
                                while(buffer.ready()){
                                    linea = buffer.readLine();
                                    String[] tokens = linea.split(",");
                                    String cod = tokens[0];//codigo de la clase
                                    String name = tokens[1];//nombre de la clase
                                    ArrayList<Clase> requisito = new ArrayList();//crea una lista vacia de requisitos
                                    clasesPasadas.add(new Clase(cod,name,requisito));//almacena las clases pasadas
                    
                                }
                            } catch (Exception e) {
                                System.out.println("No se pudo abrir el archivo");
                            }
                       } catch (Exception k) {
                            System.out.println("Ingreso un valor equivocado");
                       }
                       try{
                            buffer.close();
                       }catch(Exception h){
                           System.out.println("Ingreso un valor equivocado");
                       }
                       //compara las clases pasadas con las clases del plan, para eliminar de la lista clases las 
                       //clases que ya fueron aprobadas, tambien elimina de las clases que quedan el requisito si es que 
                       //lo es de la clase aprobada
                       int cont=0;
                       for(int i=0;i<clasesPasadas.size();i++){
                           for(int j=0;j<clases.size();j++){
                               if(clases.get(j).getCod().equals(clasesPasadas.get(i).getCod())){
                                  clases.remove(j);
                               }else{
                                   for(int k=0;k<clases.get(j).getRequisito().size();k++){
                                     if(clases.get(j).getRequisito().get(k).getCod().equals(clasesPasadas.get(i).getCod())) {
                                        clases.get(j).getRequisito().remove(k);
                                     }
                                   }
                               }
                           }
                       }
                         //Una vez ingresados los datos, llama al metodo ordentopologico, para mostrar el plan con las clases restantes
                         OrdenTopologico(clases,numeroClases);   
                         System.out.println("");
                        }
                        }catch(Exception w){
                            System.out.println("Solo Ingrese numeros");
                        }
                       
                   }
                    
                 }            
                  //Muestra de nuevo el menu de los planes     
                 menu();
                 s = new Scanner(System.in);
                 entrada=s.nextLine();
                 
                
        }while(!"2".equals(entrada));
        System.exit(0);
       
       
      
  }//fin del main
    
   public static void OrdenTopologico(ArrayList<Clase> clases, int cantidad){
       ArrayList<Clase> periodo = new ArrayList();//lista que almacenara las clases que se llevaran cada periodo
       ArrayList<Clase> anteriores = new ArrayList();//lista para almacenar las que ya se van mostrando y sacando de la lista clases
       while(!clases.isEmpty()){//Mientras la lista clases este llena, se seguiran mostrando
           System.out.println("");
           for(int o=0;o<periodo.size();o++){//almacena las clases que se almacenaron en periodo temporalmente las copia en la lista de
               //clases ya mostradas
               anteriores.add(periodo.get(o));
           }
           periodo.clear();//limpia la lista periodo, para comenzar con las clases del siguiente
           for(int j=0;j<clases.size();j++){
            if(periodo.size()<cantidad){//indica que segun la cantidad de las clases por perido, se llene la lista periodo
                if(clases.get(j).getRequisito().isEmpty()){//si la clase no tiene requisito se puede llevar perfectamente
                    periodo.add(clases.get(j));
                    clases.remove(j);//se remueve la clase de clases, porque ya se almaceno temporalmente en perido es decir ya se tomo en cuenta y se mostrara mas adelante
                }else{
                    //si es que tiene lista de clases requisito, compara con las clases en la lista anteriores(antes tomadas en cuenta)
                    //tienen que estar todas sus clases requisto antes mistradas para poder ser tomada en cuenta
                    int con=0;
                    for(int h=0;h<clases.get(j).getRequisito().size();h++){
                       for(int k=0;k<anteriores.size();k++){
                          if(clases.get(j).getRequisito().get(h).getCod().equals(anteriores.get(k).getCod())){
                            con++;
                       
                           }
                       }
                    }
                    //Si todas las clases requisito ya estan en anteriores, la almacena temporalmente en perido y la remueve de clases
                    if(con==clases.get(j).getRequisito().size()){
                      periodo.add(clases.get(j));
                      clases.remove(j);
                   }
                   
               }
           }
          }
           
         //Imprime las clases que se deben llevar en ese perido 
         for(int i=0;i<periodo.size();i++){
             System.out.print(" "+"|"+" "+periodo.get(i).getName()+" "+"|");
         }
       }
       
   }
    
    //Metodo menu principal, muestra las opciones iniciales
    public static void menu(){
        System.out.println("");
        System.out.println("Menu principal");
        System.out.println("1.-Ingresar Carrera");
        System.out.println("2.-Salir");
    }
    //Metodo menu secundario, muestra las opciones de carreras
    public static void menu2(){
        System.out.println("");
        System.out.println("Escoja su carrera a continuacion:");
        System.out.println("1.-Ingeniera en Sistemas Computacionales");
        System.out.println("2.-Administracion de Empresas Turisticas");
        System.out.println("3.-Comunicacion y Publicidad");
        System.out.println("4.-Biomedica");
        System.out.println("5.-Ingeniera Industrial");
        System.out.println("6.-Finanzas");
       
    }
}//fin de la clase
