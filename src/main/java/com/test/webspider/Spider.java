package com.test.webspider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Spider {
	static int pageNum;// 记录这个爬去页面内的交互次数
	static int questionNum;// 记录一组追问次数
	static int answerNum;// 记录一组回答次数
	static int totalInteracterNum;//记录总交互次数
	static int groupNum;//记录交互组数
	static int totalGroupNum;//记录总交互组数
	static int maxInteractionNum;//记录一个页面中最大的交互次数

	public void spider(URL url) {
		try {
		
			System.out.println(url);
			URLConnection cnn = url.openConnection();
			cnn.setReadTimeout(4000); // 设置读取时间
			BufferedReader read = new BufferedReader(new InputStreamReader(
					cnn.getInputStream(), "GBK"));// GBK为网页编码格式
			String line = "";
			int index;
			while ((line = read.readLine()) != null) {	
				
				 //if语句各种字符串匹配和处理
				if (line.indexOf("var qtagname = \"") >= 0) {
					index = line.indexOf("var qtagname = \"")+16;
					String Text = line.substring(index,line.indexOf("\";"));
					System.out.println("症状： " + Text);
				}
				if (line.indexOf("<div class=\"graydeep User_quecol pt10 mt10\" id=\"qdetailc\">") >= 0) {
					String Text = read.readLine();
						System.out.println("病人描述:"+Text);		
				}
				if(line.indexOf("<a class=\"f14 fb Doc_bla\" href=\"") >= 0){
					index =line.indexOf("<a class=\"f14 fb Doc_bla\" href=\"")+32;
					String Text = line.substring(index);
					System.out.print("医生主页："+Text.substring(0,Text.indexOf("\""))+"  ");
					System.out.println("("+Text.substring(Text.indexOf(">")+1,Text.indexOf("<"))+")");
				}
				if(line.indexOf("<div class=\"clearfix pb10  pl20 pr20 ballc pr\">")>=0){
					groupNum++;
					totalGroupNum++;
					if(questionNum>answerNum){
						System.out.println("本次交互次数"+answerNum);
						totalInteracterNum+=answerNum;
						if(answerNum>maxInteractionNum)
							maxInteractionNum=answerNum;
					}
					else{
						System.out.println("本次交互次数"+questionNum);
						totalInteracterNum+=questionNum;
						if(questionNum>maxInteractionNum)
							maxInteractionNum=questionNum;
					}
					questionNum =answerNum= 0;
				}
				if(line.indexOf("<div class=\"pt15 f14 graydeep  pl20 pr20\">") >= 0){
					index =line.indexOf("<div class=\"pt15 f14 graydeep  pl20 pr20\">")+42;
					System.out.print("问题分析:"+line.substring(index));
					while(line.indexOf("</div>")<0){
						System.out.print(line);
						line=read.readLine();
					}
					System.out.println();
				}

				if (line.indexOf("<div class=\"fl w390\">") >= 0) {
					index = line.indexOf("<div class=\"fl w390\">");
					String Text = line.substring(index + 21);
					System.out.println("追问： " + Text);
					questionNum++;
				}
				if (line.indexOf("<div class=\"pt15 f14 graydeep  pl20 pr20\">") >= 0) {
					index = line.indexOf("<div class=\"pt15 f14 graydeep  pl20 pr20\">")+42;
					String Text = line.substring(index);
					if (Text.indexOf("</b>") > Text.indexOf("</div>")) {
						Text = Text.substring(Text.indexOf("</b>")+4, Text.indexOf("</div>"));
						System.out.println("问题分析：" + Text);
					}
				}
				if (line.indexOf("回复：</span><p class=\"fl w390\">") >= 0) {
					index = line.indexOf("回复：</span><p class=\"fl w390\">");
					String Text = line.substring(index + 29);
					System.out.println("回复： " + Text);
					answerNum++;
				}

			}
			pageNum++;
			System.out.print("页面：" + pageNum+"   本页面问答组数"+groupNum+"  本页面最大交互次数"+maxInteractionNum);
			totalGroupNum+=groupNum;
			groupNum=0;
			maxInteractionNum=0;
			System.out.print("     总交互组数"+totalGroupNum);
			System.out.println("    总交互次数"+totalInteracterNum);
		} catch (Exception e) {
			System.out.println("error!!!");
		} 
	}
}
//病例分析: