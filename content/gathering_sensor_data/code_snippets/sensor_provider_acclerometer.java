public class AccelerometerSensorProvider extends SensorProvider<FloatTripleMeasurement> { //*\label{line:sensor_provider_extends}
  public AccelerometerSensorProvider(final Context context, 
                                                 final ExecutorService sensorThreadPool, 
                                                 final SensorManager sensorManager) {
    super(context, sensorThreadPool, sensorManager);
  }

  @Override //*\label{line:isSensorAvailable_start}
  public boolean isSensorAvailable() {
    return contextWeakReference.get().getPackageManager().hasSystemFeature(
      PackageManager.FEATURE_SENSOR_ACCELEROMETER
    );
  } //*\label{line:isSensorAvailable_end}

  @Override //*\label{line:getSensorType_start}
  public SensorType getSensorType() {
    return SensorType.ACCELEROMETER;
  } //*\label{line:getSensorType_end}

  @Override //*\label{line:createRegManager_start}
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
      listener); //*\label{line:createRegManager_end}
  }

  @Override //*\label{line:getDefaultMeasurement_start}
  protected FloatTripleMeasurement getDefaultMeasurement() {
    return new FloatTripleMeasurement();
  } //*\label{line:getDefaultMeasurement_end}
}