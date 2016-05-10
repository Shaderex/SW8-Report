final RealmConfiguration realmConfiguration = 
	new RealmConfiguration.Builder(BackgroundSensorService.this)
  		.name(REALM_NAME)
  		.encryptionKey(encryptionKey)
  		.build();

Realm.setDefaultConfiguration(realmConfiguration);
