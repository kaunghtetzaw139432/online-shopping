package bm.coder.shop.Exceptions;

public class OrderNotFoundException extends RuntimeException{
         public OrderNotFoundException(String message){
            super(message);
         }
}
