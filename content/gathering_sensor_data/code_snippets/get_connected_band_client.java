protected boolean getConnectedBandClient() throws InterruptedException, BandException {
  if (bandClient == null) {
    final BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
    if (devices.length == 0) {
      Log.d("Band2", "Band isn't paired with your phone (from " + this.getClass().getName() + ")."); //*\label{line:debug_log}
      return false;
    }
    final Context context = contextWeakReference.get();
    bandClient = BandClientManager.getInstance().create(context, devices[0]); //*\label{line:device_array_zero_index}
  } else if (ConnectionState.CONNECTED == bandClient.getConnectionState()) {
    return true;
  }

  Log.d("Band2", "Band is connecting...  (from " + this.getClass().getName() + ")");
  final ConnectionState result = bandClient.connect().await();

  Log.d("Band2", "Band connection status: " + result + " (from " + this.getClass().getName() + ")");

  return ConnectionState.CONNECTED == result;
}