package com.example.platform.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-10
 * Time: 10:27
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OnlyDateResponse<T> implements Serializable {
    private T data;

    private OnlyDateResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> OnlyDateResponse<T> createOnlyDataSuccess(T data){
        return new OnlyDateResponse<T>(data);
    }
}
