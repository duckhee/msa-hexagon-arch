package kr.co.won.rentalcardservice.appilcation.port.in;

import kr.co.won.rentalcardservice.adapter.web.dto.RentItemOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.RentalCardOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.ReturnItemOutputDTO;
import kr.co.won.rentalcardservice.adapter.web.dto.UserInputDTO;
import kr.co.won.rentalcardservice.appilcation.port.out.RentalCardOutputPort;
import kr.co.won.rentalcardservice.appilcation.usecase.InQueryUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InQueryInputPort implements InQueryUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;


    public InQueryInputPort(RentalCardOutputPort rentalCardOutputPort) {
        this.rentalCardOutputPort = rentalCardOutputPort;
    }

    @Override
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(RentalCardOutputDTO::mapToDTO);
    }

    @Override
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard -> loadCard.getRentalItems()
                        .stream().map(RentItemOutputDTO::mapToDTO).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard -> loadCard.getReturnItems()
                        .stream()
                        .map(ReturnItemOutputDTO::mapToDTO).collect(Collectors.toList()));
    }
}
