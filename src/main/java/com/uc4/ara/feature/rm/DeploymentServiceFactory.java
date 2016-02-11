package com.uc4.ara.feature.rm;

import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.uc4.deploymentservice.DeploymentServiceProxy;


public class DeploymentServiceFactory {

	/**
	 * The Constant DEPLOYMENTSERVICE.
	 */
	public static final String DEPLOYMENTSERVICE = "/service/DeploymentService.svc?wsdl";

	public static DeploymentServiceProxy getDeploymentServiceFromURL(String url)
			throws MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException {
		DeploymentServiceProxy service = new DeploymentServiceProxy(url + DEPLOYMENTSERVICE);

		return service;
	}

	public static DeploymentServiceProxy getDeploymentServiceFromURLWithoutSSL(
			String url) throws MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException {
		circumventSslProblems();
		DeploymentServiceProxy deploymentProxy = getDeploymentServiceFromURL(url);
		

		return deploymentProxy;
	}

	/**
	 * <pre>
	 * +--------+
	 * |        |
	 * |        O
	 * |     O--|--!
	 * |        |
	 * |       / \
	 * |
	 * L----------------
	 * </pre>
	 * 
	 * Circumvent ssl problems. Wuhaha, this is a very bad method! Do not use
	 * it. It avoids any ssl-checking. In fact the certificate of the server is
	 * not checked against the local keystore. Furthermore the hostname of the
	 * server is also not checked anymore. So you do not know who is the server
	 * you are talking with. There are better solutions:
	 * <ul>
	 * <li>easiest: enable the server to accept plain http requests instead of
	 * https.</li>
	 * <li>buy an official server certificate and install it at the server. So
	 * the certificate has a high chance to be trusted by the default keystore</li>
	 * <li>Add the server's certificate to the keystore file (see the
	 * java-keytool)</li>
	 * </ul>
	 * 
	 * Again, it is a very bad idea to call this function. Use it at your own
	 * risk and do not blame the author for it.
	 * 
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws KeyManagementException
	 *             the key management exception
	 */
	public static void circumventSslProblems() throws NoSuchAlgorithmException,
			KeyManagementException {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		{
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
	}
}
