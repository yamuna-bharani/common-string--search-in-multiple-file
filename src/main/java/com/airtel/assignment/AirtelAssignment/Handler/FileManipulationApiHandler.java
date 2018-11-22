package com.airtel.assignment.AirtelAssignment.Handler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.airtel.assignment.AirtelAssignment.Data.BaseData;

@Component
public class FileManipulationApiHandler extends BaseHandler {

	private static final int MAX_FILE_LIMIT = 3;

	@Override
	public BaseData getCommonWords(BaseData data) {

		List<String> filePaths = data.getData();
		int numFiles = filePaths.size();
		ConcurrentHashMap<String, Integer> commonWords = new ConcurrentHashMap<>();
		BaseData response = new BaseData();

		if (filePaths == null || filePaths.isEmpty() || filePaths.size() > MAX_FILE_LIMIT) {
			response.setIsSuccess(false);
			response.setMessage("OOPS..! Error in input!!");
			System.out.println("Error in input size: " + filePaths != null ? filePaths.size() : "null input");
		}
		int count = 0;
		for (String filePathStr : filePaths) {
			Path filePath = Paths.get(filePathStr);
			try {
				commonWords.put("count", ++count);
				Files.readAllLines(filePath).parallelStream().map(line -> line.split("\\s+")).flatMap(Arrays::stream)
						.parallel().map(reg -> reg.replaceAll("[^a-zA-Z0-9]", "")).map(String::toLowerCase)
						.forEach(word -> {
							if (!commonWords.containsKey(word))
								commonWords.put(word, 0);
							commonWords.put(word, commonWords.get("count"));
						});

				response.setData(commonWords.keySet().stream()
						.filter(key -> commonWords.get(key) == numFiles && !key.equals("count"))
						.collect(Collectors.toList()));
				response.setIsSuccess(true);
				response.setMessage("Tak Completed");
			} catch (Exception e) {
				response.setIsSuccess(false);
				response.setMessage("OOPS..! Exception Occured!!");
				System.out.println("Excepton while parsing file : " + e.getMessage());
			}
		}

		return response;
	}

}
