/**
 * 定义公众平台session实现
 *
 * <pre>
 * 为什么需要手动控制session? 因为和普通的web应用不同, 在这里用户不是从浏览器对web应用
 * 发起请求, 而是通过微信服务器为中转, 微信服务器并没有使用cookie, 请求头如下:
 *
 * {content-length=279, host=congyh.vicp.io, content-type=text/xml,
 * pragma=no-cache, user-agent=Mozilla/4.0, accept=\*\/\*}
 *
 * 此时我们只能通过其他手段进行手动session管控, 而不能依靠web容器进行session管控
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
package com.github.congyh.session;