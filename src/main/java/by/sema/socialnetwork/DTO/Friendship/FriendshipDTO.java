package by.sema.socialnetwork.DTO.Friendship;


import lombok.Data;

@Data
public class FriendshipDTO {
    private Integer userId;
    private String userUsername;
    private Integer friendId;
    private String friendUsername;
    private String friendImageUrl;
    private String status;
}
