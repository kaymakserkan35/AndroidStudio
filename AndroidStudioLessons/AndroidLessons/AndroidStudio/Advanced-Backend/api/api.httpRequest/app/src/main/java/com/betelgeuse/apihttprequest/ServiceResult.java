package com.betelgeuse.apihttprequest;

public class ServiceResult {
    private static ServiceResult instance = null;
    private static Object lockObj=new Object() ;
    public  static ServiceResult getInstance(){
        synchronized (lockObj) {
            if (instance == null) {
                instance = new ServiceResult();
            }
        }
        return instance;
    }

    public boolean isSuccess ( ) {
        return isSuccess;
    }

    public void setSuccess (boolean success) {
        isSuccess = success;
    }

    public String getResult ( ) {
        return result;
    }

    public void setResult (String result) {
        this.result = result;
    }

    public boolean isSuccess;
    public String  result;
}
