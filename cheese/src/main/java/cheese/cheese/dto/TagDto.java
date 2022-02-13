package cheese.cheese.dto;

import cheese.cheese.entity.TagMaster;
import cheese.cheese.entity.TagWord;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TagDto {
    @Getter
    @NoArgsConstructor
    public static class res{
        private Long tagMasterId;
        private String name;

        public res(TagMaster tagMaster, TagWord tagWord) {
            this.tagMasterId = tagMaster == null ? null : tagMaster.getTagMasterId();
            this.name = tagWord == null ? null : tagWord.getTagName();
        }
    }
}
