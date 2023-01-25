package org.usfirst.frc.team2077.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.usfirst.frc.team2077.common.WheelPosition;
import org.usfirst.frc.team2077.common.drivetrain.DriveModuleIF;

public class TankModule implements DriveModuleIF {
    public enum DrivePosition{
    FRONT_RIGHT(WheelPosition.NORTH_EAST, 0,100),
    BACK_RIGHT(WheelPosition.SOUTH_EAST, 1,100),
    FRONT_LEFT(WheelPosition.NORTH_WEST, 2,100),
    BACK_LEFT(WheelPosition.SOUTH_WEST, 3,100),
    ;
    public final int motorID;
    public final double maximumSpeed;
    public final WheelPosition position;
    DrivePosition(WheelPosition position,int motorID,double maximumSpeed) {
        this.position = position;
        this.motorID = motorID;
        this.maximumSpeed = maximumSpeed;
    }
}

    private final CANSparkMax motorController;
    private final DrivePosition position;
    public TankModule(DrivePosition position) {
        this.motorController = new CANSparkMax(position.motorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.position = position;
    }
    @Override
    public double getMaximumSpeed() {
        return 0;
    }

    @Override
    public void setVelocity(double velocity) {

    }

    @Override
    public WheelPosition getWheelPosition() {
        return null;
    }

    @Override
    public double getVelocity() {
        return 0;
    }

    @Override
    public double getDistance() {
        return 0;
    }

    @Override
    public void resetDistance() {

    }
}
