package kr.co.won.rentalcardservice.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserItemInputDTO {

    private String userId;

    private String userName;

    private Integer itemId;

    private String itemTitle;

}
