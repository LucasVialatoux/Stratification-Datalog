import java.io.*;
import java.util.*;

public class Stratificate {

    private File file;
    private String out;
    private Set<String> edbs = new LinkedHashSet<>();
    private List<IDB> idbs = new ArrayList<>();

    public Stratificate(File file, String out) {
        this.file = file;
        this.out = out;
        try {
            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void init() throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
        String line;
        boolean nextLineEDB = false;
        boolean nextLineIDB = false;
        while ((line = bf.readLine()) != null) {
            if (!line.isEmpty()) {
                if (line.toLowerCase().replaceAll(" ", "").equals("%edb")) {
                    nextLineEDB = true;
                    nextLineIDB = false;
                } else {
                    if (line.toLowerCase().replaceAll(" ", "").equals("%idb")) {
                        nextLineIDB = true;
                        nextLineEDB = false;
                    } else {
                        if (nextLineEDB) {
                            edbs.add(line.split("\\(")[0]);
                        }
                        if (nextLineIDB) {
                            idbs.add(new IDB(line));
                        }
                    }
                }
            }
        }
    }

    public void printData() {
        System.out.println("=== EDB LIST : ===");
        System.out.println(edbs);
        System.out.println("=== IDB LIST : ===");
        for (IDB i : idbs) {
            System.out.println("-");
            i.printIDB();
        }
    }

    public void solve() {
        Map<String, Integer> stratum = new HashMap<>();
        Map<String, Integer> previousStratum = new HashMap<>(stratum);

        //adding each existing predicate
        for(String s : edbs){
            stratum.put(s, 1);
        }
        for (IDB i : idbs) {
            stratum.put(i.getHead(), 1);
        }

        int maxStratum=0;
        try {
            while (maxStratum < idbs.size() && !previousStratum.equals(stratum)) {
                //use to check if stratum changed or not
                previousStratum = new HashMap<>(stratum);

                //stratificiation algorithm
                for (IDB idb : idbs) {
                    for (Map.Entry<String, Boolean> entry : idb.getTails().entrySet()) {
                        int newStrat;
                        if (entry.getValue()) {
                            newStrat = (stratum.get(idb.getHead()) > 1 + stratum.get(entry.getKey())) ? stratum.get(idb.getHead()) : 1 + stratum.get(entry.getKey());
                        } else {
                            newStrat = (stratum.get(idb.getHead()) > stratum.get(entry.getKey())) ? stratum.get(idb.getHead()) : stratum.get(entry.getKey());
                        }
                        stratum.replace(idb.getHead(), newStrat);
                    }
                }

                //get max stratum for stopping condition
                Map.Entry<String, Integer> maxEntry = Collections.max(stratum.entrySet(), Map.Entry.comparingByValue());
                maxStratum = maxEntry.getValue();
            }
        }
        catch (NullPointerException e){
            System.out.println("Rule or relation inexistant.");
            System.out.println("Check your IDB and EDB.");
            System.exit(-1);
        }

        //cleaning to have only the rules
        for(String s : edbs){
            stratum.remove(s);
        }

        //writing to file
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.out));
            int i = 1;
            while(i<=maxStratum){
                writer.write("P"+i+" = {");
                writer.newLine();
                for (Map.Entry<String, Integer> entry : stratum.entrySet()) {
                    if (entry.getValue().equals(i)) {
                        for(IDB rules : idbs){
                            if(rules.getHead().equals(entry.getKey())){
                                writer.write("\t"+rules.getCompleteRule());
                                writer.newLine();
                            }
                        }
                    }
                }
                writer.write("}");
                writer.newLine();
                i++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("solution :" +stratum);
        }

    }
}
