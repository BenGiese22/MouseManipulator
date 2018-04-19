package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Fields
    public Button clickButton;
    public TextField timeInput;
    public Button mouseInputButton;

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
    }

    //TODO Figure out how to always wait for mouse click, when clicked, right click.
}
