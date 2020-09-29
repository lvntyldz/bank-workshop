package com.samples.imdbparser;

import org.apache.commons.collections.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/*
    kodlamada kullanılan string fonksiyonları
    string.indexOf(...)
    string.lastIndexOf(...)
    string.contains(...)
    string.substring(...)
    string.split(...)
    string.replace(...)
    string.startsWith(...)
    string.endsWith(...)
    string.length(...)
    string.charAt(...)
    String.format(...) -> static
 */

public class Run {

    private static final String BASE_SITE_URL = "https://www.imdb.com";
    private static final String SEARCH_KEYWORD = "godfather";
    private static final String SEARCH_RESULT_NOT_FOUND_KEYWORD = "<h1 class=\"findHeader\">No results found for <span class=\"findSearchTerm\">\"" + SEARCH_KEYWORD + "\"</span>";
    private static final String SEARCH_SITE_URL = "https://www.imdb.com/find?q=" + SEARCH_KEYWORD + "&ref_=nv_sr_sm";

    public static void main(String[] args) {

        //gotfather'ı imDB de ara
        String htmlContent = getHtmlContentFromGivenUrl(SEARCH_SITE_URL);
        //System.out.println("htmlContent : " + htmlContent);

        //sonuç bulunamadı HTML içeriği dönerse devam etme
        if (htmlContent.contains(SEARCH_RESULT_NOT_FOUND_KEYWORD)) {
            System.out.println("*** Aranan Film Bulunamadı");
            return;
        }

        //arama sonucunda gelen HTML içinden filmlerin ve linklerinin olduğu kısmı al
        String filmListPart = getFilmListPartOfHtmlContent(htmlContent);
        //System.out.println("filmListPart : " + filmListPart);

        //linklerin olduğu HTML content içinden href kısımlarını base_url ile ilişkilendirerek al
        List<String> wholeLinks = getAllFilmLinksFromGivenContent(filmListPart);
        //System.out.println("wholeLinks : " + wholeLinks);

        //link bulamazsa devam etme
        if (CollectionUtils.isEmpty(wholeLinks)) {
            System.out.println("*** film linki bulunamadı");
            return;
        }

        //Bulunan film linkleri içinden ilk linkin içeriğini çek
        String filmDetailHtml = getHtmlContentFromGivenUrl(wholeLinks.get(0));
        System.out.println("filmDetailHtml : " + filmDetailHtml);

        //filmin başlığını detail ekranından al
        String title = getFilmTitle(filmDetailHtml);
        System.out.println("title : " + title);

        String rating = getFilmRating(filmDetailHtml);
        System.out.println("rating : " + rating);

        String directorName = getFilmDirector(filmDetailHtml);
        System.out.println("directorName : " + directorName);
    }

    private static String getFilmDirector(String filmDetailHtml) {
        String searchPattern = "Director:";
        int startIndex = filmDetailHtml.indexOf(searchPattern);
        int endIndex = filmDetailHtml.indexOf("</a>", startIndex);
        String directorPart = filmDetailHtml.substring(startIndex, endIndex);
        String directorNameSearchPattern = ">";
        int startIndexDirectorName = directorPart.lastIndexOf(directorNameSearchPattern);
        return directorPart.substring(startIndexDirectorName + directorNameSearchPattern.length());
    }

    private static String getFilmRating(String filmDetailHtml) {
        String searchPattern = "<span itemprop=\"ratingValue\">";
        String searchEndPattern = "</span>";
        int startIndex = filmDetailHtml.indexOf(searchPattern);
        int endIndex = filmDetailHtml.indexOf(searchEndPattern, startIndex);

        return filmDetailHtml.substring(startIndex + searchPattern.length(), endIndex);
    }

    private static String getFilmTitle(String filmDetailHtml) {
        String startPattern = "<div class=\"title_wrapper\"><h1 class=\"\">";
        int startIndex = filmDetailHtml.indexOf(startPattern);
        int endIndex = filmDetailHtml.indexOf("<span id=\"titleYear\">");

        String title = "";

        for (int i = startIndex + startPattern.length(); i < endIndex; i++) {
            title += filmDetailHtml.charAt(i);
        }
        title = title.replace("&nbsp;", "");
        return title;
    }

    private static String getHtmlContentFromGivenUrl(String siteUrl) {
        System.out.println(String.format("*** fetching content with given url : %s", siteUrl));
        URL url;
        try {
            // get URL content
            url = new URL(siteUrl);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            //use FileWriter to write file
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            br.close();
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> getAllFilmLinksFromGivenContent(String filmListPart) {
        List<String> wholeLinks = new ArrayList<String>();
        String[] resultLinks = filmListPart.split("<td class=\"result_text\">");

        for (int i = 0; i < resultLinks.length; i++) {
            String linkPrefix = "href=\"";
            int startIndex = resultLinks[i].indexOf(linkPrefix);
            int endIndex = resultLinks[i].indexOf(">", startIndex);
            String link = resultLinks[i].substring(startIndex + linkPrefix.length(), endIndex);
            link = link.replace("\" ", "");

            if (link.startsWith("/") && link.endsWith("/")) {
                wholeLinks.add(BASE_SITE_URL + link);
            }
        }

        return wholeLinks;
    }

    private static String getFilmListPartOfHtmlContent(String result) {

        String findSectionHeader = "<h3 class=\"findSectionHeader\"><a name=\"tt\"></a>Titles</h3>";//<div class="findSection"><h3 class="findSectionHeader"><a name="tt"></a>Titles</h3><table
        String findMoreMatchersDiv = "<div class=\"findMoreMatches\">";//<div class="findMoreMatches"> <a href="/find?q=godfather&s=tt">More title matches</a>

        //iki index arasındaki veri bulunan filmlerin HTML içeriğidir
        if (result.contains(findSectionHeader) && result.contains(findMoreMatchersDiv)) {

            int startIndex = result.indexOf(findSectionHeader);
            int endIndex = result.indexOf(findMoreMatchersDiv);
            return result.substring(startIndex, endIndex);

        }
        return null;
    }
}
