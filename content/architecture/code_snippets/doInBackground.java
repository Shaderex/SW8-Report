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
    switch (method) { //*\label{lst:switch_method_start}
      case POST:
        return sendRequest(webb.post(url));//*\label{lst:webb_post}
      case GET:
        return sendRequest(webb.get(url));
      case PUT:
        return sendRequest(webb.put(url));
      case DELETE:
        return sendRequest(webb.delete(url));
      default:
        return null;
    }//*\label{lst:switch_method_end}
  } catch (WebbException exception) {
    exception.printStackTrace();
  }
  
  return null;
}