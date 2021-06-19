package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbs.bean.UrlBean;

@Controller
public class homeController {
	
	public static Queue<String> queue =null;
	public static Set<String> groupUrl=null;
	public static String regex = "https[s]*://(\\w+\\.)*(\\w+)";
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/crowler")
	public ResponseEntity<Set<String>> crowler(@RequestBody UrlBean urlBean) throws IOException {
		
		retun new ResponseEntity<>(groupUrl);
		
	}
	
	private Set<String> getUrls(String rootUrl) throws IOException {
		
		 queue = new LinkedList<>();
		 groupUrl = new HashSet<>();
		
		queue.add(rootUrl);
		BufferedReader br = null;

		while (!queue.isEmpty()) {
			String crawleUrl = queue.poll();
			
			if (groupUrl.size() > 100)
				return;
			boolean ok = false;
			URL url = null;

			while (!ok) {
				try {
					url = new URL(crawleUrl);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					ok = true;
				} catch (MalformedURLException e) {
					System.out.println("*** malformed url1:" + crawleUrl);
					crawleUrl = queue.poll();
					ok = false;
				} catch (Exception e) {
					System.out.println("*** malformed urls:" + crawleUrl);
					crawleUrl = queue.poll();
					ok = false;
				}

			}
			StringBuilder sb = new StringBuilder();

			while ((crawleUrl = br.readLine()) != null) {
				sb.append(crawleUrl);
			}
			crawleUrl = sb.toString();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(crawleUrl);

			while (matcher.find()) {
				String w = matcher.group();
				if (!groupUrl.contains(w)) {
					groupUrl.add(w);
					queue.add(w);

				}
			}
			if (br != null)
				br.close();
		}
		return groupUrl;
	}
	
}
