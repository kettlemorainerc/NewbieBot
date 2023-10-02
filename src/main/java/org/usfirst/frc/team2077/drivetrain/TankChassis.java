package org.usfirst.frc.team2077.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2077.common.Clock;
import org.usfirst.frc.team2077.common.RobotHardware;
import org.usfirst.frc.team2077.common.WheelPosition;
import org.usfirst.frc.team2077.common.drivetrain.AbstractChassis;
import org.usfirst.frc.team2077.common.math.AccelerationLimits;
import org.usfirst.frc.team2077.subsystem.TankModule;
import org.usfirst.frc.team2077.util.SmartDashNumber;

import java.util.EnumMap;

import static org.usfirst.frc.team2077.common.drivetrain.MecanumMath.VelocityDirection.*;
import static org.usfirst.frc.team2077.common.drivetrain.MecanumMath.VelocityDirection.ROTATION;

public class TankChassis extends AbstractChassis<TankModule> {
private static final double WHEELBASE = 22 + 1/2;
private static final double TRACK_WIDTH = 20 + 5/8;
private static final double WHEEL_RADIUS = 2;
private static final SmartDashNumber MAX_SPEED = new SmartDashNumber("Max Speed", 0.5, true);

//    public TankChassis(EnumMap<WheelPosition, DriveModuleIF> driveModule) {
//        this(driveModule, Clock::getSeconds);
//    }
//
//    public TankChassis(EnumMap<WheelPosition, DriveModuleIF> driveModule, Supplier<Double> getSeconds) {
//        super(driveModule, WHEELBASE, TRACK_WIDTH, WHEEL_RADIUS, getSeconds);
//    }

    public TankChassis(RobotHardware<TankModule, TankChassis> hardware) {

        super(buildDriveTrain(hardware), WHEELBASE, TRACK_WIDTH, WHEEL_RADIUS, Clock::getSeconds);

        MAX_SPEED.onChange(this::maxSpeedUpdated);
//        MAX_SPEED.onChange(() -> maxSpeedUpdated());
        maxSpeedUpdated();
    }

    private void maxSpeedUpdated() {
        maximumRotation = maximumSpeed = MAX_SPEED.get();
    }

    @Override
    protected void updatePosition() {

    }

    @Override
    protected void updateDriveModules() {
       double forwardVelocity = targetVelocity.get(NORTH);
       double rotationalVelocity = targetVelocity.get(ROTATION);

       if(forwardVelocity != 0){
        for(WheelPosition position : WheelPosition.values()) {
            driveModule.get(position).setVelocity(forwardVelocity);
        }
       } else if(rotationalVelocity != 0){
           driveModule.get(WheelPosition.NORTH_EAST).setVelocity(-rotationalVelocity);
           driveModule.get(WheelPosition.SOUTH_EAST).setVelocity(-rotationalVelocity);
           driveModule.get(WheelPosition.NORTH_WEST).setVelocity(rotationalVelocity);
           driveModule.get(WheelPosition.SOUTH_WEST).setVelocity(rotationalVelocity);
       } else {
           for(WheelPosition position : WheelPosition.values()) {
               driveModule.get(position).setVelocity(0);
           }
       }
    }

    @Override
    public void setVelocity(double north, double east, double clockwise, AccelerationLimits accelerationLimits) {
        setVelocity(north, east, accelerationLimits);
        setRotation(clockwise, accelerationLimits);
    }

    @Override
    public void setVelocity(double north, double east, AccelerationLimits accelerationLimits) {
        targetVelocity.put(NORTH, north);
        this.accelerationLimits.set(NORTH, accelerationLimits.get(NORTH));

        targetVelocity.put(EAST, east);
        this.accelerationLimits.set(EAST, accelerationLimits.get(EAST));
    }

    @Override
    public void setRotation(double clockwise, AccelerationLimits accelerationLimits) {
        targetVelocity.put(ROTATION, clockwise);
        this.accelerationLimits.set(ROTATION, accelerationLimits.get(ROTATION));
    }

    private static EnumMap<WheelPosition, TankModule> buildDriveTrain(RobotHardware<TankModule, TankChassis> hardware) {
        EnumMap<WheelPosition, TankModule> map = new EnumMap<>(WheelPosition.class);

        for(WheelPosition p : WheelPosition.values()) {
            map.put(p, hardware.getWheel(p));
        }

        return map;
    }
}
