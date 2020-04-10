import java.io.File;

public class main {

    static String path = "./datas/";
    static String in = "datalog.txt";
    static String out = "result_"+in;

    public static void main(String[] args){
        File entryFile = new File(path+"input/"+in);
        if(!entryFile.exists()) {
            System.out.println("Fichier datalog introuvable");
            System.exit(-1);
        }
        Stratificate problem = new Stratificate(entryFile, path+"results/"+out);
        //uncomment to print parsed data
        //problem.printData();
        problem.solve();
    }
}
