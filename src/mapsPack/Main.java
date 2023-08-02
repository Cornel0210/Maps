package mapsPack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Country greatBritain = new Country();
        greatBritain.loadLinks(FileInput.getInstance().getInput());

        /*greatBritain.loadLinks(
                "London - Birmingham - 190 - 130 - false - false\n" +
                        "London - Cambridge - 98 - 90 - false - false\n" +
                        "Cambridge - Birmingham - 160 - 110 - false - false\n" +
                        "Cambridge - Oxford - 164- 90 - false - false\n" +
                        "Birmingham - Oxford - 130 - 90 - false - false\n" +
                        "Oxford - London - 96 - 90 - false - false\n" +
                        "London - Eastbourne - 135 - 90 - false - false\n" +
                        "Birmingham - Manchester - 138 - 90 - false - true\n" +
                        "Manchester - Liverpool - 56 - 90 - false - false\n" +
                        "Manchester - Glasgow - 347 - 90 -  false - true\n" +
                        "Liverpool - Glasgow - 352 - 90 - false - false\n" +
                        "Manchester - Leeds - 70 - 90 - false - false\n" +
                        "Leeds - Sheffield - 57 - 90 - false - false\n" +
                        "Sheffield - Nottingham- 72 - 90 - false - false\n" +
                        "Leicester - Nottingham - 43 - 90 - false - false\n" +
                        "Leicester - Birmingham - 70 - 90 - false - true\n");*/

        greatBritain.print();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Routes identified:");
        Map<List<Town>, LinkedList<Integer>> routes = greatBritain.getRoutes("London", "Oxford");
        List<Town> best = new LinkedList<>();
        int totalDistance = Integer.MAX_VALUE;

        for (List<Town> route : routes.keySet()){
            route.forEach(element-> System.out.print(element + " "));
            System.out.println(" -> " + routes.get(route).get(0) + "km in " +
                    routes.get(route).get(1)/60 + "h." + routes.get(route).get(1)%60 + "min");
            if (routes.get(route).get(0) < totalDistance){
                totalDistance = routes.get(route).get(0);
                best.clear();
                best.addAll(route);
            }
        }

        System.out.println("----------------------------------------------------------------");
        if (!best.isEmpty()){
            System.out.println("Shortest route is: ");
            System.out.print(best + " -> ");
            System.out.println(routes.get(best).get(0) + " km in " +
                    routes.get(best).get(1)/60 + "h." + routes.get(best).get(1)%60 + "min");
        } else {
            System.out.println("You can`t go to selected destination.");
        }
    }
}
