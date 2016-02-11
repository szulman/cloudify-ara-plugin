package com.uc4.ara.feature.utils;

import java.rmi.RemoteException;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class ResultHandler {

	public static Result handleImportExportResult(Result result,
			ImportExportServiceSoap importExportProxy)
					throws InterruptedException, RemoteException {
		if (result != null) {
			// waiting until result is available
			while (result.getStatus() == 0) {
				result = importExportProxy.getStatus(result.getToken());
				Thread.sleep(100);
			}
			FeatureUtil
			.logMsg("===================== Operation Result ===================");
			FeatureUtil.logMsg(result.getDescription());
			if (result.getData() != null)
				FeatureUtil.logMsg(result.getData());
			if (result.getErrors().getError().size() > 0) {
				for (int i = 0; i < result.getErrors().getError().size(); i++) {
					FeatureUtil.logMsg(result.getErrors().getError().get(i)
							.getErrorDetail());
				}
				throw new RuntimeException(result.getErrors().getError().get(0)
						.getErrorDetail());
			}
		}
		return result;
	}


	public static Result handleImportExportResultSilent(Result result, ImportExportServiceSoap importExportProxy)
			throws InterruptedException, RemoteException {
		if (result != null) {
			// waiting until result is available
			while (result.getStatus() == 0) {
				result = importExportProxy.getStatus(result.getToken());
				Thread.sleep(100);
			}

			if (result.getErrors().getError().size() > 0)
			{
				for (int i = 0; i < result.getErrors().getError().size(); i++)
				{
					FeatureUtil.logMsg(result.getErrors().getError().get(i).getErrorDetail());
				}
				throw new RuntimeException(result.getErrors().getError().get(0).getErrorDetail());
			}
		}
		return result;
	}

	public static void logResult(Result result, String title) {
		FeatureUtil.logMsg(title);
		FeatureUtil.logMsg(result.getDescription());
		FeatureUtil.logMsg(result.getData());

		for (com.uc4.importexportservice.Error error : result.getErrors()
				.getError()) {
			FeatureUtil.logMsg(error.getErrorTitle() + ": "
					+ error.getErrorDetail());
		}
	}
}
