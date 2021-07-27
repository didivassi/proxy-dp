package academy.mindswap;

import academy.mindswap.google.RealGoogle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Google implements GoogleProxy{

    RealGoogle realGoogle;
    Map<String,String> alreadyDoneQueries = new HashMap<>();
    Set<String> bannedSearches = new HashSet<>();


    @Override
    public String doQuery(String query) {

        if(realGoogle==null){ //lazy
            realGoogle =  new RealGoogle();
        }

        if(alreadyDoneQueries.containsKey(query)){//caching
            return "this is being cached".concat(alreadyDoneQueries.get(query));
        }

        if(bannedSearches.contains(query)){//security
            return "You cannot query about that ".concat(query);
        }

        String result =realGoogle.doQuery(query);
        alreadyDoneQueries.put(query,result);
        return result;
    }

    public void  addBannedSearches(String  searchToban ){
        bannedSearches.add(searchToban);
    }
}
