package cheese.cheese.dto;

import cheese.cheese.entity.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SchoolDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class req{
        private String schoolName;
    }

    @Getter
    @NoArgsConstructor
    public static class res{
        private Long schoolId;
        private String name;
        private String schoolEmailPostfix;

        public res(School school) {
            this.name = school.getSchoolName();
            this.schoolId = school.getSchoolId();
            this.schoolEmailPostfix = school.getSchoolEmailPostfix();
        }
    }
}
