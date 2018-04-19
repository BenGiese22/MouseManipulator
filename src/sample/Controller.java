package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.net.URL;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Fields
    public Button clickButton;
    public TextField timeInput;
    public Button mouseInputButton;
    public Button deletePathButton;
    public TextArea clickPathTextArea;
    public Button executePathButton;

    private ClickPath clickPath = new ClickPath();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //Initializer

    }

    /**
     * Takes Coordinates entered in the textBox and clicks mouse at that location.
     * @throws AWTException
     */
    public void clickButton() throws AWTException {
        System.out.println("Button Press");
        String timeInputText = timeInput.getText();
        if(timeInputText.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please input values");
            return;
        }
        String[] values = timeInputText.split(" ");
        int x = 960;
        int y = 540;
        try {
            x = Integer.parseInt(values[0]);
            y = Integer.parseInt(values[1]);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Not valid numbers");
            return;
        }
        Robot bot = new Robot();
        bot.mouseMove(x,y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * Grabs mouse coordinates from it's location 3 seconds after Button Press.
     * @throws AWTException
     */
    public void mouseInputButton() throws AWTException {
        System.out.println("Input Press");
        Robot robot = new Robot();
        robot.delay(3000);
        Point p = MouseInfo.getPointerInfo().getLocation();
        int x = p.x;
        int y = p.y;
        timeInput.setText(x + " " + y);
        clickPath.addPath(x,y);
        updatePathList();
    }

    public void executePathButton() throws AWTException {
        System.out.println("Execute Path Button Press");
        Robot robot = new Robot();
        int x = 0;
        int y = 0;
        LinkedList<String> coords = clickPath.getCoordinates();
        for(String temp : coords) {
            String[] split = temp.split(" ");
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            robot.mouseMove(x,y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(2000);
        }

    }

    public void deletePathButton() {
        try {
            clickPath.remove();
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Nothing to Remove");
        }
        updatePathList();
    }

    public void updatePathList() {
        LinkedList<String> coords = clickPath.getCoordinates();
        String displayString = "";
        for(String temp : coords) {
            displayString += temp + "\n";
        }
        clickPathTextArea.setText(displayString);
    }

    //TODO Figure out how to always wait for mouse click, when clicked, right click.
}
