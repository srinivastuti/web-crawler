/**
 * 
 */
package org.dbs.crawl.controller;

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

import org.dbs.crawl.exception.RecordNotFound;
import org.dbs.crawl.form.UrlForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author erbha
 *
 */
@Controller
public class CrawlController {

	Logger logger = LoggerFactory.getLogger(CrawlController.class);

	public static Queue<String> queue = null;
	public static Set<String> groupUrl = null;
	public static String regex = "https[s]*://(\\w+\\.)*(\\w+)";

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

	@PostMapping(path = "/crowler", produces = "application/json")
	public ResponseEntity<Object> crowler(@RequestBody UrlForm urlBean) throws IOException {
		try {
			groupUrl = getUrls(urlBean.getRootUrl());
			return new ResponseEntity<Object>(groupUrl, HttpStatus.OK);
		} catch (RecordNotFound exp) {
			logger.debug(exp.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
		}catch(Exception e) {
			logger.debug(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	}

	private Set<String> getUrls(String rootUrl) throws IOException {

		queue = new LinkedList<>();
		groupUrl = new HashSet<>();

		queue.add(rootUrl);
		BufferedReader br = null;

		while (!queue.isEmpty()) {
			String crawleUrl = queue.poll();

			if (groupUrl.size() > 10)
				return groupUrl;

			boolean ok = false;
			URL url = null;

			while (!ok) {
				try {
					url = new URL(crawleUrl);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					ok = true;
				} catch (MalformedURLException e) {
					logger.debug("*** malformed url:" + crawleUrl);
					crawleUrl = queue.poll();
					ok = true;
				} catch (Exception e) {
					logger.debug(e.getMessage());
					crawleUrl = queue.poll();
					ok = true;
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
		if (groupUrl == null)
			throw new RecordNotFound("No Record found");
		return groupUrl;
	}

}
