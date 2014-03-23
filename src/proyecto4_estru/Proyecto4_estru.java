
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
<<<<<<< HEAD
=======
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> 7bc926c5161c0d4a4551c676a4fdb38dd3a4227f
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
<<<<<<< HEAD
  //comienzo del ejemplo
        g.addEdge("ruta1","g" ,"x");
         g.addEdge("ruta2","g" ,"y");
          g.addEdge("ruta3","v" ,"y");
           g.addEdge("ruta4","x" ,"z");
           g.addEdge("ruta5","x" ,"d");
           g.addEdge("ruta6","x" ,"e");
           g.addEdge("ruta7","z" ,"q");
        ArrayList<nodo> topo = toposort(g);
        /*
        System.out.println(topo.size());
        for(int i=0;i<topo.size();i++){
            System.out.println("Nodo Actual: "+topo.get(i).label);
            for (int i2 = 0; i2 < topo.get(i).ar.size(); i2++) {
                System.out.println("Requerido para "+topo.get(i).label+ " "+ topo.get(topo.get(i).ar.get(i2)).label);
            }
        }//fin del for
         
        for(int i=0;i<topo.size();i++){
            System.out.println(topo.get(i).label);
        }//fin del for
        
        */
         Layout l = new FRLayout(g);
        BasicVisualizationServer<String, String> vv
                = new BasicVisualizationServer<String, String>(l);
=======
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
                                    for(int i=2;i<tokens.length;i++)
                                        for(int j=0;j<clases.size();j++)
                                             if(tokens[i].equals(clases.get(j).getCod()))
                                                requisito.add(clases.get(j));
                    
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
                               String espacio="";
                           for(int i=0;i<clases.size();i++){
                           for(int i2=0;i2<clases.get(i).getRequisito().size();i2++){
                               for(int i3=0;i3<cont;i3++){
                                   espacio+=" ";
                               }//fin del for
                               
                           g.addEdge(espacio, clases.get(i).getRequisito().get(i2).getName() ,clases.get(i).getName() );
                           cont++;
                           espacio="";
                           }
                       }
                       
                      execute(g);
                      
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
    
     public static void execute(Graph g){
        ArrayList<nodo> topo = toposort(g);
        Layout l = new FRLayout(g);
        
        BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<String, String>(l);
>>>>>>> 7bc926c5161c0d4a4551c676a4fdb38dd3a4227f
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame jf = new JFrame();
        vv.setPreferredSize(new Dimension(350, 350));
        JFrame frame = new JFrame("Directed Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
       //fin del ejemplo
        
        //codigo principal
<<<<<<< HEAD
        ArrayList<nodo>no;
        no=new ArrayList();
     for(int i=0;i<topo.size();i++){
         if(topo.get(i).ar.isEmpty()){
             System.out.print(topo.get(i).label+" ");
             no.add(topo.get(i));
         }//fin del if
     }//fin del for
        boolean marca=false;
        System.out.println("");
     ArrayList<nodo>meter;
     meter=new ArrayList();
     ArrayList<Integer>in;
     in=new ArrayList();
     ArrayList<String>impresion;
     impresion=new ArrayList();
     for(int i3=0;i3<no.size();i3++){
     for(int i=0;i<topo.size();i++){
         for(int i2=0;i2<topo.get(i).ar.size();i2++){
            
             if(no.isEmpty()==false){
                 if(no.contains(topo.get(topo.get(i).ar.get(i2)))){
                     if(meter.contains(topo.get(i))==false){
                         System.out.print(topo.get(i).label+" ");
                     meter.add(topo.get(i));
                     }
                 }//fin del if
             }//fin del if
         }//fin del for
         
     }//fin del for
        no.clear();
        for(int i4=0;i4<meter.size();i4++){
            no.add(meter.get(i4));
        }
        meter.clear();
         System.out.println("");
     }//fin del tercer for
     
     
    }//fin del main
=======
        ArrayList<nodo>no=new ArrayList();
        for(int i=0;i<topo.size();i++){
            if(topo.get(i).ar.isEmpty()){
                System.out.print(topo.get(i).label+" ");
                no.add(topo.get(i));
            }//fin del if
        }//fin del for
        boolean marca=false;
        System.out.println("");
        ArrayList<nodo>meter=new ArrayList();
        ArrayList<Integer>in=new ArrayList();
        ArrayList<String>impresion=new ArrayList();
        for(int i3=0;i3<no.size();i3++){
            for(int i=0;i<topo.size();i++){
                for(int i2=0;i2<topo.get(i).ar.size();i2++){
                    if(no.isEmpty()==false){
                         if(no.contains(topo.get(topo.get(i).ar.get(i2)))){
                            if(meter.contains(topo.get(i))==false){
                                System.out.print(topo.get(i).label+" ");
                                meter.add(topo.get(i));
                            }
                        }//fin del if
                    }//fin del if
               }//fin del for
         
            }//fin del for
            no.clear();
            for(int i4=0;i4<meter.size();i4++){
                no.add(meter.get(i4));
            }
            meter.clear();
            System.out.println("");
       }//fin del tercer for
    }
    
>>>>>>> 7bc926c5161c0d4a4551c676a4fdb38dd3a4227f
    
    //orden topologico
    public static ArrayList<nodo> toposort(Graph g){
        ArrayList<nodo>st=new ArrayList();
        Collection vertices = g.getVertices();
        Object[] vertex = vertices.toArray();
        for(int i=0;i<vertex.length;i++){
           st.add(new nodo(vertex[i].toString()));
        }//fin del for     
        String back="";
        for(int i=0;i<vertex.length;i++){
            Collection inEdges = g.getInEdges(vertex[i].toString());
            Object[] In = inEdges.toArray();
                for(int i2=0;i2<In.length;i2++){
                    Pair endpoints = g.getEndpoints(In[i2].toString());
                    if(!endpoints.getFirst().toString().equals(st.get(i).label)){
                        int x=finder(st,endpoints.getFirst().toString());
                        st.get(i).ar.add(x);
                    }//fin del if
                    if(!endpoints.getSecond().toString().equals(st.get(i).label)){
                        int x=finder(st,endpoints.getSecond().toString());
                        st.get(i).ar.add(x);
                     }//fin del if
                }//fin del segundo for
              
        }//fin del for
       
        return st;
    }
    
    public static int finder(ArrayList<nodo>ar, String x){
      
        for(int i=0;i<ar.size();i++){
            if(ar.get(i).label == null ? x == null : ar.get(i).label.equals(x)){
                return i;
            }
        }
        return -1;
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
