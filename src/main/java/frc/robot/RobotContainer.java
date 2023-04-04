// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The driver's controller
  private final CommandXboxController m_driverController =  new CommandXboxController(OIConstants.kDriverControllerPort);

  // A few commands that do nothing, but will demonstrate the scheduler functionality
  private final CommandBase m_instantCommand1 = new InstantCommand();
  private final CommandBase m_instantCommand2 = new InstantCommand();
  private final CommandBase m_waitCommand = new WaitCommand(5);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Set names of commands
    m_instantCommand1.setName("Instant Command 1");
    m_instantCommand2.setName("Instant Command 2");
    m_waitCommand.setName("Wait 5 Seconds Command");

    // Set the scheduler to log Shuffleboard events for command initialize, interrupt, finish
    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kCritical));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kLow));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kHigh));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Run instant command 1 when the 'A' button is pressed
    m_driverController.a().onTrue(m_instantCommand1);
    m_driverController.x().onTrue(m_instantCommand1);
    m_driverController.y().onTrue(m_instantCommand1);

    // new JoystickButton(m_driverController, Button.kA.value).onTrue(m_instantCommand1);
    // // Run instant command 2 when the 'X' button is pressed
    // new JoystickButton(m_driverController, Button.kX.value).onTrue(m_instantCommand2);
    // // Run instant command 3 when the 'Y' button is held; release early to interrupt
    // new JoystickButton(m_driverController, Button.kY.value).whileTrue(m_waitCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new InstantCommand();
  }
}
