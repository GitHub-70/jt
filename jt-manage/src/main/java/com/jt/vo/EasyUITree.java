package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
public class EasyUITree implements Serializable {

    private Long id;        //节点ID信息
    private String text;    //节点的名称
    private String state;   //节点的状态   open 打开  closed 关闭

    public EasyUITree() {
    }

    public EasyUITree(Long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
