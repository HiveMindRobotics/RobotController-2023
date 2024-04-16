package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.*
import kotlin.math.*

enum class ArmState {
    Normal,
    UpStage1,
    UpStage2,
    UpStage3,
    UpStage4,
};

@TeleOp(name = "breaky bot v12", group = "Linear Opmode")
class DriverControl2 : LinearOpMode() {
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
        
        // armLeft.mode   = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        // armRight.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        // armMiddle.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        // armWrist.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        // armLeft.power = 0.toDouble()
        // armRight.power = 0.toDouble()
        // armMiddle.power = 0.toDouble()
        // armWrist.power = 0.toDouble()

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
        var speed_modifier = 1.0
        var arm_state : ArmState = ArmState.Normal
        
        while (opModeIsActive()) {
            val z_translation = gamepad1.left_trigger - gamepad1.right_trigger
            val x_translation = -gamepad1.left_stick_x

            val leftPower = -smooth((z_translation + x_translation))
            val rightPower = smooth((z_translation - x_translation))

            if (gamepad1.b) {
                speed_modifier = 4.0
            }
            else if (gamepad1.a) {
                speed_modifier = 7.0
            }
            else if (gamepad1.x) {
                speed_modifier = .0001
            }
            else {
                speed_modifier = 1.0
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
                leftMotor.power = leftPower / speed_modifier
                rightMotor.power = rightPower / speed_modifier
            }
            else {
                leftMotor.power = smooth(lp.toFloat() * p) / speed_modifier
                rightMotor.power = -smooth(rp.toFloat() * p) / speed_modifier
            }
            if (gamepad1.dpad_left) {
                planeServo.position = 0.0
            }
            // if (gamepad1.dpad_down) {
            //     d = true
            // }
            // else if (gamepad1.dpad_up) {
            //     d = false
            // }

            // val leftBottomarmPower = gamepad2.left_stick_y.toDouble()
            // val rightBottomarmPower = -leftBottomarmPower
            // val middlearmPower = gamepad2.right_stick_y.toDouble()
            // val wristPower = gamepad2.right_trigger.toDouble() - gamepad2.left_trigger.toDouble()
            when (arm_state) {
                ArmState.Normal -> {
                    if (abs(gamepad2.left_stick_y) > 0.1f) {
                        armLeft.targetPosition = armLeft.currentPosition + (gamepad2.left_stick_y * mul).toInt()
                        armRight.targetPosition = armRight.currentPosition - (gamepad2.left_stick_y * mul).toInt()
                        a = true
                    }
                    else if (a) {
                        armLeft.targetPosition = armLeft.currentPosition
                        armRight.targetPosition = armRight.currentPosition
                        a = false
                    }
                    
                    if (abs(gamepad2.right_stick_y) > 0.1f) {
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
                    if (gamepad2.dpad_down) {
                        armServo.position = 1.0
                    }
                    if (gamepad2.dpad_up) {
                        armServo.position = 0.0
                    }
                    if (gamepad2.y) {
                        arm_state = ArmState.UpStage1
                    }
                }
                ArmState.UpStage1 -> {
                    armWrist.targetPosition = 90
                    if (abs(armWrist.currentPosition - armWrist.targetPosition) < 10)
                        arm_state = ArmState.UpStage2
                }
                ArmState.UpStage2 -> {
                    armLeft.targetPosition = 480
                    armRight.targetPosition = -480
                    if (abs(armRight.currentPosition - armRight.targetPosition) < 10 &&
                            abs(armLeft.currentPosition - armLeft.targetPosition) < 10)
                        arm_state = ArmState.Normal

                }
                ArmState.UpStage3 -> {
                    
                }
                ArmState.UpStage4 -> {
                    
                }
                
            }

            
            var g2lb = 0.0
            var g2rb = 0.0
            if (gamepad2.left_bumper) g2lb = 1000.0
            if (gamepad2.right_bumper) g2rb = 1000.0
            
            val slide_power = g2rb - g2lb
            slide.power = slide_power

            telemetry.addLine("slide.currentPosition ${slide.currentPosition}")
            
            telemetry.addLine("armLeft.currentPosition ${armLeft.currentPosition}")
            telemetry.addLine("armRight.currentPosition ${armRight.currentPosition}")
            telemetry.addLine("armMiddle.currentPosition ${armMiddle.currentPosition}")
            telemetry.addLine("armWrist.currentPosition ${armWrist.currentPosition}")
            telemetry.addLine("d $d")
            telemetry.addLine("gamepad2.right_stick_y ${gamepad2.right_stick_y}")
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
