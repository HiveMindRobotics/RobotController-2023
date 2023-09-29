package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.OperatorInput.OperatorInput;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.jetbrains.annotations.NotNull;

public abstract class DriverAction extends Action {

    private @NotNull OperatorInput operatorInput;

    public DriverAction(@NotNull BaseOpMode baseOpMode) {
        super(baseOpMode);
        operatorInput = baseOpMode.operatorInput;
    }
}
