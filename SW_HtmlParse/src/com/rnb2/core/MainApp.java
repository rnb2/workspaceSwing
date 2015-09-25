/**
 * 
 */
package com.rnb2.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.SimpleHtmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;
import org.htmlcleaner.XPatherException;

/**
 * @author budukh-rn
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CleanerProperties props = new CleanerProperties();
		 
		// set some properties to non-default values
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		 
		try {
			// do parsing
			TagNode tagNode = new HtmlCleaner(props).clean(
			    new File("load_test.html")
			);
			 
			// serialize to xml file
			//new PrettyXmlSerializer(props).writeToFile(
			//    tagNode, "chinadaily.xml", "utf-8"
			//);
			
			tagNode.traverse(new TagNodeVisitor() {
				
				@Override
				public boolean visit(TagNode tagNode, HtmlNode htNode) {
					// TODO Auto-generated method stub
					if(htNode instanceof TagNode){
						TagNode tg = (TagNode) htNode;
						String title = tg.getName();
					System.out.println(title);
					if("script".equals(title))
						System.out.println("src\t: " + tg.getAttributeByName("src"));
					
					
					if("ol".equals(title)){
						System.out.println("ol id\t: " + tg.getAttributeByName("id"));
					}
					}
					return true;
				}
			});
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	/*	
		final CleanerProperties props2 = new CleanerProperties();
		final HtmlCleaner htmlCleaner = new HtmlCleaner(props2);
		final SimpleHtmlSerializer htmlSerializer =   new SimpleHtmlSerializer(props2);
		 
		// make 10 threads using the same cleaner and the same serializer 
		for (int i = 1; i <= 10; i++) {
		    final String url = "http://search.eim.ebay.eu/Art/2-1/?en=100&ep=" + i;
		    final String fileName = "c:/temp/ebay_art" + i + ".xml";
		    new Thread(new Runnable() {
		        public void run() {
		            try {
		                TagNode tagNode = htmlCleaner.clean(new URL(url));
		                htmlSerializer.writeToFile(tagNode, fileName, "utf-8");
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }).start();
		}
		*/
	}

}
