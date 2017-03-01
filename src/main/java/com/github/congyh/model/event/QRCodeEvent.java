package com.github.congyh.model.event;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class QRCodeEvent extends AbstractEvent {
    // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
    private String eventKey;
    // 二维码的ticket，可用来换取二维码图片
    private String ticket;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
