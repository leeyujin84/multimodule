package com.kona.konabase.infrastructure.shiftee.model;

import lombok.*;

public class AttendanceAllModel {
    @Builder
    @Getter
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class RequestDto{
        String from;
        String to;
        String[] employee_numbers;
        @Builder.Default
        boolean include_clocked_out_attendances_only=true;
        @Builder.Default
        boolean include_shift=true;
    }

    @Builder
    @Getter
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class ResponseDto {

        private String created_at;
        private Shift shift;
        private String note;
        private String clock_out_time;
        private String clock_in_time;
        private String employee_number;
        private int attendance_id;

        @Getter
        @AllArgsConstructor(staticName = "of")
        @NoArgsConstructor
        public static class Shift {
            private String note;
            private String end_time;
            private String start_time;
            private String location_code;
        }
    }
}
