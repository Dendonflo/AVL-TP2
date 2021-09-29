import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public final class ProgramTrace {
    private Trace trace = null;
    private Trace firstTrace = null;
    private Trace lastTrace = null;

    private ProgramTrace(){
    }

    public void addTrace(String methodName, Map<String,String> variables){
        if(trace == null){
            trace = new Trace(methodName,variables);
            firstTrace = trace;
            lastTrace = trace;
        }
        else{
            lastTrace.setNextTrace(new Trace(methodName,variables));
            Trace temp = lastTrace;
            lastTrace = lastTrace.next();
            lastTrace.setPreviousTrace(temp);
        }
    }

    public void next(){
        if(trace.next() == null){
            System.out.println("/!\\ Derniere trace deja atteinte !");
            return;
        }
        trace = trace.next();
        System.out.println("Trace selectionnee dans la fonction: "+trace.methodName);

    }

    public void previous(){
        if(trace.previous() == null){
            System.out.println("/!\\ Premiere trace deja atteinte !");
            return;
        }
        trace = trace.next();
        System.out.println("Trace selectionnee dans la fonction: "+trace.methodName);

    }

    public void saveTraces(String filePath) throws IOException {
        // Si aucune trace n'est generee, on refuse l'enregistrement du fichier.
        if(trace == null){
            System.out.println("Aucune trace n'a encore ete creee !\nRien a enregistrer.");
            return;
        }
        // On ouvre un fichier en ecriture, et pour chaque trace en partant de la 1ere, on ecrit sa
        // representation textuelle dans le fichier.
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        Trace ptr = firstTrace;
        while(ptr != null){
            writer.write(trace.toString());
            ptr = trace.next();
        }
        writer.close();
    }

}
