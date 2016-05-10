public void notifyQuestionnaireCompleted(final long snapshotTimeStamp, Questionnaire questionnaire) {
  Realm realm = null;
  try {
    realm = Realm.getDefaultInstance();//*\label{lst:get_default_realm}
    final Snapshot snapshot = realm.where(Snapshot.class).equalTo("timestamp", snapshotTimeStamp).findFirst(); 
    if (snapshot != null) {
      realm.beginTransaction();
      questionnaire = realm.copyToRealm(questionnaire);
      realm.commitTransaction();
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