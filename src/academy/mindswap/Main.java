package academy.mindswap;

import academy.mindswap.google.RealGoogle;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //RealGoogle realGoogle = new RealGoogle();

        //String result= realGoogle.doQuery("Diogo");

       Google google = new Google();
        /* String result = google.doQuery("Diogo");

        System.out.println(result);

        String resultCached = google.doQuery("Diogo");
        System.out.println(resultCached);*/

        google.addBannedSearches("vaping");

        String resultBanned= google.doQuery("vaping");
        System.out.println(resultBanned);



    }
}
