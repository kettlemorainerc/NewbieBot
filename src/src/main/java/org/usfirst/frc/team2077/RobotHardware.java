package org.usfirst.frc.team2077;

import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj2.command.*;
import org.usfirst.frc.team2077.common.WheelPosition;
import org.usfirst.frc.team2077.common.drivetrain.*;
import org.usfirst.frc.team2077.common.sensor.AngleSensor;
import org.usfirst.frc.team2077.drivetrain.TankChassis;
import org.usfirst.frc.team2077.subsystem.TankModule;


/**
 * To prepare this class for actual use you'll need to replace all instances of `/*DRIVE_MODULE&#42;/`
 * with your chassis' wheel module type.
 * As well as replacing all instances of `/*CHASSIS_TYPE&#42;/` with the robot's actual chassis type.
 * <br /><br />
 * In the case of something like a mecanum driven chassis you might replace `/*DRIVE_MODULE&#42;/` with
 * {@link org.usfirst.frc.team2077.common.subsystem.CANLineSubsystem.SparkNeo CANLineSubsystem.SparkNeo}
 * and `/*CHASSIS_TYPE&#42;/` with {@link MecanumChassis}
 * */
public class RobotHardware implements org.usfirst.frc.team2077.common.RobotHardware<TankModule, TankChassis> {
    private final Subsystem HEADING = new Subsystem() {};
    private final Subsystem POSITION = new Subsystem() {};
    private final TankChassis CHASSIS;

    public RobotHardware() {
        CHASSIS = new TankChassis(this);
    }

    @Override public Subsystem getHeading() {
        return HEADING;
    }

    @Override public Subsystem getPosition() {
        return POSITION;
    }

    @Override public TankChassis getChassis() { return CHASSIS; }

    @Override public AHRS getNavX() {
        return null;
    }

    @Override
    public TankModule getWheel(WheelPosition position) {
        return null;
    }

    @Override public AngleSensor getAngleSensor() {
        return null;
    }

}
