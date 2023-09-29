package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Components.Scheduler;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.jetbrains.annotations.NotNull;

public abstract class Controller extends LinearOpMode {
    public @NotNull Robot robot;
    public @NotNull Scheduler scheduler;
}
