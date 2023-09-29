package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.jetbrains.annotations.NotNull;

public abstract class AutoAction extends Action {
    @NotNull Robot robot;

    public AutoAction(@NotNull BaseOpMode baseOpMode) {
        super(baseOpMode);
        this.robot = baseOpMode.robot;
    }
}
