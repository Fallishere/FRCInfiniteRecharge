/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TestingDashboard;
import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinConveyor2Timed extends CommandBase {
  /**
   * Creates a new SpinConveyor2Timed.
   */

   Timer m_timer;
   BallIntake m_ballIntake;
   double m_period;

  public SpinConveyor2Timed() {
    // Use addRequirements() here to declare subsystem dependencies.

    m_timer = new Timer();
    m_ballIntake = BallIntake.getInstance();

  }

  public static void registerWithTestingDashboard() {
    BallIntake ballIntake = BallIntake.getInstance();
    SpinConveyor2Timed cmd = new SpinConveyor2Timed();
    TestingDashboard.getInstance().registerCommand(ballIntake, "Timed", cmd);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_period = SmartDashboard.getNumber("Conveyor2MotoryTimeout", 5); // default of 5 seconds
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = SmartDashboard.getNumber("Conveyor2MotorSpeed",0.5);
    m_ballIntake.spinConveyor2(speed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballIntake.spinConveyor2(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean timerExpired = m_timer.hasPeriodPassed(m_period);
    return timerExpired;
  }
}