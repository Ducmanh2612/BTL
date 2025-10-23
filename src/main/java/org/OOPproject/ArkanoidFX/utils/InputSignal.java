package org.OOPproject.ArkanoidFX.utils;

public enum InputSignal {
    // Paddle movement
    MOVE_LEFT,      // Start moving paddle left
    MOVE_RIGHT,     // Start moving paddle right
    STOP_LEFT,      // Stop moving paddle left
    STOP_RIGHT,     // Stop moving paddle right
    
    // Game control
    PAUSE_RESUME,   // Toggle pause state
    SPACE,          // Start game, release ball, etc.
    ESCAPE,         // Return to menu
}
