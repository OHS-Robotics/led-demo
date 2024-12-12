// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /* example code starts here */
  // this variable holds the data that we send to the led. think of it like a list of colors that we tell the led to show. the 6 is the length of the led buffer.

  // this variable lets us interact with the LED at port 1. think of it as a pipe we can send commands through or something.
  AddressableLED led = new AddressableLED(1);
  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(6);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    /* This part is the example code */
    // the led needs to know how long it is for reasons unknown to me.
    led.setLength(6);

    for (int i = 0; i < 6; i += 1) {
      ledBuffer.setRGB(i, 255, 0, 0);
    }

    // this sets the colors that the led will use
    led.setData(ledBuffer);
    // this tells the led to start showing the colors in ledBuffer until we tell it to stop. since we never tell it to stop, it does this continuously.
    led.start();
  }
}
