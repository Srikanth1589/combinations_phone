package com.phonenumber;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    MainController mainController;

    @Test
    public void phoneNumberCombinationPagedTest() {
        PhoneNumberCombinations combination = new PhoneNumberCombinations();
        List<String> paged = combination.getCombinationsPageSelected("1234567", 2, 10);
        String[] expected = {"1234j67", "1234j6p", "1234j6q", "1234j6r", "1234j6s", "1234jm7", "1234jmp", "1234jmq", "1234jmr","1234jms"};
        Assert.assertNotNull(paged);
        Assert.assertArrayEquals(paged.toArray(), expected);
    }
    
    @Test
    public void phoneNumberTotalCombinations() {
        PhoneNumberCombinations combination = new PhoneNumberCombinations();
        Page<String> paged = combination.getCombinationsPageWise("1234567", 1, 10);
        Assert.assertNotNull(paged);
        Assert.assertEquals(paged.getTotalPages(), 512);
    }
    
    @Test
    public void phoneNumberTotalWithNoCombinations() {
        PhoneNumberCombinations combination = new PhoneNumberCombinations();
        Page<String> paged = combination.getCombinationsPageWise("1111111", 1, 10);
        String[] expected = {"1111111"};
        Assert.assertNotNull(paged);
        Assert.assertEquals(paged.getTotalPages(), 1);
        Assert.assertEquals(paged.getContent(),Arrays.asList(expected));
    }
    
    @Test
    public void controllerTest() {
        ResponseEntity<List<String>> paged = mainController.phonenumberPage("1234567", 2, 10);
        System.out.println(paged.getBody());
    }

}
