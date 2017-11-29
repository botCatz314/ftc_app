package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Vuforia")
public class Vuforia extends LinearOpMode
{
    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;
    VuforiaTrackable target;
    VuforiatrackableDefultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGlMatrix phoneLocation;

    public static final String VUFORIA_KEY = "AeOpxj3/////AAAAGa1hky4Ahkp6jA7uCGunP+KJAZb3Di06YSh1ToEAxDmlWGeqxY3Mp26DqFw1P5Lyc/gFq992XUJ2bf8QtwYWln76jzRISvwAoSotdCOMreIL6fpbK4fdsAG9u85FTJlPDsOMY5u9YktxQ/JERWyrQC/NhAxJX+RDVtTouFnrUx/EI8CJDHR/IFcHnQ4KIJdCfQBoeC6+qMJ1RCa2lo2BFPcQv4blFatYz4Z0P+0XVhiza0t0mwJXKzTlwq+c4V9X0nWseTQZXnmgbB0kwQx+m/pGzr9ImML9WhSiWp5qPjyqDYitWs7cU/zWLFFT1wWpW7KkhQ+boQ2zwUsYKemRKY21LV9lkHh5/2a7bJWqKHY/";

    public void runOpMode () throws InterruptedException
    {
        setupVuforia();

        lastKnownLocation = createMatrix(0, 0, 0, 0, 0, 0 );

        waitForStart();

        visionTargets.activate();

        while (opModeIsActive())
        {
            OpenGLMatrix latestLocation = listener.getUpdatedRobotLocation();

            if(latestLocation != null)
                latestKnownLocation = latestLocation;

            telemetry.addData("Tracking " + target.getName(), listener.isVisible());
            telemetry.addData("Last Known Location", formatMatrix(lastKnownLocation));

            telemetry.update();
            idle();
        }
    }

    public void setupVuforia()
    {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);

        visionTargets = vuforiaLocalizer.loadTrackablesFromAsset ("FTC_2016-17");

        target = visiontargets.get(0);
        target.setName("Wheels Target");
        target.setlocation(createMatrix(0, 0, 0, 0, 0, 0));

        phoneLocation = createMztrix(0, 0, 0, 0, 0, 0);

        listener = (VuforiaTrackableDefaultListener) target.getListener();
        listener .setPhoneInformation(phoneLocation, parameters.cameraDirection);
    }

    public OpenGlMatrix createMatrix(float x, float y, float z, float u, float v, float w)
    {
        return OpenGLMatrix.translation(x, y, z).
                multiplied(Orientation.getRotationMatrix(
                    AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, u, v, w));
    }

    public String formatMatrix(OpenGLMatrix matrix)
    {
        return matrix.formatAsTransform();
    }
}
