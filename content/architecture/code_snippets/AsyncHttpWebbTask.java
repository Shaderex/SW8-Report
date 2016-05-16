public AsyncHttpWebbTask(final Method method, final String url, final int expectedResponseCode) {
	this.url = url;
	this.webb = Webb.create();
	this.expectedResponseCode = expectedResponseCode;
	this.method = method;
}