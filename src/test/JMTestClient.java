package test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author nullzZ
 *
 */
public class JMTestClient {

	OutputStream os = null;
	InputStream is = null;
	Socket socket;

	public void connect(String host, int port) throws Exception {
		try {
			socket = new Socket(host, port);
			os = socket.getOutputStream();
			is = socket.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void send() throws IOException {
		if (os != null) {
			ByteBuffer bb = ByteBuffer.allocate(4);
			bb.putShort((short) 2);
			bb.putShort((short) 1);
			os.write(bb.array());
			os.flush();
		}
	}

	public short resev() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		while (bis.available() <= 0) {
		}
		byte[] recB = new byte[bis.available()];
		bis.read(recB);
		ByteBuffer bb = ByteBuffer.wrap(recB);
		bb.getShort();
		short c = bb.getShort();
		return c;
	}

	public void close() throws IOException {
		socket.close();
	}

	public static void main(String[] args) throws Exception {
		JMTestClient client = new JMTestClient();
		client.connect("127.0.0.1", 41000);
		client.send();
		int re = client.resev();
		System.out.println(re);
	}
}
