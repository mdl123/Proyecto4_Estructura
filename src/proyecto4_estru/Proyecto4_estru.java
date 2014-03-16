
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Mike
 */
public class Proyecto4_estru {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       

        //hay ponerle algo a las aristas como ruta1, ruta 2 , etc
        Graph<String, String> g = new DirectedSparseMultigraph<String, String>();
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
    
    //orden topologico
    public static ArrayList<nodo> toposort(Graph g){
        ArrayList<nodo>st;
        st=new ArrayList();
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
    
}//fin de la clase
