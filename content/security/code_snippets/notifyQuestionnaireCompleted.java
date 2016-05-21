public void notifyQuestionnaireCompleted(final long snapshotTimeStamp, Questionnaire questionnaire) {

    Realm realm = null;

    try {
      realm = Realm.getDefaultInstance(); //*\label{lst:get_default_realm}

      final Snapshot snapshot = realm.where(Snapshot.class).equalTo("timestamp", snapshotTimeStamp).findFirst(); //*\label{lst:get_correct_snapshot}

      if (snapshot != null) {																					//*\label{lst:snapshot_found}
        try {																									//*\label{lst:begin_try_realm}
          realm.beginTransaction();																				//*\label{lst:begin_realm_transaction}
          questionnaire = realm.copyToRealm(questionnaire);														//*\label{lst:save_questionnaire_get_return}
          realm.commitTransaction();																			//*\label{lst:end_realm_transaction}
        } catch (Exception exception) {																			//*\label{lst:try_realm}
          Log.e("BackgroundSensorService", "Exception while performing Realm Transaction");
          exception.printStackTrace();
          realm.cancelTransaction();																			//*\label{lst:cancel_realm_transaction}
          throw exception;
        }

        try {
          realm.beginTransaction();
          snapshot.setQuestionnaire(questionnaire);																//*\label{lst:set_questionnaire_on_snap}
          realm.copyToRealmOrUpdate(snapshot);																	//*\label{lst:update_snapshot_in_db}
          realm.commitTransaction();
        } catch (Exception exception) {
          Log.e("BackgroundSensorService", "Exception while performing Realm Transaction");
          exception.printStackTrace();
          realm.cancelTransaction();
          throw exception;
        }

      }
    } finally {
      if (realm != null) {
        realm.close();																							//*\label{lst:realm_close}
      }
    }
  }