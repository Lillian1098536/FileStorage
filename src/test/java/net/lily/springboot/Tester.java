package net.lily.springboot;

import java.io.File;
import java.util.Random;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Tester {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/file_storage/file";
		String[] filePath = { 
				"/Users/CS/Desktop/test/test.txt", //
				"/Users/CS/Desktop/test/gongbaojiding.jpeg", //
				"/Users/CS/Desktop/test/Introduction.pdf", //
				"/Users/CS/Desktop/test/kungpaosauce.pdf", //
				"/Users/CS/Desktop/test/new.zip", //
				"/Users/CS/Desktop/test/text.txt", //
				"/Users/CS/Desktop/test/kungpaosauce.pdf" };

		String[] owner = getOwners(filePath.length);

		RestTemplate rest = new RestTemplate();

		for (int i = 0, len = filePath.length; i < len; i++) {
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
			param.add("file", new FileSystemResource(new File(filePath[i])));
			param.add("owner", owner[i]);
			param.add("comment", "uploaded file: " + (i + 1));
			rest.postForObject(url, param, String.class);
		}
	}

	static private String[] getOwners(int num) {
		Random random = new Random();
		String[] owners = new String[num];
		for (int i = 0; i < num; i++) {
			owners[i] = "owner" + random.nextInt(5);
		}
		return owners;
	}

	@SuppressWarnings("unused")
	static private String getRandomString(int min, int max) {
		Random random = new Random();
		int length = random.nextInt(max - min) + min;
		char[] chars = new char[length];

		for (int i = 0; i < length; i++) {
			chars[i] = (char) (random.nextInt(26) + 97);
		}
		return new String(chars);
	}

}
