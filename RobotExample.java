/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// README!!!!!
// This is a sample robot program. Everything is pretty thoroughly commented as to why it exists and what exactly it does.
// This program won't work exactly with any robot; it is just for the sake of reference. Try to learn as much as you can from this, and you can copy-paste lines if needed.

package frc.robot;	// Don't touch this. This line is pretty much the core of your program, and it tells the robot where it needs to get all of its materials (also it tells the robot that it is indeed a robot)

// All your library imports land immediately after this line. You will inevitably have more imports than this, but this is the bare minimum to make this program work.
// Don't mess with these too much unless you know you're not gonna use one of these anymore.

import edu.wpi.first.wpilibj.TimedRobot;					// Your robot should be a timed robot, so this library gives your robot the information and tools to behave like a timed robot
import edu.wpi.first.wpilibj.Talon;							// This is an example of importing a motor controller. You guys are using Talon SRX motor controllers, which are a LOT different from just regular Talons, so your import won't look like this one. More on this in a bit
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;	// The Smart Dashboard is a big array of information you'll use to get camera feeds and debug your robot. Really this isn't important to you in the early stages of development, so I wouldn't really worry about this one. You probably shouldn't delete it, though
import edu.wpi.first.wpilibj.Joystick;						// This is the library of information for controllers, joysticks, and the like. Don't import the Xbox controller library, use this one

// Talon SRX motor controllers are a little tackier to import. They're technically a "third-party library," or a vendor library, which just means that FIRST, FRC, and the gods at WPI don't officially endorse it or provide code for it.
// Once you've downloaded the CTRE Phoenix installer and you've got that all hooked up, you'll need to actually tell your robot program in VS Code that the library exists and you want to use it. Go to this link https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/682619-3rd-party-libraries in your browser and scroll to where it says "Adding an offline-installed Library." Follow the instructions and you should be all set (yours is gonna look a bit different from the photo, just add a checkmark to anything that isn't checkmarked)
// After you've done that, don't worry about trying to manually type out the import. INSTEAD, write out TalonSRX motor = new TalonSRX(0); and VS Code is gonna throw a hissy fit with you and underline your code red. When it does that, place your cursor on it and hit the yellow lightbulb. Something should come up about importing a library that's called CTRE or something and has that in the name.

// Any Talon SRX documentation you need, including wiring and software, can be found at this link: http://www.ctr-electronics.com/talon-srx.html#product_tabs_technical_resources

public class Robot extends TimedRobot {	// All your code and stuff is gonna go in here. On a technical level, your robot is called Robot, and its class is a TimedRobot. Don't change this.
	// From here your program may differ from this one, since I cleared out all the default stuff they put here.
	
	// Define all your hardware, controllers, variables, etc. here before any of the functions and garbage after this
	double IAmANumber = 3;						// double means that this is a decimal number. This doesn't actually do anything, I just put it here as an example
	Joystick IAmAController = new Joystick(0);	// Joystick means that this is a Joystick. Simple, right? The 0 is the order in which you plugged your controllers into your computer, 0 being the first.
	Talon MyNameIsTalon = new Talon(1);			// Talon means that this is a Talon. It is plugged into port 1 on the roboRIO.
	TalonSRX IAmATalonSRX = new TalonSRX(0);	// Most motor controllers plug in to the PWM ports on the roboRIO. Unfortunately, this is not the case for Talon SRX controllers, which plug in through the CAN bus. CAN is a big pain in the booty to set up, but in the grand scheme of wire-setups, your wiring team will appreciate these. Now that I know how to set these up, I might ship over there someday and help you guys out with them, because it is not easy.
	double joystickLeft = 0;					// More on this one in a bit. I'm setting the stage for my controls here now before I actually enable the robot. Remember: you can't check a variable that hasn't been defined yet!
	double joystickRight = 0;					// Same on this one.
	
	// I'm skipping all the nitty-gritty autonomous and init and disabled functions, since at a robot's core just for driving purposes, you don't need them. This program simply drives a robot based on joystick input.
	
	/**
	 * This function is called periodically during teleop mode
	 */
	
	@Override
	public void teleopPeriodic() {
		// ^^^ that thing up there doesn't lie
		// All this code in here will run CONTINUOUSLY when your robot is enabled in teleop mode. This is where you will run joystick checks and ANYTHING that you want to happen when you're driving the robot
		
		// The following two commands set joystickLeft and joystickRight to a number between -1 and 1 based on where the joysticks on your controller are pointed. Each joystick has 2 axes (horizontal and vertical), and both your triggers have axes themselves too, but just one. This means you have 6 axes to choose from.
		// Each axis is given an ID. For example, 0 may end up being the X axis on the left joystick, or 5 may be the right trigger and how held down it is. These vary based on your controller.
		// You shouldn't use the trigger axes to drive because they only return any number between 0 and 1 (no input to fully pressed down). For your tank drive robot, you're gonna want the Y axis on both joysticks (literally pushing forward and backward to get your robot to move). I don't know what these are off the top of my head, so you're gonna have to either A. guess and check when you're programming, or B. use the Driver Station to figure out which axis correlates to what number. Pan through the tabs on the left side of your Driver Station with your controller plugged into your computer until you find the one where you can test out controller buttons. This is a very useful tab!
		
		// getRawAxis means exactly what it looks like; this function is asking the controller what one of its axes is doing, and returns it in a double.
		joystickLeft = IAmAController.getRawAxis(0);	// First off, you don't need to add the "double" thing at the end of this, because your robot already knows that joystickLeft is a double. Now, I don't know if the axis you want is actually 0, but I'm betting that it's close.
		joystickRight = IAmAController.getRawAxis(1);	// Again, I don't know if this number is accurate, so you'll want to check and figure it out.
		
		// I'm just assuming here that the MyNameIsTalon controller is your left wheelset, and that IAmATalonSRX is the right wheelset. Probably not accurate (and I know you guys don't have Talons on your robot anyway)
		
		MyNameIsTalon.set(joystickLeft);	// As easy as that! Literally set the motor speed to whatever joystickLeft is set to. This works perfectly, because setting a motor is ALWAYS giving it a number between -1 (full speed backward), 0 (stopped), and 1 (full speed forward).
		IAmATalonSRX.set(ControlMode.PercentOutput, joystickRight);	// Okay, this one is where it gets dicey, because Talon SRX controllers are super customizable and high-tech or whatever, so this may not work as expected. If I find out otherwise or that ControlMode.PercentOutput is wrong, then I'll revise this. That argument there where it says ControlMode.PercentOutput though is, very broadly, telling the motor controller what kind of number we're gonna feed it. In this case, it should (in theory) expect a number between -1 and 1 like our other motor controller
	}
}