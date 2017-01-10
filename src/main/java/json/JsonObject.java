package json;
import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    HashMap<String, Json> pairs;
    String str = "";

    public JsonObject(JsonPair... jsonPairs) {
        this.pairs = new HashMap<>();
        for (JsonPair pair: jsonPairs){
            add(pair);
        }

        System.out.println(pairs);
    }

    public boolean if_empty(){
        if(pairs.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toJson() {
        if (if_empty()) {
            return str += "{}";
        }
        Set set = pairs.entrySet();
        str += "{";
        for (Object i : set) {
            Map.Entry pairee = (Map.Entry) i;
            str += '\'' ;
            str += pairee.getKey().toString();
            str+= '\'' ;
            str += ": ";
            if (pairee.getValue() instanceof Json) {
                str += ((Json) pairee.getValue()).toJson() ;
                str += ',';
            }
        }
        str = str.substring(0, str.length() - 1) + '}';
        return str;
    }




    public void add(JsonPair jsonPair) {
        pairs.put(jsonPair.getKey(), jsonPair.getValue());
    }

    public Json find(String name) {
        return pairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsob = new JsonObject();
        for ( String name: names) {
            if (pairs.get(name) != null){
                new JsonPair(name, pairs.get(name));
            }
        }
        return jsob;
    }
}
