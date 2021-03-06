/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.BallIntake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.TestingDashboard;
import frc.robot.input.XboxController.XboxAxis;
import frc.robot.subsystems.BallIntake;

/**
 * A Default Intake command that passes XBox controller input to the Ball Intake.
 */
public class DefaultIntake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private static OI oi;
  private final BallIntake m_intake;

  /**
   * Creates a new DefaultIntake.
   * @param intake the subsystem used by this command.
   */
  public DefaultIntake(BallIntake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = OI.getInstance();
    m_intake = intake;
    addRequirements(m_intake);
  }

  public static void registerWithTestingDashboard() {
    BallIntake intake = BallIntake.getInstance();
    DefaultIntake cmd = new DefaultIntake(BallIntake.getInstance());
    TestingDashboard.getInstance().registerCommand(intake, "Basic", cmd);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Drives the ball intake with the right trigger.
    double speedRight = oi.getXbox().getAxis(XboxAxis.kRightTrigger);
    double speedLeft = oi.getXbox().getAxis(XboxAxis.kLeftTrigger);
    double speed = (speedRight > speedLeft) ? speedRight : -speedLeft;
    m_intake.spinIntakeRoller(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  // Default command so will never finish running.
  @Override
  public boolean isFinished() {
    return false;
  }
}
