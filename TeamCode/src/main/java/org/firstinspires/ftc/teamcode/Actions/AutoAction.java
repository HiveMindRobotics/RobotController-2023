package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public abstract class AutoAction extends Action {
    Robot robot;

    public AutoAction(Controller controller) {
        super(controller.scheduler);
        this.robot = controller.robot;
    }
}
