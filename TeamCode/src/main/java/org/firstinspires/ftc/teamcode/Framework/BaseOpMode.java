package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Framework.Scheduler;
import org.firstinspires.ftc.teamcode.OperatorInput.OperatorInput;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.jetbrains.annotations.NotNull;

public abstract class BaseOpMode extends LinearOpMode {
    public @NotNull Robot robot;
    public @NotNull Scheduler scheduler;

    public @NotNull OperatorInput operatorInput;
}
