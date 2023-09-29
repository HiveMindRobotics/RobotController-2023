package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Driver Control", group = "Linear Opmode")
public class DriverControl extends Controller {
    @Override
    public void runOpMode() throws InterruptedException {
        // Do stuff
        System.out.println(this.scheduler);
    }
}
