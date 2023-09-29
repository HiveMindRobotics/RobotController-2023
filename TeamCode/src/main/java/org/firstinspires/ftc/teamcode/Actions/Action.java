package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Components.Scheduler;

public abstract class Action {
    Scheduler scheduler;

    public Action(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
