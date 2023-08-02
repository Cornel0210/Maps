package mapsPack;

import java.util.*;

public class Country {
    private final Set<Town> towns;

    public Country() {
        towns = new HashSet<>();
    }

    private void addTown(Town town){
        towns.add(town);
    }

    private void linkTowns(String from, String to, Road road){
        Town t1 = findTown(from);
        Town t2 = findTown(to);
        if (t1 != null && t2 != null){
            t1.addNeighbour(t2, road);
        }
    }

    public void loadLinks(String input){
        for (String str : input.split("\\R")){
            String[] data = str.split("-");
            linkTowns(data[0].trim(), data[1].trim(),
                    new Road(Integer.parseInt(data[2].trim()),
                            Integer.parseInt(data[3].trim()),
                            Boolean.parseBoolean(data[4].trim().toLowerCase()),
                            Boolean.parseBoolean(data[5].trim().toLowerCase())));
        }
    }

    public void loadTowns(String input){
        String[] data = input.split(",");
        for (String str : data){
            this.addTown(new Town(str.trim()));
        }
    }

    public Map<List<Town>, LinkedList<Integer>> getRoutes(String from, String to){
        Town fromTown = findTown(from);
        Town toTown = findTown(to);
        Map<List<Town>, LinkedList<Integer>> routes = new HashMap<>();
        if (fromTown != null && toTown != null){
            getPaths(fromTown, toTown, new LinkedList<>(), new LinkedList<>(), 0,0, routes);
        }
        return routes;
    }

    private void getPaths (Town from, Town to, List<Town> visited, LinkedList<Town> route,int distance,
                           int time, Map<List<Town>, LinkedList<Integer>> lists){
        visited.add(from);
        route.add(from);
        if (from.equals(to)){
            LinkedList<Integer> details = new LinkedList<>();
            details.add(distance);
            details.add(time);
            lists.put(new LinkedList<>(route), details);
        }
        for (Town town : from.getNeighbours().keySet()){
            if (!visited.contains(town)) {
                if (!from.isClosed(town)){
                    time+= from.getTime(town);
                    distance+=from.getDistance(town);
                    getPaths(town, to, visited, route, distance, time, lists);
                    visited.remove(town);
                    route.removeLast();
                    time-=from.getTime(town);
                    distance-=from.getDistance(town);
                }
            }
        }
    }

    public void print(){
        for (Town town : towns){
            town.print();
        }
    }

    private Town findTown(String name){
        for (Town town : towns){
            if (town.getName().equalsIgnoreCase(name)){
                return town;
            }
        }
        return null;
    }
}

