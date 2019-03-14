package travel.tool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel.tool.entity.Customer;
import travel.tool.repository.dao.JdbcTemplateCustomer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ipop
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private JdbcTemplateCustomer companyRepository;
    @InjectMocks
    private CustomerService unitUnderTest = new CustomerService();

    @Test
    void getAll() {
        when(companyRepository.getAll()).thenReturn(new ArrayList<>());
        assertTrue(unitUnderTest.getAll().isEmpty());
        verify(companyRepository).getAll();
    }

    @Test
    void findById() {
        verify(companyRepository).findById(any(Long.class));
    }

    @Test
    void update() {
        verify(companyRepository).update(any(Customer.class));
    }

    @Test
    void delete() {
        verify(companyRepository).delete(any(Customer.class));
    }
}