package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.*
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
        val slide = hardwareMap.get(DcMotor::class.java, "slide")
        val armServo = hardwareMap.get(Servo::class.java, "servo0")
        val planeServo = hardwareMap.get(Servo::class.java, "servo1")
        
        val prevGamepad1 = Gamepad()
        val prevGamepad2 = Gamepad()

        waitForStart()

        armLeft.targetPosition = armLeft.currentPosition
        armRight.targetPosition = armRight.currentPosition
        armMiddle.targetPosition = armMiddle.currentPosition
        armWrist.targetPosition = armWrist.currentPosition
        
        armLeft.mode   = DcMotor.RunMode.RUN_TO_POSITION
        armRight.mode  = DcMotor.RunMode.RUN_TO_POSITION
        armMiddle.mode = DcMotor.RunMode.RUN_TO_POSITION
        armWrist.mode  = DcMotor.RunMode.RUN_TO_POSITION

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
        var e = 1
        var f = 1
        var g = false
        var h = false
        
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
            if (gamepad1.a) {
                d = true
            }
            else if (gamepad1.x) {
                d = false
            }

            // val leftBottomarmPower = gamepad2.left_stick_y.toDouble()
            // val rightBottomarmPower = -leftBottomarmPower
            // val middlearmPower = gamepad2.right_stick_y.toDouble()
            // val wristPower = gamepad2.right_trigger.toDouble() - gamepad2.left_trigger.toDouble()

            if (g) {
                if (gamepad2.a) {
                    g = false
                    armLeft.targetPosition = armLeft.currentPosition
                    armRight.targetPosition = armRight.currentPosition
                    armMiddle.targetPosition = armMiddle.currentPosition
                    armWrist.targetPosition = armWrist.currentPosition
                    
                    armLeft.mode   = DcMotor.RunMode.RUN_TO_POSITION
                    armRight.mode  = DcMotor.RunMode.RUN_TO_POSITION
                    armMiddle.mode = DcMotor.RunMode.RUN_TO_POSITION
                    armWrist.mode  = DcMotor.RunMode.RUN_TO_POSITION
                    
                    armLeft.power = p.toDouble()
                    armRight.power = p.toDouble()
                    armMiddle.power = p.toDouble()
                    armWrist.power = 10.toDouble()
                }
            }
            else {
                if (gamepad2.b) {
                    armLeft.targetPosition = -315
                    armRight.targetPosition = 315
                    armMiddle.targetPosition = -660
                    armWrist.targetPosition = -91
                    h = true
                }
                else if (gamepad2.y) {
                    armLeft.targetPosition = 0
                    armRight.targetPosition = 0
                    armMiddle.targetPosition = 0
                    armWrist.targetPosition = 0
                    h = true
                }
                if (gamepad2.x) {
                    g = true
                    armLeft.mode   = DcMotor.RunMode.RUN_WITHOUT_ENCODER
                    armRight.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
                    armMiddle.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
                    armWrist.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
                    armLeft.power = 0.toDouble()
                    armRight.power = 0.toDouble()
                    armMiddle.power = 0.toDouble()
                    armWrist.power = 0.toDouble()
                }
                if (h) {
                    if (abs(armLeft.targetPosition - armLeft.currentPosition) < 5 &&
                            abs(armRight.targetPosition - armRight.currentPosition) < 5 &&
                            abs(armMiddle.targetPosition - armMiddle.currentPosition) < 5 &&
                            abs(armWrist.targetPosition - armWrist.currentPosition) < 5) {
                        h = false
                    }
                }
                else {
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
                        armWrist.targetPosition = armWrist.currentPosition + ((gamepad2.right_trigger - gamepad2.left_trigger) * mul).toInt()
                        c = true
                    }
                    else if (c) {
                        armWrist.targetPosition = armWrist.currentPosition
                        c = false
                    }
                }
                if (gamepad2.dpad_down) {
                    armServo.position = 1.0
                }
                if (gamepad2.dpad_up) {
                    armServo.position = 0.0
                }

                if (gamepad2.dpad_left) {
                    planeServo.position = 1.0
                }
                
                var g2lb = 0.0
                var g2rb = 0.0
                if (gamepad2.left_bumper) g2lb = 10.0
                if (gamepad2.right_bumper) g2rb = 10.0
                
                var slide_power = g2lb - g2rb
                slide.power = slide_power
            }

            telemetry.addLine("slide.currentPosition ${slide.currentPosition}")
            
            telemetry.addLine("armLeft.currentPosition ${armLeft.currentPosition}")
            telemetry.addLine("armRight.currentPosition ${armRight.currentPosition}")
            telemetry.addLine("armMiddle.currentPosition ${armMiddle.currentPosition}")
            telemetry.addLine("armWrist.currentPosition ${armWrist.currentPosition}")
            telemetry.addLine("d $d")
            telemetry.addLine("gamepad2.a ${gamepad2.a}")
            telemetry.addLine("prevGamepad2.a ${prevGamepad2.a}")
            telemetry.update()

            prevGamepad1.copy(gamepad1)
            prevGamepad2.copy(gamepad2)
            // leftBottomarm.power = smooth(leftBottomarmPower.toFloat())
            // rightBottomarm.power = smooth(rightBottomarmPower.toFloat())
            // middlearm.power = smooth(middlearmPower.toFloat())
            // wristarm.power = smooth(wristPower.toFloat() -315, 315, -660, -91
        }
    }
}
