import java.io.IOException;
import org.jsoup.*;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	
	static String wikiStart = "http://en.wikpedia.org/wiki/";
	static String wikiRand = "https://en.wikipedia.org/wiki/Special:Random";
	static String hrefSel = "<a href=\"/wiki/";
	static Document endUrl = new Document(wikiRand);
	
	public static void main(String[] args) throws IOException {		
		
		
		String first3P = scrapeLinks(randomResult());
		System.out.println(first3P);
		while (!first3P.equals("https://en.wikipedia.org/wiki/Philosophy")) {
			first3P = scrapeLinks(endUrl);
			
		}
	
		
		
	}
	
	public static Document randomResult() throws IOException {
		// returns the string URL from following the Random wikipedia link
		// !!! replace url with wikiRand !!!
		String url = "https://en.wikipedia.org/wiki/Subjectivity";
		endUrl = Jsoup.connect(url).followRedirects(true).get();
		return endUrl;
	}
	
	public static String isInParenthesis(Element p) {
		
		String para = p.toString();
		String newURL = "";
		
		if (para.contains("(") || para.contains(")")) {
			
			if (para.indexOf("a href=\"/wiki/") < para.indexOf("(")) {
				// if the first link comes before the parenthesis do this...
				int gnewURL = para.indexOf("a href=\"/wiki/")+14; // location of first char of new wiki
				newURL = para.substring(gnewURL, para.indexOf("\" title="));
				System.out.println(newURL);
				
			} else if (para.indexOf(")") == para.lastIndexOf(")")) {
				
				para = para.substring(para.lastIndexOf(")")+2, para.length());
				int gnewURL = para.indexOf("a href=\"/wiki/")+14; // location of first char of new wiki
				newURL = para.substring(gnewURL, para.indexOf("\" title="));
				System.out.println(newURL);
			}
			
		} else {
			System.out.println("Something went wrong");
			return "https://en.wikipedia.org/wiki/Special:Random";
		}
		
		return wikiStart + newURL;
	}

	public static String scrapeLinks(Element p) {
		// scrape links from the first three paragraphs on a wiki page
		
		String rURL = "";
		Element temp[] = new Element[3];
		for (int i = 0; i <= 2; i++) {
			temp[i] = p.select("p").get(i);
			System.out.println(temp[i]);
		} // puts first three paragraphs into temp[]
		
		for (int i = 0; i < temp.length; i++) {
			String tempS = temp[i].toString();
			if ((tempS.contains("href"))) {
				if ((tempS.indexOf("(") < tempS.indexOf(hrefSel)) && tempS.indexOf(")") > tempS.indexOf(hrefSel)) {
					tempS = tempS.substring(tempS.indexOf(")")+1, tempS.length());
					System.out.println(tempS);
					rURL = tempS.substring(tempS.indexOf(hrefSel)+15, tempS.indexOf("\" title="));
					System.out.println(rURL);
					break;
				} else if ((tempS.indexOf(hrefSel) < tempS.indexOf("("))) {
					rURL = tempS.substring(tempS.indexOf(hrefSel)+15, tempS.indexOf("\" title="));
					System.out.println(rURL);
					break;
				} else if ((tempS.indexOf(")") < tempS.indexOf(hrefSel))) {
					rURL = tempS.substring(tempS.indexOf(hrefSel)+15, tempS.indexOf("\" title="));
					System.out.println(rURL);
					break;
				} else if ((tempS.indexOf(hrefSel) != -1)) {
					rURL = tempS.substring(tempS.indexOf(hrefSel)+15, tempS.indexOf("\" title="));
					System.out.println(rURL);
					break;
				}
			}
		}
		
		if (rURL.contains("mw-redirect")) {
			rURL = rURL.substring(0, rURL.indexOf("\""));
		}
		
		return rURL;
	}
}
