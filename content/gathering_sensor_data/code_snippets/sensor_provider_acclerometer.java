public class AccelerometerSensorProvider extends SensorProvider<FloatTripleMeasurement> {
  public AccelerometerSensorProvider(final Context context, 
                                                 final ExecutorService sensorThreadPool, 
                                                 final SensorManager sensorManager) {
    super(context, sensorThreadPool, sensorManager);
  }

  @Override
  protected EventListenerRegistrationManager createRegManager() {
    final SensorEventListener listener = new SensorEventListener() {
      @Override
      public void onSensorChanged(final SensorEvent event) {
        onNewMeasurement(new FloatTripleMeasurement(event.values));
      }

      @Override
      public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
        // Do nothing
      }
    };

    return new SensorEventListenerRegistrationManager(sensorManager, 
      sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
      listener);
  }

  @Override
  protected FloatTripleMeasurement getDefaultMeasurement() {
    return new FloatTripleMeasurement();
  }

  @Override
  public boolean isSensorAvailable() {
    return contextWeakReference.get().getPackageManager().hasSystemFeature(
      PackageManager.FEATURE_SENSOR_ACCELEROMETER
    );
  }

  @Override
  public SensorType getSensorType() {
    return SensorType.ACCELEROMETER;
  }
}