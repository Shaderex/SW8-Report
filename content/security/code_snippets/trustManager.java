final TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {

  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }

  @Override
  public void checkClientTrusted(final X509Certificate[] arg0, final String arg1) throws CertificateException {
        // Not implemented
  }

  @Override
  public void checkServerTrusted(final X509Certificate[] arg0, final String arg1) throws CertificateException {
        // Not implemented
  }
}
};