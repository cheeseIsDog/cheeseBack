package cheese.cheese.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class SchoolDto {
    @Getter
    @NoArgsConstructor
    public static class res{
        private Long schoolId;
        private String name;

        public res(String name, Long schoolId) {
            this.name = name;
            this.schoolId =schoolId;
        }
    }
}
