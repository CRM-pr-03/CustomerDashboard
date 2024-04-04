package addcontact.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import addcontact.model.AddContact;

@Repository
public interface AddContactRepository extends JpaRepository<AddContact, Long> {

    AddContact findByPhoneNumber(String phoneNumber);
    List<AddContact> findByCountry(String country);
    List<AddContact> findByCategory(String category);
    List<AddContact> findByDateCreatedBetween(Date startDate, Date endDate);
}
