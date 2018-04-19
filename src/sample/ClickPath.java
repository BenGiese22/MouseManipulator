package sample;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class ClickPath {

    private LinkedList<String> coordinates = new LinkedList<>();

    public void addPath(int x, int y) {
        String temp = x + " " + y;
        coordinates.add(temp);
    }

    public void remove() throws NoSuchElementException {
        coordinates.removeLast();
    }

    public void size() {
        coordinates.size();
    }

    public LinkedList<String> getCoordinates() {
        return coordinates;
    }
}
