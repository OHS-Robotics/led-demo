// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /* example code starts here */
  // this variable holds the data that we send to the led. think of it like a list of colors that we tell the led to show. the 6 is the length of the led buffer.
  private AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(6);
  // this variable lets us interact with the LED at port 1. think of it as a pipe we can send commands through or something.
  private AddressableLED led = new AddressableLED(1);
  private final int ledLen = ledBuffer.getLength();
  private XboxController controller = new XboxController(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    /* This part is the example code */
    // the led needs to know how long it is for reasons unknown to me.
    led.setLength(ledLen);

    for (int i = 0; i < ledLen; i++) {
      // this goes through each led in the data and sets it to off
      ledBuffer.setRGB(i, 0, 0, 0);
    }

    // this sets the colors that the led will use
    led.setData(ledBuffer);
    // this tells the led to start showing the colors in ledBuffer until we tell it to stop. since we never tell it to stop, it does this continuously.
    led.start();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // challenge: convert this into one for loop
    if (controller.getRawButton(0)) {
      ledBuffer.setRGB(0, 255, 255, 255); // if button 0 on the controller is pressed, turn the first light on
    }
    else {
      ledBuffer.setRGB(0, 0, 0, 0); // otherwise turn it on
    }

    if (controller.getRawButton(1)) {
      ledBuffer.setRGB(1, 255, 255, 255); // if button 1 on the controller is pressed, turn the second light on
    }
    else {
      ledBuffer.setRGB(1, 0, 0, 0); // otherwise turn it on
    }

    if (controller.getRawButton(2)) {
      ledBuffer.setRGB(2, 255, 255, 255); // if button 2 on the controller is pressed, turn the third light on
    }
    else {
      ledBuffer.setRGB(2, 0, 0, 0); // otherwise turn it on
    }

    if (controller.getRawButton(3)) {
      ledBuffer.setRGB(3, 255, 255, 255); // if button 3 on the controller is pressed, turn the fourth light on
    }
    else {
      ledBuffer.setRGB(3, 0, 0, 0); // otherwise turn it on
    }


    // this sets the colors that the led will use
    led.setData(ledBuffer);
  }

  /** This function is called periodically whilst in simulation. */
  // I made it so that this just calls the teleop function for testing purposes, you can ignore this.
  @Override
  public void simulationPeriodic() {
    teleopPeriodic();
  }
}
