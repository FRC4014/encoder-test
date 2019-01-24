package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static final int DRIVE_TRAIN_ENCODER_A_CHANNEL = 0;
  public static final int DRIVE_TRAIN_ENCODER_B_CHANNEL = 1;

  private static final double WHEEL_DIAMETER = 6;

  /**
   * Encoder Resolution: AMT103 and AMT102 are 2048 (dip switchs all set to 0)
   * Each full rotation causes 6 sensors, so ideally resolution is 341.3333. Based
   * on experiments in 2018 we found 330 to work best, but 2019 needs to test it
   * again.
   */
  private static final int ENCODER_RESOLUTION = 330;
  private static final double PULSES_PER_ROTATION = ENCODER_RESOLUTION;
  private static final double DISTANCE_PER_PULSE = 
                   (Math.PI * WHEEL_DIAMETER * 1 / PULSES_PER_ROTATION);
  public static Encoder encoder;

  static {
    encoder = new Encoder(DRIVE_TRAIN_ENCODER_A_CHANNEL, 
                                    DRIVE_TRAIN_ENCODER_B_CHANNEL, 
                                    false,
                                    Encoder.EncodingType.k4X);
    encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
  }

  @Override
  public void testInit() {
    System.out.println("--------- Encoder Resolution: " + ENCODER_RESOLUTION);
    System.out.println("          DISTANCE_PER_PULSE = " + DISTANCE_PER_PULSE);
  }

  int testPeriodicCalls = 0;
  @Override
  public void testPeriodic() {
    if (testPeriodicCalls++ % 100 == 0) {
      System.out.println("Encoder distance: " + encoder.getDistance());
    }
  }
}
