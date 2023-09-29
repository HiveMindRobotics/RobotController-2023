package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.jetbrains.annotations.NotNull;

public abstract class AutoAction extends Action {
    @NotNull Robot robot;

    public AutoAction(Controller controller) {
        super(controller.scheduler);
        this.robot = controller.robot;
    }
}
