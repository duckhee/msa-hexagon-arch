package kr.co.won.rentalcardservice.domain.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalCardNoTest {

    @Test
    @DisplayName(value = "01. rental card number create Tests")
    void createTests_with_rentalCardNo(){
        RentalCardNo rentalCardNo = RentalCardNo.createRentalCardNo();
        Assertions.assertNotNull(rentalCardNo);
    }

}