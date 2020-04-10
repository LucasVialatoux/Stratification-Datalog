import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IDB {

    private String head;
    private Map<String,Boolean> tails;
    private String completeRule;

    public IDB(String idb){

        try{
            this.completeRule = idb;
            this.head = idb.split(":-")[0].split("\\(")[0];
            this.tails = new HashMap<>();
            String tail = idb.split(":-")[1];
            if(!tail.equals("")){
                String[] tailSplit = tail.split("\\),");
                for(String s : tailSplit){
                    s = s.split("\\(")[0];
                    s = s.replace(" ","");
                    if(s.contains("not"))
                        addToTail(s.replace("not",""),true);
                    else{
                        addToTail(s,false);
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Incorrect IDB rule");
        }
    }

    public void addToTail(String name, boolean isNegated){
        tails.put(name,isNegated);
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Map<String, Boolean> getTails() {
        return tails;
    }

    public void setTails(Map<String, Boolean> tails) {
        this.tails = tails;
    }

    public void printIDB(){
        System.out.println("Head : " + head );
        System.out.println("Tail : ");
        for(Map.Entry<String,Boolean> entry : tails.entrySet()){
            System.out.println(entry.getKey() + " (" + entry.getValue()+")");
        }
    }

    public String getCompleteRule() {
        return completeRule;
    }

    public void setCompleteRule(String completeRule) {
        this.completeRule = completeRule;
    }
}
