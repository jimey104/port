package edu.du.web.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
}
