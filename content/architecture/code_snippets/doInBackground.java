@Override
protected Response<ResultT> doInBackground(Void... params) {

  final TrustManager[] trustAllCerts = new TrustManager[] {...};
  ...
  webb.setHostnameVerifier(new HostnameVerifier() {...});

  webb.setRetryManager(new RetryManager());

  webb.setDefaultHeader("X-Requested-With", "XMLHttpRequest");

  if (isCancelled()) {
    return null;
  }

  try {
    switch (method) {
      case POST:
        return sendRequest(webb.post(url));
      case GET:
        return sendRequest(webb.get(url));
      case PUT:
        return sendRequest(webb.put(url));
      case DELETE:
        return sendRequest(webb.delete(url));
      default:
        return null;
    }
  } catch (WebbException exception) {
    exception.printStackTrace();
  }
  return null;
}