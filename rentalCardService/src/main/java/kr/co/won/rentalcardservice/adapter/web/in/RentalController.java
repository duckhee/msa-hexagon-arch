package kr.co.won.rentalcardservice.adapter.web.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.won.rentalcardservice.adapter.web.dto.*;
import kr.co.won.rentalcardservice.appilcation.usecase.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Rental", description = "rental items and create rental card")
@RestController
@RequestMapping(path = "/api/rental")
public class RentalController {

    private final RentItemUseCase rentItemUseCase;
    private final ReturnItemUseCase returnItemUseCase;
    private final CreateRentalCardUseCase createRentalCardUseCase;
    private final OverDueItemUseCase overDueItemUseCase;
    private final ClearOverdueItemUseCase clearOverdueItemUseCase;
    private final InQueryUseCase inQueryUseCase;


    public RentalController(RentItemUseCase rentItemUseCase, ReturnItemUseCase returnItemUseCase, CreateRentalCardUseCase createRentalCardUseCase, OverDueItemUseCase overDueItemUseCase, ClearOverdueItemUseCase clearOverdueItemUseCase, InQueryUseCase inQueryUseCase) {
        this.rentItemUseCase = rentItemUseCase;
        this.returnItemUseCase = returnItemUseCase;
        this.createRentalCardUseCase = createRentalCardUseCase;
        this.overDueItemUseCase = overDueItemUseCase;
        this.clearOverdueItemUseCase = clearOverdueItemUseCase;
        this.inQueryUseCase = inQueryUseCase;
    }

    @Operation(summary = "Create Rental Card", description = "create user rental card", tags = {"Rental"})
    @PostMapping
    public ResponseEntity<RentalCardOutputDTO> createRentalCardResponse(@RequestBody UserInputDTO userInputDTO) {
        RentalCardOutputDTO response = createRentalCardUseCase.createRentalCard(userInputDTO);
        URI uri = URI.create("/api/rental/" + response.getMemberId());
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Find Rental Card", description = "Find user rental card", tags = {"Rental"})
    @GetMapping(path = "/{userId}")
    public ResponseEntity<RentalCardOutputDTO> findRentalCardResponse(@PathVariable(name = "userId") String userId) {
        RentalCardOutputDTO response = inQueryUseCase.getRentalCard(new UserInputDTO(userId, null))
                .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find user Rental Items", description = "Find user rental Items", tags = {"Rental"})
    @GetMapping(path = "/{userId}/rentItems")
    public ResponseEntity<List<RentItemOutputDTO>> findAllRentalItemsResponse(@PathVariable(name = "userId") String userId) {
        List<RentItemOutputDTO> findRentalItems = inQueryUseCase.getAllRentItem(new UserInputDTO(userId, null))
                .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));
        return ResponseEntity.ok(findRentalItems);
    }

    @Operation(summary = "Find user Return Items", description = "Find user return Items", tags = {"Rental"})
    @GetMapping(path = "/{userId}/returnItems")
    public ResponseEntity<List<ReturnItemOutputDTO>> findALlReturnItemsResponse(@PathVariable(name = "userId") String userId) {
        List<ReturnItemOutputDTO> findReturnItems = inQueryUseCase.getAllReturnItem(new UserInputDTO(userId, null))
                .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));
        return ResponseEntity.ok(findReturnItems);
    }

    @Operation(summary = "user rental items", description = "user rental items", tags = {"Rental"})
    @PostMapping(path = "/rent")
    public ResponseEntity<RentalCardOutputDTO> rentalItems(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO response = rentItemUseCase.rentItem(userItemInputDTO);
        URI uri = URI.create("/api/rental/" + userItemInputDTO.getUserId() + "/rentItems");
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "user return items", description = "user return items", tags = {"Rental"})
    @PostMapping(path = "/return")
    public ResponseEntity<RentalCardOutputDTO> returnItemResponse(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO returnResponse = returnItemUseCase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(returnResponse);
    }

    @Operation(summary = "user overdue items", description = "user overdue items", tags = {"Rental"})
    @PostMapping(path = "/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdueItemResponse(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO overdueItemResponse = overDueItemUseCase.overDueItem(userItemInputDTO);
        return ResponseEntity.ok(overdueItemResponse);
    }

    @Operation(summary = "user overdue items clear", description = "user overdue items clear", tags = {"Rental"})
    @PostMapping(path = "/clear-overdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverdueItemResponse(@RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalResultOutputDTO clearOverdueResponse = clearOverdueItemUseCase.clearOverdue(clearOverdueInfoDTO);
        return ResponseEntity.ok(clearOverdueResponse);
    }
}
