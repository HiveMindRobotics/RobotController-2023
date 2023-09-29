package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Controller;

public abstract class LogicAction extends Action {
    public LogicAction(Controller controller) {
        super(controller.scheduler);
    }
}
