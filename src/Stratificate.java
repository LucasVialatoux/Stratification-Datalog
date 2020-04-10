import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Stratificate {

    private File file;
    private String out;
    private Set<String> edbs = new LinkedHashSet<>();
    private List<IDB> idbs = new ArrayList<>();

    public Stratificate(File file, String out){
        this.file = file;
        this.out = out;
        try {
            init();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void init() throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
        String line;
        boolean nextLineEDB = false;
        boolean nextLineIDB = false;
        while((line = bf.readLine()) != null){
            if(!line.isEmpty()){
                if(line.toLowerCase().replaceAll(" ","").equals("%edb")){
                    nextLineEDB = true;
                    nextLineIDB = false;
                }
                else{
                    if(line.toLowerCase().replaceAll(" ","").equals("%idb")){
                        nextLineIDB = true;
                        nextLineEDB = false;
                    }
                    else{
                        if(nextLineEDB){
                            edbs.add(line.split("\\(")[0]);
                        }
                        if(nextLineIDB){
                            idbs.add(new IDB(line));
                        }
                    }
                }
            }
        }
    }

    public void printData(){
        System.out.println("=== EDB LIST : ===");
        System.out.println(edbs);
        System.out.println("=== IDB LIST : ===");
        for(IDB i : idbs){
            System.out.println("-");
            i.printIDB();
        }
    }
}
