package cheese.cheese.dto;

import cheese.cheese.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TagDto {
    @Getter
    @NoArgsConstructor
    public static class res{
        private Long tagId;
        private String name;

        public res(Tag tag) {
            this.tagId = tag.getTagId();
            this.name = tag.getTagName();
        }
    }
}
