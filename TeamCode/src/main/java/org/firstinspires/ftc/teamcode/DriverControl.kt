package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.*

@TeleOp(name = "abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcbaabcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba", group = "Linear Opmode")
class DriverControl : LinearOpMode() {
    private fun smooth(x: Float): Double {
        return if(x > 0) {
            sqrt(x).toDouble()
        } else {
            -sqrt(-x).toDouble()
        }
    }

    override fun runOpMode() {
        val leftMotor = hardwareMap.get(DcMotor::class.java, "leftMotor")
        val rightMotor = hardwareMap.get(DcMotor::class.java, "rightMotor")
        val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        waitForStart()

        armLeft.targetPosition = armLeft.currentPosition
        armRight.targetPosition = armRight.currentPosition
        armMiddle.targetPosition = armMiddle.currentPosition
        armWrist.targetPosition = armWrist.currentPosition
        
        armLeft.mode = DcMotor.RunMode.RUN_TO_POSITION
        armRight.mode = DcMotor.RunMode.RUN_TO_POSITION
        armMiddle.mode = DcMotor.RunMode.RUN_TO_POSITION
        armWrist.mode = DcMotor.RunMode.RUN_TO_POSITION

        val p = 10000
        val mul = 100
        
        armLeft.power = p.toDouble()
        armRight.power = p.toDouble()
        armMiddle.power = p.toDouble()
        armWrist.power = 10.toDouble()
        
        var a = false
        var b = false
        var c = false
        var d = true
        var e = 1;
        var f = 1;
        
        while (opModeIsActive()) {
            val z_translation = gamepad1.left_trigger - gamepad1.right_trigger
            if (z_translation > 0)
                e = -1
            else
                e = 1
            val x_translation = e * -gamepad1.left_stick_x

            val leftPower = -smooth((z_translation + x_translation))
            val rightPower = smooth((z_translation - x_translation))

            if (gamepad1.b) {
                f = 2
            }
            else {
                f = 1
            }
            
            var lt = 0
            var rt = 0
            var lb = 0
            var rb = 0
            if (gamepad1.left_trigger > 0) lt = 1
            if (gamepad1.right_trigger > 0) rt = 1
            if (gamepad1.left_bumper) lb = 1
            if (gamepad1.right_bumper) rb = 1
            
            val rp = lt - lb
            val lp = rt - rb
            
            if (d) {
                leftMotor.power = leftPower / f
                rightMotor.power = rightPower / f
            }
            else {
                leftMotor.power = smooth(lp.toFloat() * p) / f
                rightMotor.power = -smooth(rp.toFloat() * p) / f
            }
            if (gamepad1.a) d = !d

            // val leftBottomarmPower = gamepad2.left_stick_y.toDouble()
            // val rightBottomarmPower = -leftBottomarmPower
            // val middlearmPower = gamepad2.right_stick_y.toDouble()
            // val wristPower = gamepad2.right_trigger.toDouble() - gamepad2.left_trigger.toDouble()

        

            if (gamepad2.left_stick_y != 0.0f) {
                armLeft.targetPosition = armLeft.currentPosition + (gamepad2.left_stick_y * mul).toInt()
                armRight.targetPosition = armRight.currentPosition - (gamepad2.left_stick_y * mul).toInt()
                a = true
            }
            else if (a) {
                armLeft.targetPosition = armLeft.currentPosition
                armRight.targetPosition = armRight.currentPosition
                a = false
            }
            
            if (gamepad2.right_stick_y != 0.0f) {
                armMiddle.targetPosition = armMiddle.currentPosition + (gamepad2.right_stick_y * mul).toInt()
                b = true
            }
            else if (b) {
                armMiddle.targetPosition = armMiddle.currentPosition
                b = false
            }
            
            if (gamepad2.left_trigger != 0.0f || gamepad2.right_trigger != 0.0f) {
                armWrist.targetPosition = armWrist.currentPosition + ((gamepad2.right_trigger - gamepad2.left_trigger) * mul/2).toInt()
                c = true
            }
            else if (c) {
                armWrist.targetPosition = armWrist.currentPosition
                c = false
            }
            
            // leftBottomarm.power = smooth(leftBottomarmPower.toFloat())
            // rightBottomarm.power = smooth(rightBottomarmPower.toFloat())
            // middlearm.power = smooth(middlearmPower.toFloat())
            // wristarm.power = smooth(wristPower.toFloat())
        }
    }
}
