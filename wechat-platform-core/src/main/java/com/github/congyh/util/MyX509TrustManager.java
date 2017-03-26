package com.github.congyh.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 信任管理器类, 用于https访问时验证客户端和服务器的身份
 *
 * <p><strong>WARNING</strong> 本实现仅适用于Oracle JVM, 默认
 * 信任所有服务器端证书.
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 * @since 0.2
 */
public class MyX509TrustManager implements X509TrustManager {
    /**
     * 检查客户端证书
     *
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        // 暂且不做检查
    }

    /**
     * 检查服务器端证书
     *
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        // 暂且不做检查
    }

    /**
     * 返回受信任的X509证书组
     *
     * @return
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // 暂时不做返回
        return null;
    }
}
