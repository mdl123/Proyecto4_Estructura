
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
    public static void main(String[] args) throws IOException {
        Scanner s=new Scanner(System.in);
    
        //hay ponerle algo a las aristas como ruta1, ruta 2 , etc
        Graph<String, String> g = new DirectedSparseMultigraph<String, String>();
        menu();
        String entrada=s.nextLine();
        ArrayList<Clase> clases = new ArrayList();
        ArrayList<Clase> clasesPasadas = new ArrayList();
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
                        System.out.println("Ingrese la cantidad de clases que desea llevar cada periodo, mayor de 1 y menor de 8");
                        numeroClases = s.nextInt();
                        if(numeroClases<1 || numeroClases>7){
                            System.out.println("Numero erroneo");   
                        }else{
                        File archivo = null;
                        File archivo2 =null;
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
                             buffer = new BufferedReader(new FileReader(archivo));
                             String linea;
                             try {
                                while(buffer.ready()){
                                    linea = buffer.readLine();
                                    String[] tokens = linea.split(",");
                                    String cod = tokens[0];
                                    String name = tokens[1];
                                    ArrayList<Clase> requisito = new ArrayList();
                                    for(int i=2;i<tokens.length;i++){
                                        for(int j=0;j<clases.size();j++){
                                             if(tokens[i].equals(clases.get(j).getCod())){
                                                requisito.add(clases.get(j));
                                             }else if(tokens[i].equals("todas")){
                                               for(int q=0;q<clases.size();q++){
                                                  requisito.add(clases.get(q));
                                                   
                                               }
                                             }
                                        }
                                    }
                                    clases.add(new Clase(cod,name,requisito));
                    
                                }
                            } catch (Exception e) {
                            }
                             try {
                                 buffer = new BufferedReader(new FileReader(archivo2));
                                while(buffer.ready()){
                                    linea = buffer.readLine();
                                    String[] tokens = linea.split(",");
                                    String cod = tokens[0];
                                    String name = tokens[1];
                                    ArrayList<Clase> requisito = new ArrayList();
                                    
                                    clasesPasadas.add(new Clase(cod,name,requisito));
                    
                                }
                            } catch (Exception e) {
                            }
                       } catch (Exception k) {
                       }
                       try{
                            buffer.close();
                       }catch(Exception h){
                       }
                       
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
                      
                         OrdenTopologico(clases,numeroClases);   
                         System.out.println("");
                        }
                        }catch(Exception w){
                            System.out.println("Solo Ingrese numeros");
                        }
                       
                   }
                    
                 }            
                       
                 menu();
                
                 s = new Scanner(System.in);
                 entrada=s.nextLine();
                 
                
        }while(!"2".equals(entrada));
        System.exit(0);
       
       
      
  }//fin del main
    
   public static void OrdenTopologico(ArrayList<Clase> clases, int cantidad){
       ArrayList<Clase> periodo = new ArrayList();
       ArrayList<Clase> anteriores = new ArrayList();
       while(!clases.isEmpty()){
           System.out.println("");
           for(int o=0;o<periodo.size();o++){
               anteriores.add(periodo.get(o));
           }
           periodo.clear();
           for(int j=0;j<clases.size();j++){
            if(periodo.size()<cantidad){
                if(clases.get(j).getRequisito().isEmpty()){
                    periodo.add(clases.get(j));
                    clases.remove(j);
                }else{
                    int con=0;
                    for(int h=0;h<clases.get(j).getRequisito().size();h++){
                       for(int k=0;k<anteriores.size();k++){
                          if(clases.get(j).getRequisito().get(h).getCod().equals(anteriores.get(k).getCod())){
                            con++;
                       
                           }
                       }
                    }
                    if(con==clases.get(j).getRequisito().size()){
                      periodo.add(clases.get(j));
                      clases.remove(j);
                   }
                   
               }
           }
          }
           
           
         for(int i=0;i<periodo.size();i++){
             System.out.print(" "+"|"+" "+periodo.get(i).getName()+" "+"|");
         }
       
       }
       
   }
    
    
    public static void menu(){
        System.out.println("");
        System.out.println("Menu principal");
        System.out.println("1.-Ingresar Carrera");
        System.out.println("2.-Salir");
    }
    
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
