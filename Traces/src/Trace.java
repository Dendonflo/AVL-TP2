import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;

public class Trace {
    String methodName;
    Map<String,String> variables;

    // Definit la liste chainee ordonnee des traces suivantes et precedentes
    // (a configurer APRES la creation de ls trace)
    Trace nextTrace = null;
    Trace previousTrace = null;

    // Variables d'horodatage
    Locale loc = new Locale("fr", "FR");
    DateFormat date = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);


    public Trace(String methodName, Map<String,String> variables){
        this.methodName = methodName;
        this.variables = variables;
    }

    public void setNextTrace(Trace trace){
        this.nextTrace = trace;
    }

    public void setPreviousTrace(Trace trace){
        this.previousTrace = trace;
    }

    public Trace next(){
        return nextTrace;
    }

    public Trace previous(){
        return previousTrace;
    }

    public String toString(){
        String res = "";
        res += date.toString();
        for (Map.Entry<String,String> entry : variables.entrySet()){
            res += entry.getKey()+" = "+entry.getValue();
        }
        return res;
    }


}
