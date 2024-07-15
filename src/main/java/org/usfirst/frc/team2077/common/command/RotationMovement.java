package org.usfirst.frc.team2077.common.command;

import edu.wpi.first.wpilibj2.command.*;
import org.usfirst.frc.team2077.common.*;
import org.usfirst.frc.team2077.common.drivetrain.*;
import org.usfirst.frc.team2077.common.DriveXboxController;

public class RotationMovement extends Command {
    protected final DriveStick stick;
    protected final DriveChassisIF chassis;

    public RotationMovement(RobotHardware hardware, DriveXboxController stick) {
        addRequirements(hardware.getHeading());

        this.stick = stick;
        this.chassis = hardware.getChassis();
    }

    @Override public void execute() {
        chassis.setRotationPercent(stick.getRotation());
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
