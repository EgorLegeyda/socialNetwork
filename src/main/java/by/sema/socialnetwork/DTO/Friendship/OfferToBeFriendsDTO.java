package by.sema.socialnetwork.DTO.Friendship;


import lombok.Data;

@Data
public class OfferToBeFriendsDTO {
    private Integer userId;
    private Integer requestFromId;
    private String requestFromUsername;
    private String requestFromImageUrl;
}
