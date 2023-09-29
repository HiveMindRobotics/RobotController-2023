package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Components.Scheduler;
import org.jetbrains.annotations.NotNull;

public abstract class Action {
    @NotNull Scheduler scheduler;

    public Action(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
