package kr.co.won.rentalcardservice.adapter.jpa.out;

import kr.co.won.rentalcardservice.domain.model.RentalCard;
import kr.co.won.rentalcardservice.domain.model.vo.RentalCardNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardNo> {

    @Query(value = "SELECT m FROM RentalCard m WHERE m.member.id=:id")
    Optional<RentalCard> findByMemberId(@Param("id") String memberId);

//    Optional<RentalCard> findByMemberId(String memberId);

    @Query(value = "SELECT m FROM RentalCard m WHERE m.rentalCardNo.no=:id")
    Optional<RentalCard> findById(@Param("id") Long rentalCardId);

//    Optional<RentalCard> findByRentalCardNo_No(Long rentalCardNo);

}
