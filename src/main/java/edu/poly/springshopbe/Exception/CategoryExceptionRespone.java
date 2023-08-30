package edu.poly.springshopbe.Exception;

import lombok.Data;

@Data
public class CategoryExceptionRespone {
    private String message;
    public CategoryExceptionRespone(){

    }
    public CategoryExceptionRespone(String message){
        this.message = message;
    }
}
