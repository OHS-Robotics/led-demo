// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
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
  private AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(6);
  private AddressableLED led = new AddressableLED(1);
  private final int ledLen = ledBuffer.getLength();
  private int offset = 0;
  private int offsetAccumulator = 0;
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
    // this variable lets us interact with the LED at port 9. think of it as a pipe we can send commands through or something.
    // this variable holds the data that we send to the led. think of it like a list of colors that we tell the led to show. the 60 is the length of the led buffer.
    // the led needs to know how long it is for reasons unknown to me.
    led.setLength(ledLen);

    for (int i = 0; i < ledLen; i++) {
      // this goes through each led in the data and sets it to off, unless it's the current led to light up.
      if (offset == i) {
        ledBuffer.setRGB(i, 255, 255, 255);
      }
      else {
        ledBuffer.setRGB(i, 0, 0, 0);
      }
    }

    // this sets the colors that the led will use
    led.setData(ledBuffer);
    // this tells the led to start showing the colors in ledBuffer until we tell it to stop. since we never tell it to stop, it does this continuously.
    led.start();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // if we just move the light every time this function is called, it would be very flashy
    // as it's called every 20 milliseconds(50 times per second!)
    // we increment this accumulator, and if it's more than 5 we allow the light to change.
    // this makes the light only change every 100 ms, which is 10 times per second
    offsetAccumulator++;
    // change the offset of the light to light up, but only if the "a" button on the controller is pressed. This also resets the accumulator.
    if (controller.getAButton() && offsetAccumulator >= 10) {
      offset++;
      offsetAccumulator = 0;
    }

    offset = offset % (ledLen); // if the offset is larger than the length of the light strip, it wraps arounds around

      for (int i = 0; i < ledLen; i++) {
        // this goes through each led in the data and sets it to off, unless it's the current led to light up.
        if (offset == i) {
          ledBuffer.setRGB(i, 255, 255, 255);
        }
        else {
          ledBuffer.setRGB(i, 0, 0, 0);
        }
      }

    // this sets the colors that the led will use
    led.setData(ledBuffer);
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
    teleopPeriodic();
  }
}
