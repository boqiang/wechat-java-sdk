package com.github.congyh.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

import static java.lang.System.out;

/**
 * xml处理工具
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class XmlUtils {
    private static final XStream ENHANCED_XSTREAM = getEnhancedXStream();

    public static void main(String... args) {
        Person person = new Person(
            "张三", "男", "北邮");
        out.println(XmlUtils.pojo2WeChatXml(person));
        String xml = "<xml><gender>男</gender>" +
            "<address><![CDATA[北理]]></address></xml>";
        out.println((Alien) XmlUtils.weChatXml2Pojo(xml, Alien.class));

    }

    /**
     * 获得能够解析CDATA的XStream
     *
     * @return 增强版的XStream对象
     */
    private static XStream getEnhancedXStream() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 默认生成xml时, 所有字段都加上CDATA标识
                    boolean cdata = true;

                    @Override
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
    }

    /**
     * pojo转微信公众平台消息xml
     *
     * @param pojo pojo对象
     * @return pojo的微信公众平台消息xml
     */
    public static String pojo2WeChatXml(Object pojo) {
        // 必须先开启注解处理, 否则无法处理注解
        // TODO 这里非常像向XStream对象注册了一个类, 看看之后有没有可能重构
        // 变为用类数组批量注册, 以后就不需要反复注册了.
        ENHANCED_XSTREAM.processAnnotations(pojo.getClass());
        return ENHANCED_XSTREAM.toXML(pojo);
    }

    /**
     * 微信公众平台消息xml转pojo
     *
     * @param xml pojo的xml表示形式
     * @param cls 目标pojo对象的类
     * @return pojo对象(需要手动进行格式转换)
     */
    public static Object weChatXml2Pojo(String xml, Class<?> cls) {
        ENHANCED_XSTREAM.processAnnotations(cls);
        return ENHANCED_XSTREAM.fromXML(xml);
    }

    /**
     * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
     */
    @XStreamAlias("xml")
    private static class Person {
        @XStreamAlias("Name")
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

    /**
     * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
     */
    @XStreamAlias("xml")
    private static class Alien {
        @XStreamAlias("Name")
        private String name;
        private String gender;
        private String address;

        public Alien(String name, String gender, String address) {
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
            return "Alien{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
        }
    }
}

