package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author nullzZ
 *
 */
public class JMTestClient {

	DataOutputStream os = null;
	DataInputStream is = null;
	Socket socket;

	public void connect(String host, int port) throws Exception {
		try {
			socket = new Socket(host, port);
			socket.setReuseAddress(true);
			// 关闭传输缓存，默认为false
			socket.setTcpNoDelay(true);
			// 如果输入流等待1000毫秒还未获得服务端发送数据，则提示超时，0为永不超时
			socket.setSoTimeout(10000);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());
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
		is.readShort();
		short c = is.readShort();
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
