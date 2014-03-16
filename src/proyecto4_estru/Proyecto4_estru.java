
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
        ArrayList<Clase> clases = new ArrayList();
        File archivo = null;
        FileReader canal = null;
        BufferedReader buffer = null;
        try {
            archivo = new File("./SistemasComputacionales.txt");
            canal = new FileReader(archivo);
            buffer = new BufferedReader(canal);
            String linea;
            try {
                while(buffer.readLine()!= null){
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
       } catch (Exception k) {
       }
       try{
            buffer.close();
            canal.close();
       }catch(Exception h){
       }
      
       for(int i=0;i<clases.size();i++)
            System.out.println(clases.get(i));
    
       

        //hay ponerle algo a las aristas como ruta1, ruta 2 , etc
        Graph<String, String> g = new DirectedSparseMultigraph<String, String>();
  
        g.addEdge("ruta1","g" ,"x");
        g.addEdge("ruta2","g" ,"y");
        g.addEdge("ruta3","v" ,"y");
        g.addEdge("ruta4","x" ,"z");
        ArrayList<nodo> topo = toposort(g);
        System.out.println(topo.size());
        for(int i=0;i<topo.size();i++){
            System.out.println("Nodo Actual: "+topo.get(i).label);
            for (int i2 = 0; i2 < topo.get(i).ar.size(); i2++) {
                System.out.println("Requerido para "+topo.get(i).label+ " "+ topo.get(topo.get(i).ar.get(i2)).label);
            }
        }//fin del for
        
        Layout l = new FRLayout(g);
        BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<String, String>(l);
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
