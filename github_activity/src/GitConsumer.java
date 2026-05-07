import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GitConsumer {

    static String webService1 = "https://api.github.com/users/";
    static String WebService2 =  "/events";

    public static GitHubModel[] searchUserBy(String username)  {
        String urlToCall = webService1 + username + WebService2;

        try {
            URL url = new URL(urlToCall);
            URLConnection myUrlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myUrlConnection.getInputStream()));

            Gson gson = new Gson();
            GitHubModel[] model = gson.fromJson(in.readLine(), GitHubModel[].class);


            in.close();
            return model;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
