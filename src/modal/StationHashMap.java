package modal;

import java.util.ArrayList;

public class StationHashMap {

    private ArrayList<StationEntry>[] map;
    private int count;

    public StationHashMap(int length){
        this.map = new ArrayList[length];
        this.count = 0;
    }

    public boolean isEmpty(){
        return getCount()==0;
    }

    public int getCount(){
        return this.count;
    }

    public boolean containsKey(String key){

        int index = hashFunction(key);

        ArrayList<StationEntry> list = map[index];
        if(list == null) {
            list = new ArrayList<>();
            map[index] = list;
        }
        return false;
    }

    public Station put(Station station){
        if(count*100/map.length>75) {
            remap();
        }

        StationEntry entry = new StationEntry(station);

        int index = hashFunction(entry.getKey());

        // get or create list number (index)
        ArrayList<StationEntry> list = map[index];
        list.add(entry);

        // check if list already contains an entry for this key (remove it and return it value


        return null;
    }

    private void remap() {

        ArrayList<StationEntry> []temp = map;
        map = new ArrayList[map.length*2];
        // read all elemenets from the old map after

    }

    public int hashFunction(String key){
        return hashFunction(key, map.length);
    }

    public static int hashFunction(String key, int module) {
        int result = 0;

        for (int i = 0; i < key.length(); i++) {
            int current = key.charAt(i);
            result = result +(current *(i+1));
        }

//        result = key.hashCode() % map.length;
        result = result % module;

        if (result ==1) {
            result = result + module;
        }

        return result;


    }

    public Station get(String key){
       int index = hashFunction(key);

       ArrayList<StationEntry> list = map[index];
       if(list == null){
           return null;
       }
       else {
           return null;
       }
    }

    public Station remove(String key){
        return null;
    }

}
