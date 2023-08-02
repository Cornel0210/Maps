package mapsPack;

import java.util.HashMap;
import java.util.Map;

public class Town{
    private final String name;
    private final Map<Town, Road> neighbours = new HashMap<>();

    public Town(String name) {
        this.name = name;
    }

    public void addNeighbour(Town town, Road road){
        neighbours.putIfAbsent(town, road);
        if (!town.getNeighbours().containsKey(this)){
            town.addNeighbour(this, road);
        }
    }

    public boolean isClosed(Town destination){
        return neighbours.get(destination).isClosed();
    }

    public int getTime(Town destination){
        return neighbours.get(destination).getTime();
    }

    public int getDistance(Town destination){
        return neighbours.get(destination).getDistance();
    }

    public String getName() {
        return name;
    }

    public Map<Town, Road> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Town town = (Town) o;

        return getName().equals(town.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        for (Town t : neighbours.keySet()){
            sb.append("\t-> ").append(t).append("-").append(neighbours.get(t)).append("\n");
        }
        System.out.print(sb);
    }

    @Override
    public String toString() {
        return name;
    }
}