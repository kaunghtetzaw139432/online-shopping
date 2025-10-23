package bm.coder.shop.dtos;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class ProductDto {

   

    private String name;
    private List<MultipartFile> files;
    private Double price;
    private String description;
    private Integer catId;
    private Integer subcatId;
    private Integer childcatId;
    private Integer tagId;

   

}
