package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Map;


class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);

    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        Address address1 = new Address("Russia", "Moscow", "Arbat", "10", null);
        Map<String, List<String>> result1 = Validator.advancedValidate(address1);
        assertThat(result1).isEmpty();

        Address address2 = new Address(null, "London", "Baker Street", "221B", "1");
        Map<String, List<String>> result2 = Validator.advancedValidate(address2);
        assertThat(result2).containsEntry("country", List.of("can not be null"));

        Address address3 = new Address("England", null, null, null, "2");
        Map<String, List<String>> result3 = Validator.advancedValidate(address3);
        assertThat(result3).containsEntry("city", List.of("can not be null"))
                .containsEntry("street", List.of("can not be null"))
                .containsEntry("houseNumber", List.of("can not be null"));

        Address address4 = new Address("US", "New York", null, "10", "5");
        Map<String, List<String>> result4 = Validator.advancedValidate(address4);
        assertThat(result4).containsEntry("country", List.of("length less than 4"))
                .containsEntry("street", List.of("can not be null"));
    }
    // END
}
