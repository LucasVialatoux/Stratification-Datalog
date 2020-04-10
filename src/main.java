import java.io.File;

public class main {

    static String path = "datalog1.txt";
    static String out = "result.txt";

    public static void main(String[] args){
        File entryFile = new File(path);
        File outFile = new File(out);
        if(!entryFile.exists()) {
            System.out.println("Fichier datalog");
            System.exit(-1);
        }
        Stratificate problem = new Stratificate(entryFile, out);
        problem.printData();
    }
}
