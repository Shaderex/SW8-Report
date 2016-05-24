 @Override
 protected final void onPostExecute(final Response<ResultT> response) {

  if (response == null) {
    onConnectionFailure();
  } else if (response.getStatusCode() == this.expectedResponseCode) {
    onResponseCodeMatching(response);
  } else {
    onResponseCodeNotMatching(response);
  }

  super.onPostExecute(response);
}

public abstract void onResponseCodeMatching(Response<ResultT> response);

public abstract void onResponseCodeNotMatching(Response<ResultT> response);

public abstract void onConnectionFailure();