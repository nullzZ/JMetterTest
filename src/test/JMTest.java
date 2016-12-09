package test;

import java.io.IOException;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author nullzZ
 *
 */
public class JMTest extends AbstractJavaSamplerClient {
	JMTestClient client;

	@Override
	public void setupTest(JavaSamplerContext arg0) {
		// SampleResult sp = new SampleResult();
		// sp.sampleStart();
		client = new JMTestClient();
		try {
			client.connect("127.0.0.1", 41000);
			// sp.setSuccessful(true);
		} catch (Exception e) {
			e.printStackTrace();
			// sp.setSuccessful(false);
		} finally {
			// sp.sampleEnd();
		}
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult sp = new SampleResult();
		sp.sampleStart();
		try {
			client.send();
			if (client.resev() == 1) {
				sp.setSuccessful(true);
			} else {
				sp.setSuccessful(false);
			}
		} catch (Exception e) {
			sp.setSuccessful(false);
			e.printStackTrace();
		} finally {
			sp.sampleEnd();
		}
		return sp;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
