/**************************************************************************
 * File name: Main
 *
 * Description: Main class
 *
 * Author: Thomas Murzaku
 *
 * Date: June 3rd, 2024
 *
 * Concepts: Object Oriented Programming
 * Input: None
 * Processing: Creates GUI, calls action method
 * Output: GUI
 ***************************************************************************/

public class Main {
  public static void main(String[] args) {

    Gui gui = new Gui();// Creating new instance of GUI object
    gui.actions();// Calling static action method

  }
}