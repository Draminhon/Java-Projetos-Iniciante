import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do usuário");
        String username = sc.nextLine();
        System.out.println("Output:\n ");
        Map<String, HashMap<String, Integer>> mapa = new HashMap<String, HashMap<String, Integer>>();
        int push, pull, delete;
        try {
            GitHubModel[] model =  GitConsumer.searchUserBy(username);

            for (GitHubModel gitHubModel : model) {
                if (!mapa.containsKey(gitHubModel.getRepo().getUrl())) {
                    HashMap<String, Integer> values = new HashMap<String, Integer>();
                    values.put(gitHubModel.getType().substring(0, gitHubModel.getType().indexOf("Event")), 1);
                    mapa.put(gitHubModel.getRepo().getUrl(), values);
                } else {
                   HashMap<String, Integer> eventoAtual = mapa.get(gitHubModel.getRepo().getUrl());
                    int valorAtual =  eventoAtual.getOrDefault(gitHubModel.getType().substring(0, gitHubModel.getType().indexOf("Event")), 0);
                  eventoAtual.put(gitHubModel.getType().substring(0, gitHubModel.getType().indexOf("Event")), valorAtual + 1);
                }



            }



            mapa.forEach((s, stringIntegerHashMap) ->
                    stringIntegerHashMap.forEach((s1, integer) -> {
                        switch (s1){
                            case "Push":
                                System.out.println("- Pushed " +integer+ " commits at " + s );
                                break;
                            case "Create":
                                System.out.println("- Created " + s );
                                break;
                            case "PullRequest":
                                System.out.println("- Opened a Pull Request at " + s );
                                break;
                            case "Delete":
                                System.out.println("- Deleted " + s );
                                break;
                        }
            }));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    static String containsPushPullDelete(List<String> list){
        System.out.println(list);
        return "";
    }
}
