public void notifyQuestionnaireCompleted(final long snapshotTimeStamp, final Questionnaire questionnaire) {
	Realm realm = null;
	try {
		realm = Realm.getDefaultInstance();//*\label{lst:get_default_realm}

		final Snapshot snapshot = realm.where(Snapshot.class).equalTo("timestamp", snapshotTimeStamp).findFirst(); 

		// If we found a snapshot
		if (snapshot != null) {
			// Save the questionnaire
			realm.beginTransaction();
			questionnaire = realm.copyToRealm(questionnaire);
			realm.commitTransaction();

			// Associate the questionnaire to the snapshots, and update it 
			realm.beginTransaction();
			snapshot.setQuestionnaire(questionnaire);
			realm.copyToRealmOrUpdate(snapshot);
			realm.commitTransaction();
		}
	} finally {
		if (realm != null) {
			realm.close();
		}
	}
}