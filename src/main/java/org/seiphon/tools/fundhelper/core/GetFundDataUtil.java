package org.seiphon.tools.fundhelper.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFundDataUtil {

    public static String getDetailByFundCode(String code) throws IOException {
        List<String> fundDetailList = new ArrayList<String>();

        //要爬取的网站
        String url = "http://fund.eastmoney.com/" + code + ".html?spm=search";

        //获得一个和网站的链接，注意是Jsoup的connect
        Connection connect = Jsoup.connect(url);

        //获得该网站的Document对象
        Document document = connect.get();

        Element titleElement = document.selectFirst(".fundDetail-tit div");

        String fundTitle = titleElement.text() + ")";

        fundDetailList.add(fundTitle);

        Elements infoElements = document.select(".infoOfFund table tbody tr td");

        for(Element ele : infoElements){
            fundDetailList.add(ele.text());
        }

        Element infoTips = document.selectFirst(".dataItem01 dt p span");
        Element infoTipsValue = document.selectFirst(".dataItem01 dd dl span");

        fundDetailList.add(infoTips.text() + " " + infoTipsValue);

        Element dataItem02 = document.selectFirst(".dataItem02 dt p");

        Elements dataItem02Values = document.select(".dataItem02 dd span");

        String dataItem02String = dataItem02.text() + ": ";

        for(Element ele : infoElements){
            dataItem02String += " " + ele.text();
        }


        return fundDetailList.toString();
    }

    public void test () throws IOException {
        //要爬取的网站
        String url = "https://www.qidian.com/search?kw=完美世界";

        //获得一个和网站的链接，注意是Jsoup的connect
        Connection connect = Jsoup.connect(url);

        //获得该网站的Document对象
        Document document = connect.get();

        //我们可以通过对Document对象的select方法获得具体的文本内容
        //下面的意思是获得.bool-img-text这个类下的 ul 下的 li
        Elements rootselect = document.select(".book-img-text ul li");

        for(Element ele : rootselect){
            //然后获得a标签里面具体的内容
            Elements novelname = ele.select(".book-mid-info h4 a");

            String name  = novelname.text();

            Elements author = ele.select(".book-mid-info p a");

            String authorname = author.first().text();

            Elements sumadvice = ele.select(".total p");

            String sum = sumadvice.last().text();

            System.out.println("书名:"+name+" 作者:"+authorname+" 推荐量:"+sum);
        }
    }
}
