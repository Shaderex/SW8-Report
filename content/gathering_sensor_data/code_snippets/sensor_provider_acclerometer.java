public class AccelerometerSensorProvider 
  extends SensorProvider<FloatTripleMeasurement> { //*\label{line:sensor_provider_extends}
  public AccelerometerSensorProvider(final Context context, 
                                                 final ExecutorService sensorThreadPool, 
                                                 final SensorManager sensorManager) {
    super(context, sensorThreadPool, sensorManager);
  }

  @Override           
  public boolean isSensorAvailable() {
    return contextWeakReference.get().getPackageManager().hasSystemFeature( //*\label{line:isSensorAvailable_start}
      PackageManager.FEATURE_SENSOR_ACCELEROMETER
    ); //*\label{line:isSensorAvailable_end}
  }

  @Override
  public SensorType getSensorType() {
    return SensorType.ACCELEROMETER; //*\label{line:getSensorType}
  }

  @Override
  protected EventListenerRegistrationManager createRegManager() {
    final SensorEventListener listener = new SensorEventListener() {
      @Override
      public void onSensorChanged(final SensorEvent event) {
        onNewMeasurement(new FloatTripleMeasurement(event.values)); //*\label{line:createRegManager}
      }

      @Override
      public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
        // Do nothing
      }
    };

    return new SensorEventListenerRegistrationManager(sensorManager, //*\label{line:createRegManager_start}
      sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
      listener); //*\label{line:createRegManager_end}
  }

  @Override
  protected FloatTripleMeasurement getDefaultMeasurement() {
    return new FloatTripleMeasurement(); //*\label{line:getDefaultMeasurement}
  }
  
}