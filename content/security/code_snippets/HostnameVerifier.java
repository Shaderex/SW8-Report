webb.setHostnameVerifier(new HostnameVerifier() {
  @Override
  public boolean verify(String hostname, SSLSession session) {
    Log.e("HOSTNAME", hostname);


    String[] domains = {
      "dev.local.element67.dk",
      "dev.global.element67.dk",
      "prod.local.element67.dk",
      "prod.global.element67.dk"
    };

    for (String domain : domains) {
      if (domain.equals(hostname)) {
        return true;
      }
    }

    return false;
  }
});