package dto;
public class GridDTO {
    private String title , content;
    private int imgresId;
    // Mybatis List<Object DTO> => 빈깡통을 먼저 만들고 get , set
    // error발생 
    // VO , Value Object ( Web의 기본 DTO 형태 ) GridDTO dto = new GridDTO();<=
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgresId() {
        return imgresId;
    }

    public void setImgresId(int imgresId) {
        this.imgresId = imgresId;
    }
}
