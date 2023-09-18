package org.usfirst.frc.team2077.subsystem;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.usfirst.frc.team2077.common.WheelPosition;
import org.usfirst.frc.team2077.common.drivetrain.DriveModuleIF;

public class TankModule implements Subsystem, DriveModuleIF {
    public enum DrivePosition{
        FRONT_RIGHT(WheelPosition.NORTH_EAST, 0,100),
        BACK_RIGHT(WheelPosition.SOUTH_EAST, 1,100),
        FRONT_LEFT(WheelPosition.NORTH_WEST, 2,100),
        BACK_LEFT(WheelPosition.SOUTH_WEST, 3,100);

        public final int motorID;
        public final double maximumSpeed;
        public final WheelPosition position;
        DrivePosition(WheelPosition position,int motorID,double maximumSpeed) {
            this.position = position;
            this.motorID = motorID;
            this.maximumSpeed = maximumSpeed;
        }
    }

    private final VictorSPX motorController;
    private final DrivePosition position;

    private double targetMagnitude = 0;

    public TankModule(DrivePosition position) {
        this.motorController = new VictorSPX(position.motorID);
        this.position = position;
    }

    @Override
    public void periodic() {
        motorController.set(VictorSPXControlMode.Velocity, targetMagnitude);
    }

    @Override
    public double getMaximumSpeed() {
        return position.maximumSpeed;
    }

    @Override
    public void setVelocity(double velocity) {
        targetMagnitude = velocity;
    }

    @Override
    public WheelPosition getWheelPosition() {
        return position.position;
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
