@Override
protected Response<ResultT> doInBackground(Void... params) {
  try {
    final SSLContext sc = SSLContext.getInstance("TLS");
    sc.init(null, getTrustManagerFactory(context).getTrustManagers(), new java.security.SecureRandom());
    webb.setSSLSocketFactory(sc.getSocketFactory());
  } catch (KeyManagementException | NoSuchAlgorithmException exception) {
    exception.printStackTrace();
  }

  webb.setRetryManager(new RetryManager());
  webb.setDefaultHeader("Accept", "application/json");

  if (isCancelled()) {
    return null;
  }

  try {
    switch (method) { //*\label{lst:switch_method_start}
      case POST:
        return sendRequest(webb.post(url)); //*\label{lst:webb_post}
      case GET:
        return sendRequest(webb.get(url));
      case PUT:
        return sendRequest(webb.put(url));
      case DELETE:
        return sendRequest(webb.delete(url));
      default:
        return null;
    } //*\label{lst:switch_method_end}
  } catch (WebbException exception) {
    exception.printStackTrace();
  }
  return null;
}

protected abstract Response<ResultT> sendRequest(Request webb);