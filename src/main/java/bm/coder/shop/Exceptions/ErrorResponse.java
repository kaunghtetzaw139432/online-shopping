package bm.coder.shop.Exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
       private String message;
       private int status;
       private Long timeStamp;
}
