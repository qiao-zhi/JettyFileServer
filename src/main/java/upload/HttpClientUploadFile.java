package upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * httpclient上传文件(测试没问题)
 * 
 * @author Administrator
 *
 */
public class HttpClientUploadFile {
	public static void main(String[] args) throws ClassNotFoundException, ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		try {
			HttpPut httpput = new HttpPut(
					"http://localhost:8080/fileserver/ID:MicroWin10-1535-54829-1554981858740-1:1:1:1:1");
			// 可以选择文件，也可以选择附加的参数
			HttpEntity req = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("file", new FileBody(new File("G:/Exam.log")))// 上传文件,如果不需要上传文件注掉此行
					.build();
			httpput.setEntity(req);

			System.out.println("executing request: " + httpput.getRequestLine());
			response = httpclient.execute(httpput);

			HttpEntity re = response.getEntity();
			System.out.println(response.getStatusLine());
			if (re != null) {
				System.out.println(
						"Response content: " + new BufferedReader(new InputStreamReader(re.getContent())).readLine());
			}
			EntityUtils.consume(re);
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}