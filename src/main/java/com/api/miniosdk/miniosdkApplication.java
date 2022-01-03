package com.api.miniosdk;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.api.miniosdk.components.MinioComponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class miniosdkApplication {

	public static void main(String[] args)
			throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
		ApplicationContext applicationContext = SpringApplication.run(miniosdkApplication.class, args);

		String fileName = "localfile.txt";
		MinioComponent minIOComponent = applicationContext.getBean(MinioComponent.class);
		minIOComponent.ReadWriteMinIo(fileName);

		System.out.println ("fine");

	}
}
