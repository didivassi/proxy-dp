package academy.mindswap.google;


import academy.mindswap.GoogleProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RealGoogle implements GoogleProxy {


    @Override
    public String doQuery(String query){

        try {
            return getSearchContent(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method will return the search page result in a {@link String} object
     *
     * @param googleSearchQuery the google search query
     * @return the content as {@link String} object
     * @throws Exception
     */
    private static String getSearchContent(String googleSearchQuery) throws Exception {
        //URL encode string in JAVA to use with google search
        System.out.println("Searching for: " + googleSearchQuery);
        googleSearchQuery = googleSearchQuery.trim();
        googleSearchQuery = URLEncoder
                .encode(googleSearchQuery, StandardCharsets.UTF_8.toString());
        String queryUrl = "https://api.scaleserp.com/search?api_key=44DFA736611049A9A3AC9DFC9B7E975C&q=" + googleSearchQuery;
        System.out.println(queryUrl);
        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        URL url = new URL(queryUrl);
        final URLConnection connection = url.openConnection();
        /**
         * User-Agent is mandatory otherwise Google will return HTTP response
         * code: 403
         */
        connection.setRequestProperty("User-Agent", agent);
        final InputStream stream = connection.getInputStream();
        return getPerfectJSON(getText(stream));

    }

    private static String getText(InputStream stream) {
        return new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }


    private static String getPerfectJSON(String unformattedJSON) {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        return GSON.toJson(JsonParser.parseString(unformattedJSON));
    }



}
