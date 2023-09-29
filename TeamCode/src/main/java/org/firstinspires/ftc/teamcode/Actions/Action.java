package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Scheduler;
import org.jetbrains.annotations.NotNull;

public abstract class Action {
    @NotNull Scheduler scheduler;

    public Action(@NotNull BaseOpMode baseOpMode) {
        this.scheduler = baseOpMode.scheduler;
    }
}
