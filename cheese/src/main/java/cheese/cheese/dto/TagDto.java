package cheese.cheese.dto;

import cheese.cheese.entity.TagMaster;
import cheese.cheese.entity.TagWord;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TagDto {

    @Getter
    @NoArgsConstructor
    public static class req{
        private Long schoolId;
        private String tagName;
    }

    @Getter
    @NoArgsConstructor
    public static class res{
        private String name;

        public res(TagWord tagWord) {
            this.name = tagWord == null ? null : tagWord.getTagName();
        }
    }
}
