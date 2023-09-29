package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public abstract class DriverAction extends Action {
    Robot robot;

    public DriverAction(Controller controller) {
        super(controller.scheduler);
        this.robot = controller.robot;
    }
}
