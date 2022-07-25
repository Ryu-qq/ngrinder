package com.connectly.luxury.be.ngrinder.lib;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberEntity {


    private String memberId;

    private String memberName;

    private String email;

    private String memberType;


    private String memberGrade;


    private String blackListType;


    private String providerType;

    private String authYn;

    private String blackListYn;

    private String lastLoginDate;

    private String mobilePhoneNo;

    private String certified;

    private String agreementYn;

    private String adAcceptYn;

    private String deleteYn;

    private String insertDate;

    private String updateDate;

    private String insertOperator;
    
    private String updateOperator;


}
