package com.github.congyh.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import static java.lang.System.out;

/**
 * xml处理工具
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class XStreamUtils {

    public static void main(String... args) {
        Person person = new Person(
            "张三", "男", "北邮");
        out.println(XStreamUtils.pojo2WeChatXml(person));
        String xml = "<xml><name>李四</name><gender>男</gender>" +
            "<address>北理</address></xml>";
        out.println((Person)XStreamUtils.weChatXml2Pojo(xml, Person.class));

    }

    /**
     * pojo转微信公众平台消息xml
     *
     * @param pojo pojo对象
     * @return pojo的微信公众平台消息xml
     */
    public static String pojo2WeChatXml(Object pojo) {
        XStream xs = new XStream(new Dom4JDriver());
        // 定义xml转换的起始元素及关联的pojo类型
        xs.alias("xml", pojo.getClass());
        return xs.toXML(pojo);
    }

    /**
     * 微信公众平台消息xml转pojo
     *
     * @param xml pojo的xml表示形式
     * @param cls 目标pojo的类型
     * @return pojo对象(需要手动进行格式转换)
     */
    public static Object weChatXml2Pojo(String xml, Class<?> cls) {
        XStream xs = new XStream(new Dom4JDriver());
        // 定义xml转换的起始元素及关联的pojo类型
        xs.alias("xml", cls);
        return xs.fromXML(xml);
    }
}

class Person {
    private String name;
    private String gender;
    private String address;

    public Person(String name, String gender, String address) {
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
