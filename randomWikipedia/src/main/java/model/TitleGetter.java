package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TitleGetter {

	public static String getTitle(URL url) throws IOException {

		// 入力ストリームを開く
		InputStream is = url.openStream();

		// InputStreamReaderにエンコーディングを指定 (例: UTF-8)
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");

		// BufferedReaderを使用して効率よく読み込む
		BufferedReader br = new BufferedReader(isr);

		String content = "";
		while (br.readLine() != null) {
			content += br.readLine();

			br.readLine();
		}

		// ストリームを閉じる
		br.close();

		// タイトルを取得する
		String title = "";

		String start = "<title>";
		String end = "</title>";
		int startIndex = content.indexOf(start);
		int endIndex = content.indexOf(end);

		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			title += content.substring(startIndex + start.length(), endIndex);
		} else {
			title = "タイトルが取得できませんでした。";
		}
		return title;
	}

	public static Page makePage(URL url) throws IOException {

		/*// 新規
		String randomPageUrl = url.toString();
		
		// リダイレクト先のURLを取得
		HttpURLConnection conn = (HttpURLConnection) new URL(randomPageUrl).openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.connect();
		String redirectedUrl = conn.getHeaderField("Location");
		
		// タイトルを取得（URLの最後の部分）
		String title = redirectedUrl.substring(redirectedUrl.lastIndexOf("/") + 1);
		title = URLDecoder.decode(title, "UTF-8");
		*/

		// 入力ストリームを開く
		InputStream is = url.openStream();

		// InputStreamReaderにエンコーディングを指定 (例: UTF-8)
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");

		// BufferedReaderを使用して効率よく読み込む
		BufferedReader br = new BufferedReader(isr);

		String content = "";
		while (br.readLine() != null) {
			content += br.readLine();

			br.readLine();
		}

		// ストリームを閉じる
		br.close();

		// タイトルを取得する
		String title = "";

		String start = "<title>";
		String end = "</title>";
		int startIndex = content.indexOf(start);
		int endIndex = content.indexOf(end);

		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			title += content.substring(startIndex + start.length(), endIndex);
		} else {
			title = "タイトルが取得できませんでした。";
		}

		// タイムスタンプを取得する
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分ss秒");
		String timestamp = sdf.format(date);
		
		String encoded = URLEncoder.encode(title, "UTF-8");
		String titleUrl = "https://ja.wikipedia.org/wiki/" + encoded;

		Page page = new Page(title, url, timestamp);
		return page;
	}
}
