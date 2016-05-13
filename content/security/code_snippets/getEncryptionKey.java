@Override
protected Response<byte[]> sendRequest(Request request) {
  final Context context = weakContextReference.get();

  if (context != null) {
    try {
      // Get GCM device identifier token (Can throw IOException)
      
      ...

      return request.param("device_id", token).retry(3, false).asBytes();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  
  return null;
}

@Override
public void onResponseCodeMatching(final Response<byte[]> response) {
  encryptionKey = response.getBody();

  // Sets up the realm configuration and start collection of snapshots
  setupRealmAndStartTimers();
}