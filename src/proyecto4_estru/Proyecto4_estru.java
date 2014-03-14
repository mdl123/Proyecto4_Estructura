
package proyecto4_estru;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mike
 */
public class Proyecto4_estru {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       

        DirectedGraph<String,String> g ;
        
        //hay ponerle algo a las aristas como ruta1, ruta 2 , etc
        
        
    }//fin del main
    
    //orden topologico
    public ArrayList<nodo> toposort(DirectedGraph g){
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
                       Pair endpoints = g.getEndpoints(In[i].toString());
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
    
    public int finder(ArrayList<nodo>ar, String x){
      
        for(int i=0;i<ar.size();i++){
            if(ar.get(i).label == null ? x == null : ar.get(i).label.equals(x)){
                return i;
            }
        }
        return -1;
    }
    
}//fin de la clase
