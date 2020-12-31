package com.badam.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "addPersonResponse", namespace = "http://service.ba.com")
public class ResponseDTO {

    private boolean sonuc;

    @XmlElement(name = "return")
    public boolean isSonuc() {
        return sonuc;
    }

    public void setSonuc(boolean sonuc) {
        this.sonuc = sonuc;
    }
}
