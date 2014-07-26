package xml.jdom2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlUtil {

    public static void main(String[] args) throws JDOMException, IOException {
        String file = "/home/ww/softwares/p.xml"; // 文件存放位置
        XmlUtil dj = new XmlUtil();
        dj.createXml(file);
        dj.parserXml(file);

    }
    /** 
     * 生成XML 
     * @param filePath 文件路径 
     */
    public void createXml(String fileName) {
        Element root = new Element("persons");
     // TODO 如果不生成<?xml version="1.0" encoding="UTF-8"?>xml标示头，可删去Document
        Document document = new Document(root);
        Element person = new Element("person");
        root.addContent(person);
        Element name = new Element("name");
        name.setText("java小强");
        person.addContent(name);
        Element sex = new Element("sex");
        sex.setText("man");
        person.addContent(sex);
        Element age = new Element("age");
        age.setText("23");
        person.addContent(age);
        XMLOutputter XMLOut = new XMLOutputter();
        try {
            Format f = Format.getPrettyFormat();
            /*
             * 可以将xml在一行输出
             * Format f = Format.getRawFormat();
             */
            f.setEncoding("UTF-8");//default=UTF-8
            XMLOut.setFormat(f);
            // 如果输出包括xml标示头则传document，如不包括传root
            XMLOut.output(document, new FileOutputStream(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /** 
     * 解析XML 
     * @param filePath 文件路径 
     */ 
    public void parserXml(String fileName) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(fileName);
            Element root = document.getRootElement();
            List persons = root.getChildren("person");
            for (int i = 0; i < persons.size(); i++) {
                Element person = (Element) persons.get(i);
                List pros = person.getChildren();
                for (int j = 0; j < pros.size(); j++) {
                    Element element = (Element) pros.get(j);
                    System.out.println(element.getName() + ":" + element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

