package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.dto.stats.FindingWordStatsDto;
import sejong.hci.darkmodesurveybackend.service.StatsService;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/finding-word")
    public ResponseEntity<ApiResult<FindingWordStatsDto>> getFindingWordStats() {

        ApiResult<FindingWordStatsDto> result = ApiResult.<FindingWordStatsDto>builder()
                .data(statsService.getFindingWordStats())
                .build();

        return ResponseEntity.ok(result);
    }
}
