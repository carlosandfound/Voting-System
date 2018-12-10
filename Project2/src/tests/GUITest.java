/*
 * File: GUITest.java
 *
 * Description: this file contains the unit tests for all public methods within the GUI class.
 *
 * Authors: Carlos Alvarenga
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GUITest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = new GUI();
    }

    @Test
    void userInterfaceConstructor() {
        assertDoesNotThrow(() -> {
            gui = new GUI();
        });
    }

    @Test
    void getUserInput() {
        assertEquals("", gui.getUserInput());
    }

    @Test
    void setUserInput() {
        gui.setUserInput("filename");
        assertEquals("filename", gui.getUserInput());
    }
}
