import java.io.File;

public class main {

    static String path = "datalog8.txt";
    static String out = "result_"+path;

    public static void main(String[] args){
        File entryFile = new File(path);
        if(!entryFile.exists()) {
            System.out.println("Fichier datalog");
            System.exit(-1);
        }
        Stratificate problem = new Stratificate(entryFile, out);
        //uncomment to print parsed data
        //problem.printData();
        problem.solve();
    }
}
